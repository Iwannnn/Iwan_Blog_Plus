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
import cn.iwannnn.blog.domain.BlogArticle;
import cn.iwannnn.blog.service.IBlogArticleService;
import cn.iwannnn.common.utils.poi.ExcelUtil;
import cn.iwannnn.common.core.page.TableDataInfo;

/**
 * 博客文章Controller
 * 
 * @author iwan
 * @date 2021-09-15
 */
@RestController
@RequestMapping("/blog/article")
public class BlogArticleController extends BaseController {
	@Autowired
	private IBlogArticleService blogArticleService;

	/**
	 * 查询博客文章列表
	 */
	// @PreAuthorize("@ss.hasPermi('blog:article:list')")
	@GetMapping("/list")
	public TableDataInfo list(BlogArticle blogArticle) {
		startPage();
		List<BlogArticle> list = blogArticleService.selectBlogArticleList(blogArticle);
		return getDataTable(list);
	}

	/**
	 * 导出博客文章列表
	 */
	@PreAuthorize("@ss.hasPermi('blog:article:export')")
	@Log(title = "博客文章", businessType = BusinessType.EXPORT)
	@GetMapping("/export")
	public AjaxResult export(BlogArticle blogArticle) {
		List<BlogArticle> list = blogArticleService.selectBlogArticleList(blogArticle);
		ExcelUtil<BlogArticle> util = new ExcelUtil<BlogArticle>(BlogArticle.class);
		return util.exportExcel(list, "博客文章数据");
	}

	/**
	 * 获取博客文章详细信息
	 */
	// @PreAuthorize("@ss.hasPermi('blog:article:query')")
	@GetMapping(value = "/{articleId}")
	public AjaxResult getInfo(@PathVariable("articleId") Long articleId) {
		return AjaxResult.success(blogArticleService.selectBlogArticleByArticleId(articleId));
	}

	/**
	 * 新增博客文章
	 */
	@PreAuthorize("@ss.hasPermi('blog:article:add')")
	@Log(title = "博客文章", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@RequestBody BlogArticle blogArticle) {
		return toAjax(blogArticleService.insertBlogArticle(blogArticle));
	}

	/**
	 * 修改博客文章
	 */
	@PreAuthorize("@ss.hasPermi('blog:article:edit')")
	@Log(title = "博客文章", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@RequestBody BlogArticle blogArticle) {
		return toAjax(blogArticleService.updateBlogArticle(blogArticle));
	}

	/**
	 * 删除博客文章
	 */
	@PreAuthorize("@ss.hasPermi('blog:article:remove')")
	@Log(title = "博客文章", businessType = BusinessType.DELETE)
	@DeleteMapping("/{articleIds}")
	public AjaxResult remove(@PathVariable Long[] articleIds) {
		return toAjax(blogArticleService.deleteBlogArticleByArticleIds(articleIds));
	}

	/**
	 * 获取标题id
	 */
	@GetMapping("/getArticleId")
	public AjaxResult getBlogArticleId() {
		return AjaxResult.success(blogArticleService.selectBlogArticleVos());
	}

}
