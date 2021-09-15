package cn.iwannnn.blog.mapper;

import java.util.List;
import cn.iwannnn.blog.domain.BlogUserLike;

/**
 * 用户点赞Mapper接口
 * 
 * @author iwan
 * @date 2021-09-15
 */
public interface BlogUserLikeMapper 
{
    /**
     * 查询用户点赞
     * 
     * @param id 用户点赞主键
     * @return 用户点赞
     */
    public BlogUserLike selectBlogUserLikeById(Long id);

    /**
     * 查询用户点赞列表
     * 
     * @param blogUserLike 用户点赞
     * @return 用户点赞集合
     */
    public List<BlogUserLike> selectBlogUserLikeList(BlogUserLike blogUserLike);

    /**
     * 新增用户点赞
     * 
     * @param blogUserLike 用户点赞
     * @return 结果
     */
    public int insertBlogUserLike(BlogUserLike blogUserLike);

    /**
     * 修改用户点赞
     * 
     * @param blogUserLike 用户点赞
     * @return 结果
     */
    public int updateBlogUserLike(BlogUserLike blogUserLike);

    /**
     * 删除用户点赞
     * 
     * @param id 用户点赞主键
     * @return 结果
     */
    public int deleteBlogUserLikeById(Long id);

    /**
     * 批量删除用户点赞
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBlogUserLikeByIds(Long[] ids);
}
