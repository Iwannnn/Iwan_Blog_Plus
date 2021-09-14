package cn.iwannnn.blog.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户点赞管理对象 blog_user_like
 * 
 * @author iwan
 * @date 2021-09-14
 */
public class BlogUserLike extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户点赞关联 */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 文章id */
    @Excel(name = "文章id")
    private Long articleId;

    /** 是否取消了点赞 */
    @Excel(name = "是否取消了点赞")
    private Integer isDelete;

    /** 评论id */
    @Excel(name = "评论id")
    private Long commentId;

    /** 备注 */
    @Excel(name = "备注")
    private String temark;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setArticleId(Long articleId) 
    {
        this.articleId = articleId;
    }

    public Long getArticleId() 
    {
        return articleId;
    }
    public void setIsDelete(Integer isDelete) 
    {
        this.isDelete = isDelete;
    }

    public Integer getIsDelete() 
    {
        return isDelete;
    }
    public void setCommentId(Long commentId) 
    {
        this.commentId = commentId;
    }

    public Long getCommentId() 
    {
        return commentId;
    }
    public void setTemark(String temark) 
    {
        this.temark = temark;
    }

    public String getTemark() 
    {
        return temark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("articleId", getArticleId())
            .append("isDelete", getIsDelete())
            .append("commentId", getCommentId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("temark", getTemark())
            .toString();
    }
}
