package cn.iwannnn.blog.service;

import java.util.List;
import cn.iwannnn.blog.domain.BlogComment;

/**
 * 博客评论Service接口
 * 
 * @author iwan
 * @date 2021-09-15
 */
public interface IBlogCommentService 
{
    /**
     * 查询博客评论
     * 
     * @param commentId 博客评论主键
     * @return 博客评论
     */
    public BlogComment selectBlogCommentByCommentId(Long commentId);

    /**
     * 查询博客评论列表
     * 
     * @param blogComment 博客评论
     * @return 博客评论集合
     */
    public List<BlogComment> selectBlogCommentList(BlogComment blogComment);

    /**
     * 新增博客评论
     * 
     * @param blogComment 博客评论
     * @return 结果
     */
    public int insertBlogComment(BlogComment blogComment);

    /**
     * 修改博客评论
     * 
     * @param blogComment 博客评论
     * @return 结果
     */
    public int updateBlogComment(BlogComment blogComment);

    /**
     * 批量删除博客评论
     * 
     * @param commentIds 需要删除的博客评论主键集合
     * @return 结果
     */
    public int deleteBlogCommentByCommentIds(Long[] commentIds);

    /**
     * 删除博客评论信息
     * 
     * @param commentId 博客评论主键
     * @return 结果
     */
    public int deleteBlogCommentByCommentId(Long commentId);
}
