package cn.iwannnn.blog.mapper;

import java.util.List;
import cn.iwannnn.blog.domain.BlogCategory;
import cn.iwannnn.blog.vo.BlogCategoryVo;

/**
 * 博客分类Mapper接口
 * 
 * @author iwan
 * @date 2021-09-15
 */
public interface BlogCategoryMapper {
	/**
	 * 查询博客分类
	 * 
	 * @param categoryId 博客分类主键
	 * @return 博客分类
	 */
	public BlogCategory selectBlogCategoryByCategoryId(Long categoryId);

	/**
	 * 查询博客分类列表
	 * 
	 * @param blogCategory 博客分类
	 * @return 博客分类集合
	 */
	public List<BlogCategory> selectBlogCategoryList(BlogCategory blogCategory);

	/**
	 * 新增博客分类
	 * 
	 * @param blogCategory 博客分类
	 * @return 结果
	 */
	public int insertBlogCategory(BlogCategory blogCategory);

	/**
	 * 修改博客分类
	 * 
	 * @param blogCategory 博客分类
	 * @return 结果
	 */
	public int updateBlogCategory(BlogCategory blogCategory);

	/**
	 * 删除博客分类
	 * 
	 * @param categoryId 博客分类主键
	 * @return 结果
	 */
	public int deleteBlogCategoryByCategoryId(Long categoryId);

	/**
	 * 批量删除博客分类
	 * 
	 * @param categoryIds 需要删除的数据主键集合
	 * @return 结果
	 */
	public int deleteBlogCategoryByCategoryIds(Long[] categoryIds);

	/**
	 * 获取类别id 名称
	 * 
	 * @return id name
	 */
	public List<BlogCategoryVo> selectBlogCategoryVos();

	/**
	 * 更新博客数量
	 * 
	 * @return void
	 */
	public void updateCategoryNum();

	/**
	 * 重置类型数量
	 * 
	 * @return 0
	 */
	public void clearCategoryNum();
}
