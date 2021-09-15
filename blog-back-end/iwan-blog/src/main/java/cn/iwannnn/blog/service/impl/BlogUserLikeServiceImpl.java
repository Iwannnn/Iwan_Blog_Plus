package cn.iwannnn.blog.service.impl;

import java.util.List;
import cn.iwannnn.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.iwannnn.blog.mapper.BlogUserLikeMapper;
import cn.iwannnn.blog.domain.BlogUserLike;
import cn.iwannnn.blog.service.IBlogUserLikeService;

/**
 * 用户点赞Service业务层处理
 * 
 * @author iwan
 * @date 2021-09-15
 */
@Service
public class BlogUserLikeServiceImpl implements IBlogUserLikeService {
	@Autowired
	private BlogUserLikeMapper blogUserLikeMapper;

	/**
	 * 查询用户点赞
	 * 
	 * @param id 用户点赞主键
	 * @return 用户点赞
	 */
	@Override
	public BlogUserLike selectBlogUserLikeById(Long id) {
		return blogUserLikeMapper.selectBlogUserLikeById(id);
	}

	/**
	 * 查询用户点赞列表
	 * 
	 * @param blogUserLike 用户点赞
	 * @return 用户点赞
	 */
	@Override
	public List<BlogUserLike> selectBlogUserLikeList(BlogUserLike blogUserLike) {
		return blogUserLikeMapper.selectBlogUserLikeList(blogUserLike);
	}

	/**
	 * 新增用户点赞
	 * 
	 * @param blogUserLike 用户点赞
	 * @return 结果
	 */
	@Override
	public int insertBlogUserLike(BlogUserLike blogUserLike) {
		blogUserLike.setCreateTime(DateUtils.getNowDate());
		return blogUserLikeMapper.insertBlogUserLike(blogUserLike);
	}

	/**
	 * 修改用户点赞
	 * 
	 * @param blogUserLike 用户点赞
	 * @return 结果
	 */
	@Override
	public int updateBlogUserLike(BlogUserLike blogUserLike) {
		blogUserLike.setUpdateTime(DateUtils.getNowDate());
		return blogUserLikeMapper.updateBlogUserLike(blogUserLike);
	}

	/**
	 * 批量删除用户点赞
	 * 
	 * @param ids 需要删除的用户点赞主键
	 * @return 结果
	 */
	@Override
	public int deleteBlogUserLikeByIds(Long[] ids) {
		return blogUserLikeMapper.deleteBlogUserLikeByIds(ids);
	}

	/**
	 * 删除用户点赞信息
	 * 
	 * @param id 用户点赞主键
	 * @return 结果
	 */
	@Override
	public int deleteBlogUserLikeById(Long id) {
		return blogUserLikeMapper.deleteBlogUserLikeById(id);
	}
}
