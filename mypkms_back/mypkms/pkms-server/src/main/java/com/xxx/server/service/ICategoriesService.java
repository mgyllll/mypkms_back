package com.xxx.server.service;

import com.xxx.server.pojo.Categories;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.server.pojo.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoyong
 * @since 2021-03-18
 */
public interface ICategoriesService extends IService<Categories> {


    /**
     * 根据用户id查询分类列表
     * @return
     */
    List<Categories> getCategoriesByUserID();

    /**
     * 根据角色获取菜单列表
     * @return
     */
    List<Categories> getCategoriesByRoleType();

    /**
     * 新增类别
     * @param categories
     * @return
     */
    RespBean addCategory(Categories categories);

    /**
     * 删除类别
     * @param id
     * @return
     */
    RespBean deleteCategory(String id);

    /**
     * 查询分类列表非树状
     * @return
     */
    List<Categories> getCategories();

    /**
     * 获取知识类别树
     * @return
     */
    List<Categories> getCategoryTree();
}
