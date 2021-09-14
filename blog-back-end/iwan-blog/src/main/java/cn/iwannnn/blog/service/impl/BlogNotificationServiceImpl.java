package cn.iwannnn.blog.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.iwannnn.blog.mapper.BlogNotificationMapper;
import cn.iwannnn.blog.domain.BlogNotification;
import cn.iwannnn.blog.service.IBlogNotificationService;

/**
 * 消息通知Service业务层处理
 * 
 * @author iwan
 * @date 2021-09-14
 */
@Service
public class BlogNotificationServiceImpl implements IBlogNotificationService 
{
    @Autowired
    private BlogNotificationMapper blogNotificationMapper;

    /**
     * 查询消息通知
     * 
     * @param notificationId 消息通知主键
     * @return 消息通知
     */
    @Override
    public BlogNotification selectBlogNotificationByNotificationId(Long notificationId)
    {
        return blogNotificationMapper.selectBlogNotificationByNotificationId(notificationId);
    }

    /**
     * 查询消息通知列表
     * 
     * @param blogNotification 消息通知
     * @return 消息通知
     */
    @Override
    public List<BlogNotification> selectBlogNotificationList(BlogNotification blogNotification)
    {
        return blogNotificationMapper.selectBlogNotificationList(blogNotification);
    }

    /**
     * 新增消息通知
     * 
     * @param blogNotification 消息通知
     * @return 结果
     */
    @Override
    public int insertBlogNotification(BlogNotification blogNotification)
    {
        blogNotification.setCreateTime(DateUtils.getNowDate());
        return blogNotificationMapper.insertBlogNotification(blogNotification);
    }

    /**
     * 修改消息通知
     * 
     * @param blogNotification 消息通知
     * @return 结果
     */
    @Override
    public int updateBlogNotification(BlogNotification blogNotification)
    {
        blogNotification.setUpdateTime(DateUtils.getNowDate());
        return blogNotificationMapper.updateBlogNotification(blogNotification);
    }

    /**
     * 批量删除消息通知
     * 
     * @param notificationIds 需要删除的消息通知主键
     * @return 结果
     */
    @Override
    public int deleteBlogNotificationByNotificationIds(Long[] notificationIds)
    {
        return blogNotificationMapper.deleteBlogNotificationByNotificationIds(notificationIds);
    }

    /**
     * 删除消息通知信息
     * 
     * @param notificationId 消息通知主键
     * @return 结果
     */
    @Override
    public int deleteBlogNotificationByNotificationId(Long notificationId)
    {
        return blogNotificationMapper.deleteBlogNotificationByNotificationId(notificationId);
    }
}
