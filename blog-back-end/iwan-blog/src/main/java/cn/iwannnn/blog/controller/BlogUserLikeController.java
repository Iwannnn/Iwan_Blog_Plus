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
import cn.iwannnn.blog.domain.BlogUserLike;
import cn.iwannnn.blog.service.IBlogUserLikeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户点赞Controller
 * 
 * @author iwan
 * @date 2021-09-15
 */
@RestController
@RequestMapping("/blog/like")
public class BlogUserLikeController extends BaseController
{
    @Autowired
    private IBlogUserLikeService blogUserLikeService;

    /**
     * 查询用户点赞列表
     */
    @PreAuthorize("@ss.hasPermi('blog:like:list')")
    @GetMapping("/list")
    public TableDataInfo list(BlogUserLike blogUserLike)
    {
        startPage();
        List<BlogUserLike> list = blogUserLikeService.selectBlogUserLikeList(blogUserLike);
        return getDataTable(list);
    }

    /**
     * 导出用户点赞列表
     */
    @PreAuthorize("@ss.hasPermi('blog:like:export')")
    @Log(title = "用户点赞", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BlogUserLike blogUserLike)
    {
        List<BlogUserLike> list = blogUserLikeService.selectBlogUserLikeList(blogUserLike);
        ExcelUtil<BlogUserLike> util = new ExcelUtil<BlogUserLike>(BlogUserLike.class);
        return util.exportExcel(list, "用户点赞数据");
    }

    /**
     * 获取用户点赞详细信息
     */
    @PreAuthorize("@ss.hasPermi('blog:like:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(blogUserLikeService.selectBlogUserLikeById(id));
    }

    /**
     * 新增用户点赞
     */
    @PreAuthorize("@ss.hasPermi('blog:like:add')")
    @Log(title = "用户点赞", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlogUserLike blogUserLike)
    {
        return toAjax(blogUserLikeService.insertBlogUserLike(blogUserLike));
    }

    /**
     * 修改用户点赞
     */
    @PreAuthorize("@ss.hasPermi('blog:like:edit')")
    @Log(title = "用户点赞", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlogUserLike blogUserLike)
    {
        return toAjax(blogUserLikeService.updateBlogUserLike(blogUserLike));
    }

    /**
     * 删除用户点赞
     */
    @PreAuthorize("@ss.hasPermi('blog:like:remove')")
    @Log(title = "用户点赞", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(blogUserLikeService.deleteBlogUserLikeByIds(ids));
    }
}
