package cn.iwannnn.blog.service;

import java.util.List;
import cn.iwannnn.blog.domain.BlogMusic;

/**
 * 博客音乐Service接口
 * 
 * @author iwan
 * @date 2021-09-17
 */
public interface IBlogMusicService 
{
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
     * 批量删除博客音乐
     * 
     * @param musicIds 需要删除的博客音乐主键集合
     * @return 结果
     */
    public int deleteBlogMusicByMusicIds(Long[] musicIds);

    /**
     * 删除博客音乐信息
     * 
     * @param musicId 博客音乐主键
     * @return 结果
     */
    public int deleteBlogMusicByMusicId(Long musicId);
}
