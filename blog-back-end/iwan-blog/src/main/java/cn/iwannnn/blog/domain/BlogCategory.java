package cn.iwannnn.blog.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.iwannnn.common.annotation.Excel;
import cn.iwannnn.common.core.domain.BaseEntity;

/**
 * 博客分类对象 blog_category
 * 
 * @author iwan
 * @date 2021-09-15
 */
public class BlogCategory extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 分类id */
	private Long categoryId;

	/** 分类名称 */
	@Excel(name = "分类名称")
	private String categoryName;

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("categoryId", getCategoryId())
				.append("categoryName", getCategoryName()).append("createTime", getCreateTime())
				.append("updateTime", getUpdateTime()).append("remark", getRemark()).toString();
	}
}
