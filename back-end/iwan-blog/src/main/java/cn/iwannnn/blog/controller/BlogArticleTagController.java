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
import cn.iwannnn.blog.domain.BlogArticleTag;
import cn.iwannnn.blog.service.IBlogArticleTagService;
import cn.iwannnn.common.utils.poi.ExcelUtil;
import cn.iwannnn.common.core.page.TableDataInfo;

/**
 * 文章标签Controller
 * 
 * @author iwan
 * @date 2021-09-15
 */
@RestController
@RequestMapping("/blog/articletag")
public class BlogArticleTagController extends BaseController {
	@Autowired
	private IBlogArticleTagService blogArticleTagService;

	/**
	 * 查询文章标签列表
	 */
	// @PreAuthorize("@ss.hasPermi('blog:tag:list')")
	@GetMapping("/list")
	public TableDataInfo list(BlogArticleTag blogArticleTag) {
		startPage();
		List<BlogArticleTag> list = blogArticleTagService.selectBlogArticleTagList(blogArticleTag);
		return getDataTable(list);
	}

	/**
	 * 导出文章标签列表
	 */
	@PreAuthorize("@ss.hasPermi('blog:tag:export')")
	@Log(title = "文章标签", businessType = BusinessType.EXPORT)
	@GetMapping("/export")
	public AjaxResult export(BlogArticleTag blogArticleTag) {
		List<BlogArticleTag> list = blogArticleTagService.selectBlogArticleTagList(blogArticleTag);
		ExcelUtil<BlogArticleTag> util = new ExcelUtil<BlogArticleTag>(BlogArticleTag.class);
		return util.exportExcel(list, "文章标签数据");
	}

	/**
	 * 获取文章标签详细信息
	 */
	@PreAuthorize("@ss.hasPermi('blog:tag:query')")
	@GetMapping(value = "/{id}")
	public AjaxResult getInfo(@PathVariable("id") Long id) {
		return AjaxResult.success(blogArticleTagService.selectBlogArticleTagById(id));
	}

	/**
	 * 新增文章标签
	 */
	@PreAuthorize("@ss.hasPermi('blog:tag:add')")
	@Log(title = "文章标签", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@RequestBody BlogArticleTag blogArticleTag) {
		return toAjax(blogArticleTagService.insertBlogArticleTag(blogArticleTag));
	}

	/**
	 * 修改文章标签
	 */
	@PreAuthorize("@ss.hasPermi('blog:tag:edit')")
	@Log(title = "文章标签", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@RequestBody BlogArticleTag blogArticleTag) {
		return toAjax(blogArticleTagService.updateBlogArticleTag(blogArticleTag));
	}

	/**
	 * 删除文章标签
	 */
	@PreAuthorize("@ss.hasPermi('blog:tag:remove')")
	@Log(title = "文章标签", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
	public AjaxResult remove(@PathVariable Long[] ids) {
		return toAjax(blogArticleTagService.deleteBlogArticleTagByIds(ids));
	}
}
