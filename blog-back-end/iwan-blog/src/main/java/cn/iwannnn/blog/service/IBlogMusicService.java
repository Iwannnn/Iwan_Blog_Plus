package cn.iwannnn.blog.service;

import java.util.List;
import cn.iwannnn.blog.domain.BlogMusic;

/**
 * 文章中的音乐Service接口
 * 
 * @author iwan
 * @date 2021-09-14
 */
public interface IBlogMusicService 
{
    /**
     * 查询文章中的音乐
     * 
     * @param musicId 文章中的音乐主键
     * @return 文章中的音乐
     */
    public BlogMusic selectBlogMusicByMusicId(Long musicId);

    /**
     * 查询文章中的音乐列表
     * 
     * @param blogMusic 文章中的音乐
     * @return 文章中的音乐集合
     */
    public List<BlogMusic> selectBlogMusicList(BlogMusic blogMusic);

    /**
     * 新增文章中的音乐
     * 
     * @param blogMusic 文章中的音乐
     * @return 结果
     */
    public int insertBlogMusic(BlogMusic blogMusic);

    /**
     * 修改文章中的音乐
     * 
     * @param blogMusic 文章中的音乐
     * @return 结果
     */
    public int updateBlogMusic(BlogMusic blogMusic);

    /**
     * 批量删除文章中的音乐
     * 
     * @param musicIds 需要删除的文章中的音乐主键集合
     * @return 结果
     */
    public int deleteBlogMusicByMusicIds(Long[] musicIds);

    /**
     * 删除文章中的音乐信息
     * 
     * @param musicId 文章中的音乐主键
     * @return 结果
     */
    public int deleteBlogMusicByMusicId(Long musicId);
}
