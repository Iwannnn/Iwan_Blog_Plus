package cn.iwannnn.blog.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import cn.iwannnn.blog.domain.BlogNotification;
import cn.iwannnn.blog.service.IBlogNotificationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 博客通知Controller
 * 
 * @author ruoyi
 * @date 2021-09-15
 */
@RestController
@RequestMapping("/blog/notification")
public class BlogNotificationController extends BaseController
{
    @Autowired
    private IBlogNotificationService blogNotificationService;

    /**
     * 查询博客通知列表
     */
    @PreAuthorize("@ss.hasPermi('blog:notification:list')")
    @GetMapping("/list")
    public TableDataInfo list(BlogNotification blogNotification)
    {
        startPage();
        List<BlogNotification> list = blogNotificationService.selectBlogNotificationList(blogNotification);
        return getDataTable(list);
    }

    /**
     * 导出博客通知列表
     */
    @PreAuthorize("@ss.hasPermi('blog:notification:export')")
    @Log(title = "博客通知", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BlogNotification blogNotification)
    {
        List<BlogNotification> list = blogNotificationService.selectBlogNotificationList(blogNotification);
        ExcelUtil<BlogNotification> util = new ExcelUtil<BlogNotification>(BlogNotification.class);
        return util.exportExcel(list, "博客通知数据");
    }

    /**
     * 获取博客通知详细信息
     */
    @PreAuthorize("@ss.hasPermi('blog:notification:query')")
    @GetMapping(value = "/{notificationId}")
    public AjaxResult getInfo(@PathVariable("notificationId") Long notificationId)
    {
        return AjaxResult.success(blogNotificationService.selectBlogNotificationByNotificationId(notificationId));
    }

    /**
     * 新增博客通知
     */
    @PreAuthorize("@ss.hasPermi('blog:notification:add')")
    @Log(title = "博客通知", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlogNotification blogNotification)
    {
        return toAjax(blogNotificationService.insertBlogNotification(blogNotification));
    }

    /**
     * 修改博客通知
     */
    @PreAuthorize("@ss.hasPermi('blog:notification:edit')")
    @Log(title = "博客通知", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlogNotification blogNotification)
    {
        return toAjax(blogNotificationService.updateBlogNotification(blogNotification));
    }

    /**
     * 删除博客通知
     */
    @PreAuthorize("@ss.hasPermi('blog:notification:remove')")
    @Log(title = "博客通知", businessType = BusinessType.DELETE)
	@DeleteMapping("/{notificationIds}")
    public AjaxResult remove(@PathVariable Long[] notificationIds)
    {
        return toAjax(blogNotificationService.deleteBlogNotificationByNotificationIds(notificationIds));
    }
}
