package cn.iwannnn.blog.controller;

import java.util.ArrayList;
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
import cn.iwannnn.blog.domain.BlogArticleTag;
import cn.iwannnn.blog.service.IBlogArticleService;
import cn.iwannnn.blog.service.IBlogArticleTagService;
import cn.iwannnn.blog.service.IBlogCategoryService;
import cn.iwannnn.blog.vo.LikeVo;
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

	@Autowired
	private IBlogCategoryService blogCategoryService;

	@Autowired
	private IBlogArticleTagService blogArticleTagService;

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

	@GetMapping("/listByTagId")
	public TableDataInfo listByTagId(Long tagId) {
		startPage();
		BlogArticleTag blogArticleTag = new BlogArticleTag();
		blogArticleTag.setTagId(tagId);
		List<BlogArticleTag> list = blogArticleTagService.selectBlogArticleTagList(blogArticleTag);
		List<BlogArticle> res = new ArrayList<BlogArticle>();
		for (int i = 0; i < list.size(); i++) {
			res.add(blogArticleService.selectBlogArticleByArticleId(list.get(i).getArticleId()));
		}
		if (res.equals(null))
			return getDataTable(null);
		return getDataTable(res);
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
		int res = blogArticleService.insertBlogArticle(blogArticle);
		blogCategoryService.updataCategoryNum();
		return toAjax(res);
	}

	/**
	 * 修改博客文章
	 */
	@PreAuthorize("@ss.hasPermi('blog:article:edit')")
	@Log(title = "博客文章", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@RequestBody BlogArticle blogArticle) {
		int res = blogArticleService.updateBlogArticle(blogArticle);
		blogCategoryService.updataCategoryNum();
		return toAjax(res);
	}

	/**
	 * 删除博客文章
	 */
	@PreAuthorize("@ss.hasPermi('blog:article:remove')")
	@Log(title = "博客文章", businessType = BusinessType.DELETE)
	@DeleteMapping("/{articleIds}")
	public AjaxResult remove(@PathVariable Long[] articleIds) {
		int res = blogArticleService.deleteBlogArticleByArticleIds(articleIds);
		blogCategoryService.updataCategoryNum();
		return toAjax(res);
	}

	/**
	 * 获取标题id
	 */
	@GetMapping("/getArticleId")
	public AjaxResult getBlogArticleId() {
		return AjaxResult.success(blogArticleService.selectBlogArticleVos());
	}

	/**
	 * 点赞
	 */
	@RequestMapping("/likeArticle")
	public AjaxResult likeArticle(@RequestBody LikeVo likeVo) {
		return AjaxResult.success(blogArticleService.likeArticle(likeVo));
	}

	/**
	 * 取消点赞
	 */
	@RequestMapping("/dislikeArticle")
	public AjaxResult dislikeArticle(@RequestBody LikeVo likeVo) {
		return AjaxResult.success(blogArticleService.dislikeArticle(likeVo));
	}
}
