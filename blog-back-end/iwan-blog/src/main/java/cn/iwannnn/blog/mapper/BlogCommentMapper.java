package cn.iwannnn.blog.mapper;

import java.util.List;
import cn.iwannnn.blog.domain.BlogComment;

/**
 * 文章评论Mapper接口
 * 
 * @author iwan
 * @date 2021-09-14
 */
public interface BlogCommentMapper 
{
    /**
     * 查询文章评论
     * 
     * @param commentId 文章评论主键
     * @return 文章评论
     */
    public BlogComment selectBlogCommentByCommentId(Long commentId);

    /**
     * 查询文章评论列表
     * 
     * @param blogComment 文章评论
     * @return 文章评论集合
     */
    public List<BlogComment> selectBlogCommentList(BlogComment blogComment);

    /**
     * 新增文章评论
     * 
     * @param blogComment 文章评论
     * @return 结果
     */
    public int insertBlogComment(BlogComment blogComment);

    /**
     * 修改文章评论
     * 
     * @param blogComment 文章评论
     * @return 结果
     */
    public int updateBlogComment(BlogComment blogComment);

    /**
     * 删除文章评论
     * 
     * @param commentId 文章评论主键
     * @return 结果
     */
    public int deleteBlogCommentByCommentId(Long commentId);

    /**
     * 批量删除文章评论
     * 
     * @param commentIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBlogCommentByCommentIds(Long[] commentIds);
}
