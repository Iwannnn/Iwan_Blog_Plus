package cn.iwannnn.blog.service.impl;

import java.util.List;

import cn.iwannnn.common.utils.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.iwannnn.blog.mapper.BlogArticleMapper;
import cn.iwannnn.blog.domain.BlogArticle;
import cn.iwannnn.blog.service.IBlogArticleService;
import cn.iwannnn.blog.vo.BlogArticleVo;
import cn.iwannnn.blog.vo.LikeVo;

/**
 * 博客文章Service业务层处理
 * 
 * @author iwan
 * @date 2021-09-15
 */
@Service
public class BlogArticleServiceImpl implements IBlogArticleService {
	@Autowired
	private BlogArticleMapper blogArticleMapper;

	/**
	 * 查询博客文章
	 * 
	 * @param articleId 博客文章主键
	 * @return 博客文章
	 */
	@Override
	public BlogArticle selectBlogArticleByArticleId(Long articleId) {
		return blogArticleMapper.selectBlogArticleByArticleId(articleId);
	}

	/**
	 * 查询博客文章列表
	 * 
	 * @param blogArticle 博客文章
	 * @return 博客文章
	 */
	@Override
	public List<BlogArticle> selectBlogArticleList(BlogArticle blogArticle) {
		return blogArticleMapper.selectBlogArticleList(blogArticle);
	}

	/**
	 * 新增博客文章
	 * 
	 * @param blogArticle 博客文章
	 * @return 结果
	 */
	@Override
	public int insertBlogArticle(BlogArticle blogArticle) {
		blogArticle.setCreateTime(DateUtils.getNowDate());
		return blogArticleMapper.insertBlogArticle(blogArticle);
	}

	/**
	 * 修改博客文章
	 * 
	 * @param blogArticle 博客文章
	 * @return 结果
	 */
	@Override
	public int updateBlogArticle(BlogArticle blogArticle) {
		blogArticle.setUpdateTime(DateUtils.getNowDate());
		return blogArticleMapper.updateBlogArticle(blogArticle);
	}

	/**
	 * 批量删除博客文章
	 * 
	 * @param articleIds 需要删除的博客文章主键
	 * @return 结果
	 */
	@Override
	public int deleteBlogArticleByArticleIds(Long[] articleIds) {
		return blogArticleMapper.deleteBlogArticleByArticleIds(articleIds);
	}

	/**
	 * 删除博客文章信息
	 * 
	 * @param articleId 博客文章主键
	 * @return 结果
	 */
	@Override
	public int deleteBlogArticleByArticleId(Long articleId) {
		return blogArticleMapper.deleteBlogArticleByArticleId(articleId);
	}

	/**
	 * 获取文章标题id
	 * 
	 * @return id 标题
	 */
	@Override
	public List<BlogArticleVo> selectBlogArticleVos() {
		return blogArticleMapper.selectBlogArticleVos();
	}

	@Override
	public int likeArticle(LikeVo likeVo) {
		if (likeVo.getUserId() != 0)
			blogArticleMapper.likeArticle(likeVo);
		return blogArticleMapper.increaseArticleLike(likeVo);
	}

	@Override
	public int dislikeArticle(LikeVo likeVo) {
		if (likeVo.getUserId() != 0)
			blogArticleMapper.dislikeArticle(likeVo);
		return blogArticleMapper.decreaseArticleLike(likeVo);
	}

}
