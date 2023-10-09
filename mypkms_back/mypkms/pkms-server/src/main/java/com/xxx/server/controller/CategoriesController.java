package com.xxx.server.controller;


import com.xxx.server.pojo.Categories;
import com.xxx.server.pojo.RespBean;
import com.xxx.server.service.ICategoriesService;
import com.xxx.server.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author luoyong
 * @since 2021-03-18
 */
@RestController
@RequestMapping("/categories")
public class CategoriesController {

    @Autowired
    private ICategoriesService categoriesService;

    @ApiOperation(value = "查询分类列表")
    @GetMapping("/categories")
    public List<Categories> getCategoriesByUserID(){
        return categoriesService.getCategoriesByUserID();
    }

    @ApiOperation(value = "查询分类列表非树状")
    @GetMapping("/data")
    public List<Categories> getCategories(){
        return categoriesService.getCategories();
    }


    @ApiOperation(value = "新增类别")
    @PostMapping("/addCategory")
    public RespBean addCategory(@RequestBody Categories categories){
        return categoriesService.addCategory(categories);
    }

    @ApiOperation(value = "删除类别")
    @DeleteMapping("/{id}")
    public RespBean deleteCategory(@PathVariable String id){
        return categoriesService.deleteCategory(id);
    }

    @ApiOperation(value = "获取知识类别树")
    @GetMapping("/tree")
    public List<Categories> getCategoryTree(){
        return categoriesService.getCategoryTree();
    }
}
