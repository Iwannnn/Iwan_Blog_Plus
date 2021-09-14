package cn.iwannnn.blog.service;

import java.util.List;
import cn.iwannnn.blog.domain.BlogNotification;

/**
 * 消息通知Service接口
 * 
 * @author iwan
 * @date 2021-09-14
 */
public interface IBlogNotificationService 
{
    /**
     * 查询消息通知
     * 
     * @param notificationId 消息通知主键
     * @return 消息通知
     */
    public BlogNotification selectBlogNotificationByNotificationId(Long notificationId);

    /**
     * 查询消息通知列表
     * 
     * @param blogNotification 消息通知
     * @return 消息通知集合
     */
    public List<BlogNotification> selectBlogNotificationList(BlogNotification blogNotification);

    /**
     * 新增消息通知
     * 
     * @param blogNotification 消息通知
     * @return 结果
     */
    public int insertBlogNotification(BlogNotification blogNotification);

    /**
     * 修改消息通知
     * 
     * @param blogNotification 消息通知
     * @return 结果
     */
    public int updateBlogNotification(BlogNotification blogNotification);

    /**
     * 批量删除消息通知
     * 
     * @param notificationIds 需要删除的消息通知主键集合
     * @return 结果
     */
    public int deleteBlogNotificationByNotificationIds(Long[] notificationIds);

    /**
     * 删除消息通知信息
     * 
     * @param notificationId 消息通知主键
     * @return 结果
     */
    public int deleteBlogNotificationByNotificationId(Long notificationId);
}
