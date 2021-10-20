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
import cn.iwannnn.blog.domain.BlogTag;
import cn.iwannnn.blog.service.IBlogTagService;
import cn.iwannnn.common.utils.poi.ExcelUtil;
import cn.iwannnn.common.core.page.TableDataInfo;

/**
 * 博客标签Controller
 * 
 * @author iwan
 * @date 2021-09-15
 */
@RestController
@RequestMapping("/blog/tag")
public class BlogTagController extends BaseController {
	@Autowired
	private IBlogTagService blogTagService;

	/**
	 * 查询博客标签列表
	 */
	// @PreAuthorize("@ss.hasPermi('blog:tag:list')")
	@GetMapping("/list")
	public TableDataInfo list(BlogTag blogTag) {
		startPage();
		List<BlogTag> list = blogTagService.selectBlogTagList(blogTag);
		return getDataTable(list);
	}

	/**
	 * 导出博客标签列表
	 */
	@PreAuthorize("@ss.hasPermi('blog:tag:export')")
	@Log(title = "博客标签", businessType = BusinessType.EXPORT)
	@GetMapping("/export")
	public AjaxResult export(BlogTag blogTag) {
		List<BlogTag> list = blogTagService.selectBlogTagList(blogTag);
		ExcelUtil<BlogTag> util = new ExcelUtil<BlogTag>(BlogTag.class);
		return util.exportExcel(list, "博客标签数据");
	}

	/**
	 * 获取博客标签详细信息
	 */
	@PreAuthorize("@ss.hasPermi('blog:tag:query')")
	@GetMapping(value = "/{tagId}")
	public AjaxResult getInfo(@PathVariable("tagId") Long tagId) {
		return AjaxResult.success(blogTagService.selectBlogTagByTagId(tagId));
	}

	/**
	 * 新增博客标签
	 */
	@PreAuthorize("@ss.hasPermi('blog:tag:add')")
	@Log(title = "博客标签", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@RequestBody BlogTag blogTag) {
		return toAjax(blogTagService.insertBlogTag(blogTag));
	}

	/**
	 * 修改博客标签
	 */
	@PreAuthorize("@ss.hasPermi('blog:tag:edit')")
	@Log(title = "博客标签", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@RequestBody BlogTag blogTag) {
		return toAjax(blogTagService.updateBlogTag(blogTag));
	}

	/**
	 * 删除博客标签
	 */
	@PreAuthorize("@ss.hasPermi('blog:tag:remove')")
	@Log(title = "博客标签", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tagIds}")
	public AjaxResult remove(@PathVariable Long[] tagIds) {
		return toAjax(blogTagService.deleteBlogTagByTagIds(tagIds));
	}

	/**
	 * 获取id和标签名
	 */
	@GetMapping("/getTagId")
	public AjaxResult getBlogTagId() {
		return AjaxResult.success(blogTagService.selectBlogTagVos());
	}
}
