package cn.iwannnn.blog.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.iwannnn.blog.mapper.BlogUserMapper;
import cn.iwannnn.blog.domain.BlogUser;
import cn.iwannnn.blog.service.IBlogUserService;

/**
 * 博客系统用户Service业务层处理
 * 
 * @author iwan
 * @date 2021-09-14
 */
@Service
public class BlogUserServiceImpl implements IBlogUserService 
{
    @Autowired
    private BlogUserMapper blogUserMapper;

    /**
     * 查询博客系统用户
     * 
     * @param userId 博客系统用户主键
     * @return 博客系统用户
     */
    @Override
    public BlogUser selectBlogUserByUserId(Long userId)
    {
        return blogUserMapper.selectBlogUserByUserId(userId);
    }

    /**
     * 查询博客系统用户列表
     * 
     * @param blogUser 博客系统用户
     * @return 博客系统用户
     */
    @Override
    public List<BlogUser> selectBlogUserList(BlogUser blogUser)
    {
        return blogUserMapper.selectBlogUserList(blogUser);
    }

    /**
     * 新增博客系统用户
     * 
     * @param blogUser 博客系统用户
     * @return 结果
     */
    @Override
    public int insertBlogUser(BlogUser blogUser)
    {
        blogUser.setCreateTime(DateUtils.getNowDate());
        return blogUserMapper.insertBlogUser(blogUser);
    }

    /**
     * 修改博客系统用户
     * 
     * @param blogUser 博客系统用户
     * @return 结果
     */
    @Override
    public int updateBlogUser(BlogUser blogUser)
    {
        blogUser.setUpdateTime(DateUtils.getNowDate());
        return blogUserMapper.updateBlogUser(blogUser);
    }

    /**
     * 批量删除博客系统用户
     * 
     * @param userIds 需要删除的博客系统用户主键
     * @return 结果
     */
    @Override
    public int deleteBlogUserByUserIds(Long[] userIds)
    {
        return blogUserMapper.deleteBlogUserByUserIds(userIds);
    }

    /**
     * 删除博客系统用户信息
     * 
     * @param userId 博客系统用户主键
     * @return 结果
     */
    @Override
    public int deleteBlogUserByUserId(Long userId)
    {
        return blogUserMapper.deleteBlogUserByUserId(userId);
    }
}
