package cn.iwannnn.blog.mapper;

import java.util.List;
import cn.iwannnn.blog.domain.BlogNotification;

/**
 * 消息通知Mapper接口
 * 
 * @author iwan
 * @date 2021-09-14
 */
public interface BlogNotificationMapper 
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
     * 删除消息通知
     * 
     * @param notificationId 消息通知主键
     * @return 结果
     */
    public int deleteBlogNotificationByNotificationId(Long notificationId);

    /**
     * 批量删除消息通知
     * 
     * @param notificationIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBlogNotificationByNotificationIds(Long[] notificationIds);
}
