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
import cn.iwannnn.common.annotation.Log;
import cn.iwannnn.common.core.controller.BaseController;
import cn.iwannnn.common.core.domain.AjaxResult;
import cn.iwannnn.common.enums.BusinessType;
import cn.iwannnn.blog.domain.BlogMusic;
import cn.iwannnn.blog.service.IBlogMusicService;
import cn.iwannnn.common.utils.poi.ExcelUtil;
import cn.iwannnn.common.core.page.TableDataInfo;

/**
 * 博客音乐Controller
 * 
 * @author iwan
 * @date 2021-10-11
 */
@RestController
@RequestMapping("/blog/music")
public class BlogMusicController extends BaseController {
	@Autowired
	private IBlogMusicService blogMusicService;

	/**
	 * 查询博客音乐列表
	 */
	@GetMapping("/list")
	public TableDataInfo list(BlogMusic blogMusic) {
		startPage();
		List<BlogMusic> list = blogMusicService.selectBlogMusicList(blogMusic);
		return getDataTable(list);
	}

	/**
	 * 导出博客音乐列表
	 */
	@PreAuthorize("@ss.hasPermi('blog:music:export')")
	@Log(title = "博客音乐", businessType = BusinessType.EXPORT)
	@GetMapping("/export")
	public AjaxResult export(BlogMusic blogMusic) {
		List<BlogMusic> list = blogMusicService.selectBlogMusicList(blogMusic);
		ExcelUtil<BlogMusic> util = new ExcelUtil<BlogMusic>(BlogMusic.class);
		return util.exportExcel(list, "博客音乐数据");
	}

	/**
	 * 获取博客音乐详细信息
	 */
	@PreAuthorize("@ss.hasPermi('blog:music:query')")
	@GetMapping(value = "/{musicId}")
	public AjaxResult getInfo(@PathVariable("musicId") Long musicId) {
		return AjaxResult.success(blogMusicService.selectBlogMusicByMusicId(musicId));
	}

	/**
	 * 新增博客音乐
	 */
	@PreAuthorize("@ss.hasPermi('blog:music:add')")
	@Log(title = "博客音乐", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@RequestBody BlogMusic blogMusic) {
		return toAjax(blogMusicService.insertBlogMusic(blogMusic));
	}

	/**
	 * 修改博客音乐
	 */
	@PreAuthorize("@ss.hasPermi('blog:music:edit')")
	@Log(title = "博客音乐", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@RequestBody BlogMusic blogMusic) {
		return toAjax(blogMusicService.updateBlogMusic(blogMusic));
	}

	/**
	 * 删除博客音乐
	 */
	@PreAuthorize("@ss.hasPermi('blog:music:remove')")
	@Log(title = "博客音乐", businessType = BusinessType.DELETE)
	@DeleteMapping("/{musicIds}")
	public AjaxResult remove(@PathVariable Long[] musicIds) {
		return toAjax(blogMusicService.deleteBlogMusicByMusicIds(musicIds));
	}

	/**
	 * 获取音乐适配aplayer
	 */
	@GetMapping("/listMusic")
	public AjaxResult listMusic() {
		return AjaxResult.success(blogMusicService.selectMusicForAplayer());
	}

	/**
	 * 获取标题id
	 */
	@GetMapping("/getMusicId")
	public AjaxResult getBlogMusicId() {
		return AjaxResult.success(blogMusicService.selectBlogMusicVos());
	}
}
