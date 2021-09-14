package cn.iwannnn.blog.mapper;

import java.util.List;
import cn.iwannnn.blog.domain.BlogUserLike;

/**
 * 用户点赞管理Mapper接口
 * 
 * @author iwan
 * @date 2021-09-14
 */
public interface BlogUserLikeMapper 
{
    /**
     * 查询用户点赞管理
     * 
     * @param id 用户点赞管理主键
     * @return 用户点赞管理
     */
    public BlogUserLike selectBlogUserLikeById(Long id);

    /**
     * 查询用户点赞管理列表
     * 
     * @param blogUserLike 用户点赞管理
     * @return 用户点赞管理集合
     */
    public List<BlogUserLike> selectBlogUserLikeList(BlogUserLike blogUserLike);

    /**
     * 新增用户点赞管理
     * 
     * @param blogUserLike 用户点赞管理
     * @return 结果
     */
    public int insertBlogUserLike(BlogUserLike blogUserLike);

    /**
     * 修改用户点赞管理
     * 
     * @param blogUserLike 用户点赞管理
     * @return 结果
     */
    public int updateBlogUserLike(BlogUserLike blogUserLike);

    /**
     * 删除用户点赞管理
     * 
     * @param id 用户点赞管理主键
     * @return 结果
     */
    public int deleteBlogUserLikeById(Long id);

    /**
     * 批量删除用户点赞管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBlogUserLikeByIds(Long[] ids);
}
