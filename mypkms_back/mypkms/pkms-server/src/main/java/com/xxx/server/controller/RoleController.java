package com.xxx.server.controller;


import com.xxx.server.pojo.Resources;
import com.xxx.server.pojo.Role;
import com.xxx.server.service.IResourcesService;
import com.xxx.server.service.IRoleService;
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
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @ApiOperation(value = "获取角色列表")
    @GetMapping("/")
    public List<Role> getRoles(){
        return roleService.list();
    }

}
