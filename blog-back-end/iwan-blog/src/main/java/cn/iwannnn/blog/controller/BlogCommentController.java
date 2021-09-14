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
import cn.iwannnn.blog.domain.BlogComment;
import cn.iwannnn.blog.service.IBlogCommentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 文章评论Controller
 * 
 * @author iwan
 * @date 2021-09-14
 */
@RestController
@RequestMapping("/blog/comment")
public class BlogCommentController extends BaseController
{
    @Autowired
    private IBlogCommentService blogCommentService;

    /**
     * 查询文章评论列表
     */
    @PreAuthorize("@ss.hasPermi('blog:comment:list')")
    @GetMapping("/list")
    public TableDataInfo list(BlogComment blogComment)
    {
        startPage();
        List<BlogComment> list = blogCommentService.selectBlogCommentList(blogComment);
        return getDataTable(list);
    }

    /**
     * 导出文章评论列表
     */
    @PreAuthorize("@ss.hasPermi('blog:comment:export')")
    @Log(title = "文章评论", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BlogComment blogComment)
    {
        List<BlogComment> list = blogCommentService.selectBlogCommentList(blogComment);
        ExcelUtil<BlogComment> util = new ExcelUtil<BlogComment>(BlogComment.class);
        return util.exportExcel(list, "文章评论数据");
    }

    /**
     * 获取文章评论详细信息
     */
    @PreAuthorize("@ss.hasPermi('blog:comment:query')")
    @GetMapping(value = "/{commentId}")
    public AjaxResult getInfo(@PathVariable("commentId") Long commentId)
    {
        return AjaxResult.success(blogCommentService.selectBlogCommentByCommentId(commentId));
    }

    /**
     * 新增文章评论
     */
    @PreAuthorize("@ss.hasPermi('blog:comment:add')")
    @Log(title = "文章评论", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlogComment blogComment)
    {
        return toAjax(blogCommentService.insertBlogComment(blogComment));
    }

    /**
     * 修改文章评论
     */
    @PreAuthorize("@ss.hasPermi('blog:comment:edit')")
    @Log(title = "文章评论", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlogComment blogComment)
    {
        return toAjax(blogCommentService.updateBlogComment(blogComment));
    }

    /**
     * 删除文章评论
     */
    @PreAuthorize("@ss.hasPermi('blog:comment:remove')")
    @Log(title = "文章评论", businessType = BusinessType.DELETE)
	@DeleteMapping("/{commentIds}")
    public AjaxResult remove(@PathVariable Long[] commentIds)
    {
        return toAjax(blogCommentService.deleteBlogCommentByCommentIds(commentIds));
    }
}
