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
import cn.iwannnn.blog.domain.BlogUser;
import cn.iwannnn.blog.service.IBlogUserService;
import cn.iwannnn.common.utils.poi.ExcelUtil;
import cn.iwannnn.common.core.page.TableDataInfo;

/**
 * 博客用户Controller
 * 
 * @author iwan
 * @date 2021-09-15
 */
@RestController
@RequestMapping("/blog/user")
public class BlogUserController extends BaseController {
	@Autowired
	private IBlogUserService blogUserService;

	/**
	 * 查询博客用户列表
	 */
	@PreAuthorize("@ss.hasPermi('blog:user:list')")
	@GetMapping("/list")
	public TableDataInfo list(BlogUser blogUser) {
		startPage();
		List<BlogUser> list = blogUserService.selectBlogUserList(blogUser);
		return getDataTable(list);
	}

	/**
	 * 导出博客用户列表
	 */
	@PreAuthorize("@ss.hasPermi('blog:user:export')")
	@Log(title = "博客用户", businessType = BusinessType.EXPORT)
	@GetMapping("/export")
	public AjaxResult export(BlogUser blogUser) {
		List<BlogUser> list = blogUserService.selectBlogUserList(blogUser);
		ExcelUtil<BlogUser> util = new ExcelUtil<BlogUser>(BlogUser.class);
		return util.exportExcel(list, "博客用户数据");
	}

	/**
	 * 获取博客用户详细信息
	 */
	@PreAuthorize("@ss.hasPermi('blog:user:query')")
	@GetMapping(value = "/{userId}")
	public AjaxResult getInfo(@PathVariable("userId") Long userId) {
		return AjaxResult.success(blogUserService.selectBlogUserByUserId(userId));
	}

	/**
	 * 新增博客用户
	 */
	@PreAuthorize("@ss.hasPermi('blog:user:add')")
	@Log(title = "博客用户", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@RequestBody BlogUser blogUser) {
		return toAjax(blogUserService.insertBlogUser(blogUser));
	}

	/**
	 * 修改博客用户
	 */
	@PreAuthorize("@ss.hasPermi('blog:user:edit')")
	@Log(title = "博客用户", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@RequestBody BlogUser blogUser) {
		return toAjax(blogUserService.updateBlogUser(blogUser));
	}

	/**
	 * 删除博客用户
	 */
	@PreAuthorize("@ss.hasPermi('blog:user:remove')")
	@Log(title = "博客用户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
	public AjaxResult remove(@PathVariable Long[] userIds) {
		return toAjax(blogUserService.deleteBlogUserByUserIds(userIds));
	}
}
