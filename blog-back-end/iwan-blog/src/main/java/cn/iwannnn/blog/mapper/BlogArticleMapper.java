package cn.iwannnn.blog.mapper;

import java.util.List;
import cn.iwannnn.blog.domain.BlogArticle;

/**
 * 博客文章Mapper接口
 * 
 * @author iwan
 * @date 2021-09-15
 */
public interface BlogArticleMapper 
{
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
}
