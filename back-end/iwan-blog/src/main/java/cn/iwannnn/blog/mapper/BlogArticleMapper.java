package cn.iwannnn.blog.mapper;

import java.util.List;
import cn.iwannnn.blog.domain.BlogArticle;
import cn.iwannnn.blog.vo.BlogArticleVo;
import cn.iwannnn.blog.vo.LikeVo;

/**
 * 博客文章Mapper接口
 * 
 * @author iwan
 * @date 2021-09-15
 */
public interface BlogArticleMapper {
	/**
	 * 查询博客文章
	 * 
	 * @param articleId 博客文章主键
	 * @return 博客文章
	 */
	public BlogArticle selectBlogArticleByArticleId(Long articleId);

	/**
	 * 查询博客文章列表
	 * 
	 * @param blogArticle 博客文章
	 * @return 博客文章集合
	 */
	public List<BlogArticle> selectBlogArticleList(BlogArticle blogArticle);

	/**
	 * 新增博客文章
	 * 
	 * @param blogArticle 博客文章
	 * @return 结果
	 */
	public int insertBlogArticle(BlogArticle blogArticle);

	/**
	 * 修改博客文章
	 * 
	 * @param blogArticle 博客文章
	 * @return 结果
	 */
	public int updateBlogArticle(BlogArticle blogArticle);

	/**
	 * 删除博客文章
	 * 
	 * @param articleId 博客文章主键
	 * @return 结果
	 */
	public int deleteBlogArticleByArticleId(Long articleId);

	/**
	 * 批量删除博客文章
	 * 
	 * @param articleIds 需要删除的数据主键集合
	 * @return 结果
	 */
	public int deleteBlogArticleByArticleIds(Long[] articleIds);

	/**
	 * 获取文章标题id
	 * 
	 * @return id 标题
	 */
	public List<BlogArticleVo> selectBlogArticleVos();

	/**
	 * 点赞文章
	 * 
	 * @param userId和articleId
	 * @return 是否成功
	 */

	public int likeArticle(LikeVo likeVo);

	/**
	 * 增加点赞
	 * 
	 * @param userId和articleId
	 * @return 是否成功
	 * 
	 */
	public int increaseArticleLike(LikeVo likeVo);

	/**
	 * 取消点赞文章
	 * 
	 * @param userId和articleId
	 * @return 是否成功
	 */
	public int dislikeArticle(LikeVo likeVo);

	/**
	 * 减小点赞
	 * 
	 * @param userId和articleId
	 * @return 是否成功
	 */
	public int decreaseArticleLike(LikeVo likeVo);

	/**
	 * 增加浏览量
	 * 
	 * @param articleId
	 * @return 是否成功
	 */
	public int increaseArticleViews(Long articleId);
}
