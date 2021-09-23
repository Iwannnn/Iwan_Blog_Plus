package cn.iwannnn.blog.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.iwannnn.common.annotation.Log;
import cn.iwannnn.common.core.controller.BaseController;
import cn.iwannnn.common.core.domain.AjaxResult;
import cn.iwannnn.common.enums.BusinessType;
import cn.iwannnn.blog.domain.BlogCategory;
import cn.iwannnn.blog.service.IBlogCategoryService;
import cn.iwannnn.common.utils.poi.ExcelUtil;
import cn.iwannnn.common.core.page.TableDataInfo;

/**
 * 博客分类Controller
 * 
 * @author iwan
 * @date 2021-09-15
 */
@RestController
@RequestMapping("/blog/category")
public class BlogCategoryController extends BaseController {
	@Autowired
	private IBlogCategoryService blogCategoryService;

	/**
	 * 查询博客分类列表
	 */
	@PreAuthorize("@ss.hasPermi('blog:category:list')")
	@GetMapping("/list")
	public TableDataInfo list(BlogCategory blogCategory) {
		startPage();
		List<BlogCategory> list = blogCategoryService.selectBlogCategoryList(blogCategory);
		return getDataTable(list);
	}

	/**
	 * 导出博客分类列表
	 */
	@PreAuthorize("@ss.hasPermi('blog:category:export')")
	@Log(title = "博客分类", businessType = BusinessType.EXPORT)
	@GetMapping("/export")
	public AjaxResult export(BlogCategory blogCategory) {
		List<BlogCategory> list = blogCategoryService.selectBlogCategoryList(blogCategory);
		ExcelUtil<BlogCategory> util = new ExcelUtil<BlogCategory>(BlogCategory.class);
		return util.exportExcel(list, "博客分类数据");
	}

	/**
	 * 获取博客分类详细信息
	 */
	@PreAuthorize("@ss.hasPermi('blog:category:query')")
	@GetMapping(value = "/{categoryId}")
	public AjaxResult getInfo(@PathVariable("categoryId") Long categoryId) {
		return AjaxResult.success(blogCategoryService.selectBlogCategoryByCategoryId(categoryId));
	}

	/**
	 * 新增博客分类
	 */
	@PreAuthorize("@ss.hasPermi('blog:category:add')")
	@Log(title = "博客分类", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@RequestBody BlogCategory blogCategory) {
		return toAjax(blogCategoryService.insertBlogCategory(blogCategory));
	}

	/**
	 * 修改博客分类
	 */
	@PreAuthorize("@ss.hasPermi('blog:category:edit')")
	@Log(title = "博客分类", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@RequestBody BlogCategory blogCategory) {
		return toAjax(blogCategoryService.updateBlogCategory(blogCategory));
	}

	/**
	 * 删除博客分类
	 */
	@PreAuthorize("@ss.hasPermi('blog:category:remove')")
	@Log(title = "博客分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{categoryIds}")
	public AjaxResult remove(@PathVariable Long[] categoryIds) {
		return toAjax(blogCategoryService.deleteBlogCategoryByCategoryIds(categoryIds));
	}

	/**
	 * 获取类别id
	 */
	@GetMapping("/getCategoryId")
	public AjaxResult getBlogCategoryId() {
		return AjaxResult.success(blogCategoryService.selectBlogCategoryVos());
	}
}
