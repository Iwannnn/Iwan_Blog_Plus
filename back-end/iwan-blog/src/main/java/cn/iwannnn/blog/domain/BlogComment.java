package cn.iwannnn.blog.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.iwannnn.common.annotation.Excel;
import cn.iwannnn.common.core.domain.BaseEntity;

/**
 * 博客评论对象 blog_comment
 * 
 * @author iwan
 * @date 2021-09-15
 */
public class BlogComment extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 评论id */
	private Long commentId;

	/** 博客id */
	@Excel(name = "博客id")
	private Long blogId;

	/** 父级评论 */
	@Excel(name = "父级评论")
	private Long parentId;

	/** 被评论用户id */
	@Excel(name = "被评论用户id")
	private Long becommentUserId;

	/** 评论用户id */
	@Excel(name = "评论用户id")
	private Long commentUserId;

	/** 评论点赞数 */
	@Excel(name = "评论点赞数")
	private Long likes;

	/** 评论内容 */
	@Excel(name = "评论内容")
	private String content;

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

	public Long getBlogId() {
		return blogId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setBecommentUserId(Long becommentUserId) {
		this.becommentUserId = becommentUserId;
	}

	public Long getBecommentUserId() {
		return becommentUserId;
	}

	public void setCommentUserId(Long commentUserId) {
		this.commentUserId = commentUserId;
	}

	public Long getCommentUserId() {
		return commentUserId;
	}

	public void setLikes(Long likes) {
		this.likes = likes;
	}

	public Long getLikes() {
		return likes;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("commentId", getCommentId())
				.append("blogId", getBlogId()).append("parentId", getParentId())
				.append("becommentUserId", getBecommentUserId()).append("commentUserId", getCommentUserId())
				.append("likes", getLikes()).append("content", getContent()).append("createTime", getCreateTime())
				.append("updateTime", getUpdateTime()).append("remark", getRemark()).toString();
	}
}
