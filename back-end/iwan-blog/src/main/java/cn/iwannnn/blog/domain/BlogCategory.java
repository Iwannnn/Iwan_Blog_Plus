package cn.iwannnn.blog.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.iwannnn.common.annotation.Excel;
import cn.iwannnn.common.core.domain.BaseEntity;

/**
 * 博客分类对象 blog_category
 * 
 * @author iwan
 * @date 2021-10-25
 */
public class BlogCategory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分类id */
    private Long categoryId;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String name;

    /** 分类个数 */
    @Excel(name = "分类个数")
    private Long num;

    public void setCategoryId(Long categoryId) 
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() 
    {
        return categoryId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setNum(Long num) 
    {
        this.num = num;
    }

    public Long getNum() 
    {
        return num;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("categoryId", getCategoryId())
            .append("name", getName())
            .append("num", getNum())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
