package cn.iwannnn.blog.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.iwannnn.common.annotation.Excel;
import cn.iwannnn.common.core.domain.BaseEntity;

/**
 * 博客文章对象 blog_article
 * 
 * @author iwan
 * @date 2021-09-15
 */
public class BlogArticle extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 文章id */
	private Long articleId;

	/** 博主 */
	@Excel(name = "博主")
	private Long userId;

	/** 文章标题 */
	@Excel(name = "文章标题")
	private String tittle;

	/** 文章内容 */
	@Excel(name = "文章内容")
	private String content;

	/** 音乐id */
	@Excel(name = "音乐id")
	private Long musicId;

	/** 分类id */
	@Excel(name = "分类id")
	private Long categoryId;

	/** 头像链接 */
	@Excel(name = "头像链接")
	private String avatar;

	/** 概要 */
	@Excel(name = "概要")
	private String summary;

	/** 浏览量 */
	@Excel(name = "浏览量")
	private Long pageviews;

	/** 点赞数 */
	@Excel(name = "点赞数")
	private Long likes;

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getTittle() {
		return tittle;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setMusicId(Long musicId) {
		this.musicId = musicId;
	}

	public Long getMusicId() {
		return musicId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setAvatar(String avator) {
		this.avatar = avator;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSummary() {
		return summary;
	}

	public void setPageviews(Long pageviews) {
		this.pageviews = pageviews;
	}

	public Long getPageviews() {
		return pageviews;
	}

	public void setLikes(Long likes) {
		this.likes = likes;
	}

	public Long getLikes() {
		return likes;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("articleId", getArticleId())
				.append("userId", getUserId()).append("tittle", getTittle()).append("content", getContent())
				.append("musicId", getMusicId()).append("categoryId", getCategoryId()).append("avator", getAvatar())
				.append("summary", getSummary()).append("pageviews", getPageviews()).append("likes", getLikes())
				.append("createTime", getCreateTime()).append("updateTime", getUpdateTime())
				.append("remark", getRemark()).toString();
	}
}
