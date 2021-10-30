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
import cn.iwannnn.common.utils.StringUtils;
import cn.iwannnn.common.utils.poi.ExcelUtil;
import cn.iwannnn.common.utils.sign.Md5Utils;
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
	// @PreAuthorize("@ss.hasPermi('blog:user:list')")
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
	// @PreAuthorize("@ss.hasPermi('blog:user:add')")
	@Log(title = "博客用户", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@RequestBody BlogUser blogUser) {
		blogUser.setPassword(Md5Utils.hash(blogUser.getPassword()));
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

	/**
	 * 获取id和用户名
	 */
	@GetMapping("/getUserId")
	public AjaxResult getBlogUserId() {
		return AjaxResult.success(blogUserService.selectBlogUserVos());
	}

	/**
	 * 登录
	 */
	@RequestMapping("/login")
	public AjaxResult login(String account, String password) {
		BlogUser blogUser = new BlogUser();
		blogUser.setAccount(account);
		BlogUser res = blogUserService.selectBlogUser(blogUser);
		if (res == null) {
			return AjaxResult.success("账号不存在");
		} else if (res.getPassword().equals(Md5Utils.hash(password))) {
			return AjaxResult.success();
		} else {
			return AjaxResult.success("密码错误");
		}

	}

	/**
	 * 注册
	 */
	@RequestMapping("/register")
	public AjaxResult register(@RequestBody BlogUser blogUser) {
		return AjaxResult.success(blogUserService.insertBlogUser(blogUser));
	}

	/**
	 * 验证用户注册信息
	 */
	@RequestMapping("/check")
	public AjaxResult check(BlogUser blogUser) {
		if (blogUser.getAccount() == null && blogUser.getPhone() == null && blogUser.getEmail() == null)
			return AjaxResult.success();
		BlogUser res = blogUserService.selectBlogUser(blogUser);
		if (res == null)
			return AjaxResult.success();
		else
			return AjaxResult.success("exist");
	}
}
