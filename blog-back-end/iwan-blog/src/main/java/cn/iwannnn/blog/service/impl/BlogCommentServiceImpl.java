package cn.iwannnn.blog.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.iwannnn.blog.mapper.BlogCommentMapper;
import cn.iwannnn.blog.domain.BlogComment;
import cn.iwannnn.blog.service.IBlogCommentService;

/**
 * 文章评论Service业务层处理
 * 
 * @author iwan
 * @date 2021-09-14
 */
@Service
public class BlogCommentServiceImpl implements IBlogCommentService 
{
    @Autowired
    private BlogCommentMapper blogCommentMapper;

    /**
     * 查询文章评论
     * 
     * @param commentId 文章评论主键
     * @return 文章评论
     */
    @Override
    public BlogComment selectBlogCommentByCommentId(Long commentId)
    {
        return blogCommentMapper.selectBlogCommentByCommentId(commentId);
    }

    /**
     * 查询文章评论列表
     * 
     * @param blogComment 文章评论
     * @return 文章评论
     */
    @Override
    public List<BlogComment> selectBlogCommentList(BlogComment blogComment)
    {
        return blogCommentMapper.selectBlogCommentList(blogComment);
    }

    /**
     * 新增文章评论
     * 
     * @param blogComment 文章评论
     * @return 结果
     */
    @Override
    public int insertBlogComment(BlogComment blogComment)
    {
        blogComment.setCreateTime(DateUtils.getNowDate());
        return blogCommentMapper.insertBlogComment(blogComment);
    }

    /**
     * 修改文章评论
     * 
     * @param blogComment 文章评论
     * @return 结果
     */
    @Override
    public int updateBlogComment(BlogComment blogComment)
    {
        blogComment.setUpdateTime(DateUtils.getNowDate());
        return blogCommentMapper.updateBlogComment(blogComment);
    }

    /**
     * 批量删除文章评论
     * 
     * @param commentIds 需要删除的文章评论主键
     * @return 结果
     */
    @Override
    public int deleteBlogCommentByCommentIds(Long[] commentIds)
    {
        return blogCommentMapper.deleteBlogCommentByCommentIds(commentIds);
    }

    /**
     * 删除文章评论信息
     * 
     * @param commentId 文章评论主键
     * @return 结果
     */
    @Override
    public int deleteBlogCommentByCommentId(Long commentId)
    {
        return blogCommentMapper.deleteBlogCommentByCommentId(commentId);
    }
}
