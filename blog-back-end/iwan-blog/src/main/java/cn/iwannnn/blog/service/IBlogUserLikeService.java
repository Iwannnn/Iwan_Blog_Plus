package cn.iwannnn.blog.service;

import java.util.List;
import cn.iwannnn.blog.domain.BlogUserLike;

/**
 * 用户点赞管理Service接口
 * 
 * @author iwan
 * @date 2021-09-14
 */
public interface IBlogUserLikeService 
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
     * 批量删除用户点赞管理
     * 
     * @param ids 需要删除的用户点赞管理主键集合
     * @return 结果
     */
    public int deleteBlogUserLikeByIds(Long[] ids);

    /**
     * 删除用户点赞管理信息
     * 
     * @param id 用户点赞管理主键
     * @return 结果
     */
    public int deleteBlogUserLikeById(Long id);
}
