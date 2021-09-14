package cn.iwannnn.blog.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 消息通知对象 blog_notification
 * 
 * @author iwan
 * @date 2021-09-14
 */
public class BlogNotification extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 通知id */
    private Long notificationId;

    /** 被通知用户id */
    @Excel(name = "被通知用户id")
    private Long userId;

    /** 通知内容 */
    @Excel(name = "通知内容")
    private String content;

    public void setNotificationId(Long notificationId) 
    {
        this.notificationId = notificationId;
    }

    public Long getNotificationId() 
    {
        return notificationId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("notificationId", getNotificationId())
            .append("userId", getUserId())
            .append("content", getContent())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
