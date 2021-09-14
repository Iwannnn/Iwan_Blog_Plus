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
import cn.iwannnn.blog.domain.BlogCategory;
import cn.iwannnn.blog.service.IBlogCategoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 文章分类Controller
 * 
 * @author iwan
 * @date 2021-09-14
 */
@RestController
@RequestMapping("/blog/category")
public class BlogCategoryController extends BaseController
{
    @Autowired
    private IBlogCategoryService blogCategoryService;

    /**
     * 查询文章分类列表
     */
    @PreAuthorize("@ss.hasPermi('blog:category:list')")
    @GetMapping("/list")
    public TableDataInfo list(BlogCategory blogCategory)
    {
        startPage();
        List<BlogCategory> list = blogCategoryService.selectBlogCategoryList(blogCategory);
        return getDataTable(list);
    }

    /**
     * 导出文章分类列表
     */
    @PreAuthorize("@ss.hasPermi('blog:category:export')")
    @Log(title = "文章分类", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BlogCategory blogCategory)
    {
        List<BlogCategory> list = blogCategoryService.selectBlogCategoryList(blogCategory);
        ExcelUtil<BlogCategory> util = new ExcelUtil<BlogCategory>(BlogCategory.class);
        return util.exportExcel(list, "文章分类数据");
    }

    /**
     * 获取文章分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('blog:category:query')")
    @GetMapping(value = "/{categoryId}")
    public AjaxResult getInfo(@PathVariable("categoryId") Long categoryId)
    {
        return AjaxResult.success(blogCategoryService.selectBlogCategoryByCategoryId(categoryId));
    }

    /**
     * 新增文章分类
     */
    @PreAuthorize("@ss.hasPermi('blog:category:add')")
    @Log(title = "文章分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlogCategory blogCategory)
    {
        return toAjax(blogCategoryService.insertBlogCategory(blogCategory));
    }

    /**
     * 修改文章分类
     */
    @PreAuthorize("@ss.hasPermi('blog:category:edit')")
    @Log(title = "文章分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlogCategory blogCategory)
    {
        return toAjax(blogCategoryService.updateBlogCategory(blogCategory));
    }

    /**
     * 删除文章分类
     */
    @PreAuthorize("@ss.hasPermi('blog:category:remove')")
    @Log(title = "文章分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{categoryIds}")
    public AjaxResult remove(@PathVariable Long[] categoryIds)
    {
        return toAjax(blogCategoryService.deleteBlogCategoryByCategoryIds(categoryIds));
    }
}
