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
import cn.iwannnn.blog.domain.BlogMusic;
import cn.iwannnn.blog.service.IBlogMusicService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 文章中的音乐Controller
 * 
 * @author iwan
 * @date 2021-09-14
 */
@RestController
@RequestMapping("/blog/music")
public class BlogMusicController extends BaseController
{
    @Autowired
    private IBlogMusicService blogMusicService;

    /**
     * 查询文章中的音乐列表
     */
    @PreAuthorize("@ss.hasPermi('blog:music:list')")
    @GetMapping("/list")
    public TableDataInfo list(BlogMusic blogMusic)
    {
        startPage();
        List<BlogMusic> list = blogMusicService.selectBlogMusicList(blogMusic);
        return getDataTable(list);
    }

    /**
     * 导出文章中的音乐列表
     */
    @PreAuthorize("@ss.hasPermi('blog:music:export')")
    @Log(title = "文章中的音乐", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BlogMusic blogMusic)
    {
        List<BlogMusic> list = blogMusicService.selectBlogMusicList(blogMusic);
        ExcelUtil<BlogMusic> util = new ExcelUtil<BlogMusic>(BlogMusic.class);
        return util.exportExcel(list, "文章中的音乐数据");
    }

    /**
     * 获取文章中的音乐详细信息
     */
    @PreAuthorize("@ss.hasPermi('blog:music:query')")
    @GetMapping(value = "/{musicId}")
    public AjaxResult getInfo(@PathVariable("musicId") Long musicId)
    {
        return AjaxResult.success(blogMusicService.selectBlogMusicByMusicId(musicId));
    }

    /**
     * 新增文章中的音乐
     */
    @PreAuthorize("@ss.hasPermi('blog:music:add')")
    @Log(title = "文章中的音乐", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlogMusic blogMusic)
    {
        return toAjax(blogMusicService.insertBlogMusic(blogMusic));
    }

    /**
     * 修改文章中的音乐
     */
    @PreAuthorize("@ss.hasPermi('blog:music:edit')")
    @Log(title = "文章中的音乐", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlogMusic blogMusic)
    {
        return toAjax(blogMusicService.updateBlogMusic(blogMusic));
    }

    /**
     * 删除文章中的音乐
     */
    @PreAuthorize("@ss.hasPermi('blog:music:remove')")
    @Log(title = "文章中的音乐", businessType = BusinessType.DELETE)
	@DeleteMapping("/{musicIds}")
    public AjaxResult remove(@PathVariable Long[] musicIds)
    {
        return toAjax(blogMusicService.deleteBlogMusicByMusicIds(musicIds));
    }
}
