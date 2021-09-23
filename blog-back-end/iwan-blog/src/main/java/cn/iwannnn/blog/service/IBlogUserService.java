package cn.iwannnn.blog.service;

import java.util.List;
import cn.iwannnn.blog.domain.BlogUser;
import cn.iwannnn.blog.vo.BlogUserVo;

/**
 * 博客用户Service接口
 * 
 * @author iwan
 * @date 2021-09-15
 */
public interface IBlogUserService {
	/**
	 * 查询博客用户
	 * 
	 * @param userId 博客用户主键
	 * @return 博客用户
	 */
	public BlogUser selectBlogUserByUserId(Long userId);

	/**
	 * 查询博客用户列表
	 * 
	 * @param blogUser 博客用户
	 * @return 博客用户集合
	 */
	public List<BlogUser> selectBlogUserList(BlogUser blogUser);

	/**
	 * 新增博客用户
	 * 
	 * @param blogUser 博客用户
	 * @return 结果
	 */
	public int insertBlogUser(BlogUser blogUser);

	/**
	 * 修改博客用户
	 * 
	 * @param blogUser 博客用户
	 * @return 结果
	 */
	public int updateBlogUser(BlogUser blogUser);

	/**
	 * 批量删除博客用户
	 * 
	 * @param userIds 需要删除的博客用户主键集合
	 * @return 结果
	 */
	public int deleteBlogUserByUserIds(Long[] userIds);

	/**
	 * 删除博客用户信息
	 * 
	 * @param userId 博客用户主键
	 * @return 结果
	 */
	public int deleteBlogUserByUserId(Long userId);

	/**
	 * 获取用户信息
	 * 
	 * @return id account name
	 */
	public List<BlogUserVo> selectBlogUserVos();

}
