package com.xxx.server.controller;


import com.xxx.server.pojo.Resources;
import com.xxx.server.pojo.RespBean;
import com.xxx.server.pojo.Types;
import com.xxx.server.service.ICategoriesService;
import com.xxx.server.service.IResourcesService;
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
 * @since 2021-03-28
 */
@RestController
@RequestMapping("/resources")
public class ResourcesController {

    @Autowired
    private IResourcesService resourcesService;

    @ApiOperation(value = "根据用户UserID和类别CategoryID获取资源列表")
    @GetMapping("/")
    public List<Resources> getResourcesByUserIdAndCategoryId(String categoryID){
        return resourcesService.getResourcesByUserIdAndCategoryId(categoryID);
    }

    @ApiOperation(value = "根据用户UserID获取资源列表")
    @GetMapping("/all")
    public List<Resources> getResourcesByUserId(String all){
        return resourcesService.getResourcesByUserId(all);
    }

    @ApiOperation(value = "关键词keyword查询共享资源")
    @GetMapping("/shareRes")
    public List<Resources> getResourcesByKeyword(String keyword){
        return resourcesService.getResourcesByKeyword(keyword);
    }

    @ApiOperation(value = "添加资源")
    @PostMapping("/addRes")
    public RespBean addRes(@RequestBody Resources resources){
        return resourcesService.addRes(resources);
    }

    @ApiOperation(value = "更新资源")
    @PutMapping("/updateRes")
    public RespBean updateRes(@RequestBody Resources resources){
        return resourcesService.updateRes(resources);
    }

    @ApiOperation(value = "资源审核")
    @PutMapping("/audit")
    public RespBean auditRes(@RequestBody Resources resources){
        return resourcesService.auditRes(resources);
    }

    @ApiOperation(value = "知识点赞")
    @GetMapping("/like")
    public void likeRes(Long resourceID){
        resourcesService.likeRes(resourceID);
    }

    @ApiOperation(value = "增加阅读量")
    @GetMapping("/read")
    public void readRes(Long resourceID){
        resourcesService.readRes(resourceID);
    }
}
