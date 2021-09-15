package cn.iwannnn.blog.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 文章标签对象 blog_article_tag
 * 
 * @author iwan
 * @date 2021-09-15
 */
public class BlogArticleTag extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文章和标签关联 */
    private Long id;

    /** 文章id */
    @Excel(name = "文章id")
    private Long article;

    /** 标签id */
    @Excel(name = "标签id")
    private Long tag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setArticle(Long article) 
    {
        this.article = article;
    }

    public Long getArticle() 
    {
        return article;
    }
    public void setTag(Long tag) 
    {
        this.tag = tag;
    }

    public Long getTag() 
    {
        return tag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("article", getArticle())
            .append("tag", getTag())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
