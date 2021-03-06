package cn.iwannnn.blog.mapper;

import java.util.List;
import cn.iwannnn.blog.domain.BlogUser;
import cn.iwannnn.blog.vo.BlogUserVo;

/**
 * 博客用户Mapper接口
 * 
 * @author iwan
 * @date 2021-09-15
 */
public interface BlogUserMapper {
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
	 * 删除博客用户
	 * 
	 * @param userId 博客用户主键
	 * @return 结果
	 */
	public int deleteBlogUserByUserId(Long userId);

	/**
	 * 批量删除博客用户
	 * 
	 * @param userIds 需要删除的数据主键集合
	 * @return 结果
	 */
	public int deleteBlogUserByUserIds(Long[] userIds);

	/**
	 * 获取用户信息
	 * 
	 * @return id account name
	 */
	public List<BlogUserVo> selectBlogUserVos();

	/**
	 * 查找用户
	 * 
	 * @param blogUser
	 * @return user
	 */
	public BlogUser selectBlogUser(BlogUser blogUser);
}
