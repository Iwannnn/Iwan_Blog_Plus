package cn.iwannnn.blog.service;

import java.util.List;
import cn.iwannnn.blog.domain.BlogTag;

/**
 * 文章的标签Service接口
 * 
 * @author iwan
 * @date 2021-09-14
 */
public interface IBlogTagService 
{
    /**
     * 查询文章的标签
     * 
     * @param tagId 文章的标签主键
     * @return 文章的标签
     */
    public BlogTag selectBlogTagByTagId(Long tagId);

    /**
     * 查询文章的标签列表
     * 
     * @param blogTag 文章的标签
     * @return 文章的标签集合
     */
    public List<BlogTag> selectBlogTagList(BlogTag blogTag);

    /**
     * 新增文章的标签
     * 
     * @param blogTag 文章的标签
     * @return 结果
     */
    public int insertBlogTag(BlogTag blogTag);

    /**
     * 修改文章的标签
     * 
     * @param blogTag 文章的标签
     * @return 结果
     */
    public int updateBlogTag(BlogTag blogTag);

    /**
     * 批量删除文章的标签
     * 
     * @param tagIds 需要删除的文章的标签主键集合
     * @return 结果
     */
    public int deleteBlogTagByTagIds(Long[] tagIds);

    /**
     * 删除文章的标签信息
     * 
     * @param tagId 文章的标签主键
     * @return 结果
     */
    public int deleteBlogTagByTagId(Long tagId);
}
