package cn.iwannnn.blog.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.iwannnn.blog.mapper.BlogArticleTagMapper;
import cn.iwannnn.blog.domain.BlogArticleTag;
import cn.iwannnn.blog.service.IBlogArticleTagService;

/**
 * 文章标签Service业务层处理
 * 
 * @author iwan
 * @date 2021-09-15
 */
@Service
public class BlogArticleTagServiceImpl implements IBlogArticleTagService 
{
    @Autowired
    private BlogArticleTagMapper blogArticleTagMapper;

    /**
     * 查询文章标签
     * 
     * @param id 文章标签主键
     * @return 文章标签
     */
    @Override
    public BlogArticleTag selectBlogArticleTagById(Long id)
    {
        return blogArticleTagMapper.selectBlogArticleTagById(id);
    }

    /**
     * 查询文章标签列表
     * 
     * @param blogArticleTag 文章标签
     * @return 文章标签
     */
    @Override
    public List<BlogArticleTag> selectBlogArticleTagList(BlogArticleTag blogArticleTag)
    {
        return blogArticleTagMapper.selectBlogArticleTagList(blogArticleTag);
    }

    /**
     * 新增文章标签
     * 
     * @param blogArticleTag 文章标签
     * @return 结果
     */
    @Override
    public int insertBlogArticleTag(BlogArticleTag blogArticleTag)
    {
        blogArticleTag.setCreateTime(DateUtils.getNowDate());
        return blogArticleTagMapper.insertBlogArticleTag(blogArticleTag);
    }

    /**
     * 修改文章标签
     * 
     * @param blogArticleTag 文章标签
     * @return 结果
     */
    @Override
    public int updateBlogArticleTag(BlogArticleTag blogArticleTag)
    {
        blogArticleTag.setUpdateTime(DateUtils.getNowDate());
        return blogArticleTagMapper.updateBlogArticleTag(blogArticleTag);
    }

    /**
     * 批量删除文章标签
     * 
     * @param ids 需要删除的文章标签主键
     * @return 结果
     */
    @Override
    public int deleteBlogArticleTagByIds(Long[] ids)
    {
        return blogArticleTagMapper.deleteBlogArticleTagByIds(ids);
    }

    /**
     * 删除文章标签信息
     * 
     * @param id 文章标签主键
     * @return 结果
     */
    @Override
    public int deleteBlogArticleTagById(Long id)
    {
        return blogArticleTagMapper.deleteBlogArticleTagById(id);
    }
}
