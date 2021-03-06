package cn.iwannnn.blog.service.impl;

import java.util.List;
import cn.iwannnn.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.iwannnn.blog.mapper.BlogUserMapper;
import cn.iwannnn.blog.domain.BlogUser;
import cn.iwannnn.blog.service.IBlogUserService;
import cn.iwannnn.blog.vo.BlogUserVo;

/**
 * 博客用户Service业务层处理
 * 
 * @author iwan
 * @date 2021-09-15
 */
@Service
public class BlogUserServiceImpl implements IBlogUserService {
	@Autowired
	private BlogUserMapper blogUserMapper;

	/**
	 * 查询博客用户
	 * 
	 * @param userId 博客用户主键
	 * @return 博客用户
	 */
	@Override
	public BlogUser selectBlogUserByUserId(Long userId) {
		return blogUserMapper.selectBlogUserByUserId(userId);
	}

	/**
	 * 查询博客用户列表
	 * 
	 * @param blogUser 博客用户
	 * @return 博客用户
	 */
	@Override
	public List<BlogUser> selectBlogUserList(BlogUser blogUser) {
		return blogUserMapper.selectBlogUserList(blogUser);
	}

	/**
	 * 新增博客用户
	 * 
	 * @param blogUser 博客用户
	 * @return 结果
	 */
	@Override
	public int insertBlogUser(BlogUser blogUser) {
		blogUser.setCreateTime(DateUtils.getNowDate());
		return blogUserMapper.insertBlogUser(blogUser);
	}

	/**
	 * 修改博客用户
	 * 
	 * @param blogUser 博客用户
	 * @return 结果
	 */
	@Override
	public int updateBlogUser(BlogUser blogUser) {
		blogUser.setUpdateTime(DateUtils.getNowDate());
		return blogUserMapper.updateBlogUser(blogUser);
	}

	/**
	 * 批量删除博客用户
	 * 
	 * @param userIds 需要删除的博客用户主键
	 * @return 结果
	 */
	@Override
	public int deleteBlogUserByUserIds(Long[] userIds) {
		return blogUserMapper.deleteBlogUserByUserIds(userIds);
	}

	/**
	 * 删除博客用户信息
	 * 
	 * @param userId 博客用户主键
	 * @return 结果
	 */
	@Override
	public int deleteBlogUserByUserId(Long userId) {
		return blogUserMapper.deleteBlogUserByUserId(userId);
	}

	/**
	 * 获取用户信息
	 * 
	 * @return id account name
	 */
	@Override
	public List<BlogUserVo> selectBlogUserVos() {
		return blogUserMapper.selectBlogUserVos();
	}

	/**
	 * 查找用户
	 * 
	 * @param blogUser
	 * @return user
	 */
	@Override
	public BlogUser selectBlogUser(BlogUser blogUser) {
		return blogUserMapper.selectBlogUser(blogUser);
	}
}
