package cn.iwannnn.blog.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.iwannnn.blog.mapper.BlogMusicMapper;
import cn.iwannnn.blog.domain.BlogMusic;
import cn.iwannnn.blog.service.IBlogMusicService;

/**
 * 博客音乐Service业务层处理
 * 
 * @author iwan
 * @date 2021-09-15
 */
@Service
public class BlogMusicServiceImpl implements IBlogMusicService 
{
    @Autowired
    private BlogMusicMapper blogMusicMapper;

    /**
     * 查询博客音乐
     * 
     * @param musicId 博客音乐主键
     * @return 博客音乐
     */
    @Override
    public BlogMusic selectBlogMusicByMusicId(Long musicId)
    {
        return blogMusicMapper.selectBlogMusicByMusicId(musicId);
    }

    /**
     * 查询博客音乐列表
     * 
     * @param blogMusic 博客音乐
     * @return 博客音乐
     */
    @Override
    public List<BlogMusic> selectBlogMusicList(BlogMusic blogMusic)
    {
        return blogMusicMapper.selectBlogMusicList(blogMusic);
    }

    /**
     * 新增博客音乐
     * 
     * @param blogMusic 博客音乐
     * @return 结果
     */
    @Override
    public int insertBlogMusic(BlogMusic blogMusic)
    {
        blogMusic.setCreateTime(DateUtils.getNowDate());
        return blogMusicMapper.insertBlogMusic(blogMusic);
    }

    /**
     * 修改博客音乐
     * 
     * @param blogMusic 博客音乐
     * @return 结果
     */
    @Override
    public int updateBlogMusic(BlogMusic blogMusic)
    {
        blogMusic.setUpdateTime(DateUtils.getNowDate());
        return blogMusicMapper.updateBlogMusic(blogMusic);
    }

    /**
     * 批量删除博客音乐
     * 
     * @param musicIds 需要删除的博客音乐主键
     * @return 结果
     */
    @Override
    public int deleteBlogMusicByMusicIds(Long[] musicIds)
    {
        return blogMusicMapper.deleteBlogMusicByMusicIds(musicIds);
    }

    /**
     * 删除博客音乐信息
     * 
     * @param musicId 博客音乐主键
     * @return 结果
     */
    @Override
    public int deleteBlogMusicByMusicId(Long musicId)
    {
        return blogMusicMapper.deleteBlogMusicByMusicId(musicId);
    }
}
