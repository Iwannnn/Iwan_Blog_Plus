package cn.iwannnn.blog.mapper;

import java.util.List;
import cn.iwannnn.blog.domain.BlogMusic;
import cn.iwannnn.blog.vo.BlogMusicVo;

/**
 * 博客音乐Mapper接口
 * 
 * @author iwan
 * @date 2021-09-17
 */
public interface BlogMusicMapper {
	/**
	 * 查询博客音乐
	 * 
	 * @param musicId 博客音乐主键
	 * @return 博客音乐
	 */
	public BlogMusic selectBlogMusicByMusicId(Long musicId);

	/**
	 * 查询博客音乐列表
	 * 
	 * @param blogMusic 博客音乐
	 * @return 博客音乐集合
	 */
	public List<BlogMusic> selectBlogMusicList(BlogMusic blogMusic);

	/**
	 * 新增博客音乐
	 * 
	 * @param blogMusic 博客音乐
	 * @return 结果
	 */
	public int insertBlogMusic(BlogMusic blogMusic);

	/**
	 * 修改博客音乐
	 * 
	 * @param blogMusic 博客音乐
	 * @return 结果
	 */
	public int updateBlogMusic(BlogMusic blogMusic);

	/**
	 * 删除博客音乐
	 * 
	 * @param musicId 博客音乐主键
	 * @return 结果
	 */
	public int deleteBlogMusicByMusicId(Long musicId);

	/**
	 * 批量删除博客音乐
	 * 
	 * @param musicIds 需要删除的数据主键集合
	 * @return 结果
	 */
	public int deleteBlogMusicByMusicIds(Long[] musicIds);

	/**
	 * 获取博客音乐id 名称
	 * 
	 * @return 结果
	 */
	public List<BlogMusicVo> selectBlogMusicVos();
}
