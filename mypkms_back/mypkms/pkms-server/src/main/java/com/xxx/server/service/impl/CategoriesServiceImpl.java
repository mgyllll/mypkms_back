package com.xxx.server.service.impl;

import com.xxx.server.UserUtils;
import com.xxx.server.pojo.Categories;
import com.xxx.server.mapper.CategoriesMapper;
import com.xxx.server.pojo.RespBean;
import com.xxx.server.pojo.User;
import com.xxx.server.service.ICategoriesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luoyong
 * @since 2021-03-18
 */
@Service
public class CategoriesServiceImpl extends ServiceImpl<CategoriesMapper, Categories> implements ICategoriesService {

    @Autowired
    private CategoriesMapper categoriesMapper;

    /**
     * 根据用户id查询分类列表
     * @return
     */
    @Override
    public List<Categories> getCategoriesByUserID() {
        return categoriesMapper.getCategoriesByUserID(UserUtils.getCurrentUser().getUserID());
    }

    /**
     * 根据角色获取菜单列表
     * @return
     */
    @Override
    public List<Categories> getCategoriesByRoleType() {
        return categoriesMapper.getCategoriesByRoleType();
    }

    /**
     * 新增类别
     * @param categories
     * @return
     */
    @Override
    public RespBean addCategory(Categories categories) {
        categories.setUserID(UserUtils.getCurrentUser().getUserID());
        categoriesMapper.addCategory(categories);
        if (1==categories.getResult()){
            return RespBean.success("新增成功！", categories);
        }
        return RespBean.error("新增失败！");
    }

    /**
     * 删除类别
     * @param id
     * @return
     */
    @Override
    public RespBean deleteCategory(String id) {
        Categories categories =new Categories();
        categories.setCategoryID(id);
        categories.setUserID(UserUtils.getCurrentUser().getUserID());
        categoriesMapper.deleteCategory(categories);
        if (1==categories.getResult()){
            return RespBean.success("删除成功！！");
        }
        if(2==categories.getResult()){
            return RespBean.success("不存在该类别！");
        }
        return RespBean.success("删除失败！！");
    }

    /**
     * 查询分类列表非树状
     * @return
     */
    @Override
    public List<Categories> getCategories() {
        return categoriesMapper.getCategories(UserUtils.getCurrentUser().getUserID());
    }

    /**
     * 获取知识类别树
     * @return
     */
    @Override
    public List<Categories> getCategoryTree() {
        return categoriesMapper.getCategoryTree(UserUtils.getCurrentUser().getUserID());
    }
}
