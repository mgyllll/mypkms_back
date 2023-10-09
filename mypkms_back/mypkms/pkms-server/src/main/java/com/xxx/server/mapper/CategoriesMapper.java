package com.xxx.server.mapper;

import com.xxx.server.pojo.Categories;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.server.pojo.RespBean;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author luoyong
 * @since 2021-03-18
 */
public interface CategoriesMapper extends BaseMapper<Categories> {


    /**
     * 根据角色获取菜单列表
     *
     * @return
     */
    List<Categories> getCategoriesByRoleType();

    /**
     * 根据用户id查询分类列表
     * @param userID
     * @return
     */
    List<Categories> getCategoriesByUserID(Integer userID);


    /**
     * 新增类别
     * @param categories
     */
    void addCategory(Categories categories);

    /**
     * 删除类别
     * @param categories
     */
    void deleteCategory(Categories categories);

    /**
     * 查询分类列表非树状
     * @param userID
     * @return
     */
    List<Categories> getCategories(Integer userID);

    /**
     * 获取知识类别树
     * @param userID
     * @return
     */
    List<Categories> getCategoryTree(Integer userID);
}
