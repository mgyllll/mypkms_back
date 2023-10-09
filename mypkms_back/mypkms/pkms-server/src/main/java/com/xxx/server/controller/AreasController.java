package com.xxx.server.controller;


import com.xxx.server.pojo.Areas;
import com.xxx.server.pojo.Categories;
import com.xxx.server.pojo.Types;
import com.xxx.server.service.IAreasService;
import com.xxx.server.service.ITypesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/areas")
public class AreasController {

    @Autowired
    private IAreasService areasService;

    @ApiOperation(value = "获取所有地区信息")
    @GetMapping("/")
    public List<Areas> getAllAreas(){
        return areasService.list();
    }

    @ApiOperation(value = "查询地区列表")
    @GetMapping("/areas")
    public List<Areas> getAreas(){
        return areasService.getAreas();
    }

}
