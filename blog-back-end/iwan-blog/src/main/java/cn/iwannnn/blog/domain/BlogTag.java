package cn.iwannnn.blog.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.iwannnn.common.annotation.Excel;
import cn.iwannnn.common.core.domain.BaseEntity;

/**
 * 博客标签对象 blog_tag
 * 
 * @author iwan
 * @date 2021-09-15
 */
public class BlogTag extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 标签id */
	private Long tagId;

	/** 标签名称 */
	@Excel(name = "标签名称")
	private String tagName;

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public Long getTagId() {
		return tagId;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getTagName() {
		return tagName;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("tagId", getTagId())
				.append("tagName", getTagName()).append("createTime", getCreateTime())
				.append("updateTime", getUpdateTime()).append("remark", getRemark()).toString();
	}
}
