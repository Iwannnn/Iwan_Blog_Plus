package cn.iwannnn.blog.service;

import java.util.List;
import cn.iwannnn.blog.domain.BlogNotification;

/**
 * 博客通知Service接口
 * 
 * @author ruoyi
 * @date 2021-09-15
 */
public interface IBlogNotificationService 
{
    /**
     * 查询博客通知
     * 
     * @param notificationId 博客通知主键
     * @return 博客通知
     */
    public BlogNotification selectBlogNotificationByNotificationId(Long notificationId);

    /**
     * 查询博客通知列表
     * 
     * @param blogNotification 博客通知
     * @return 博客通知集合
     */
    public List<BlogNotification> selectBlogNotificationList(BlogNotification blogNotification);

    /**
     * 新增博客通知
     * 
     * @param blogNotification 博客通知
     * @return 结果
     */
    public int insertBlogNotification(BlogNotification blogNotification);

    /**
     * 修改博客通知
     * 
     * @param blogNotification 博客通知
     * @return 结果
     */
    public int updateBlogNotification(BlogNotification blogNotification);

    /**
     * 批量删除博客通知
     * 
     * @param notificationIds 需要删除的博客通知主键集合
     * @return 结果
     */
    public int deleteBlogNotificationByNotificationIds(Long[] notificationIds);

    /**
     * 删除博客通知信息
     * 
     * @param notificationId 博客通知主键
     * @return 结果
     */
    public int deleteBlogNotificationByNotificationId(Long notificationId);
}
