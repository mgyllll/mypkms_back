package com.xxx.server.controller;



import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.xxx.server.pojo.RespBean;
import com.xxx.server.pojo.User;
import com.xxx.server.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author luoyong
 * @since 2021-03-12
 */
@RestController
@RequestMapping("/system/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "获取所有用户")
    @GetMapping("/")
    public List<User> getAllUsers(String keywords){
        return userService.getAllUsers(keywords);
    }

    @ApiOperation(value = "添加用户")
    @PostMapping("/addUser")
    public RespBean addUser(@RequestBody User user){
        return  userService.addUser(user);
    }

    @ApiOperation(value = "更新用户")
    @PostMapping(value = "/", produces = "application/json;charset=UTF-8")
    public RespBean updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/{id}")
    public RespBean delUser(@PathVariable Integer id){
        return  userService.delUser(id);
    }

    @ApiOperation(value = "导出用户数据")
    @GetMapping("/export")
    public void exportUsers(HttpServletResponse response) {
        List<User> list = userService.getUsers(null);
        ExportParams params = new ExportParams("用户表","用户表", ExcelType.HSSF);
        System.out.println(list);
        Workbook workbook = ExcelExportUtil.exportExcel(params, User.class, list);
        ServletOutputStream out = null;
        try {
            // 流形式
            response.setHeader("content-type","application/octet-stream");
            // 防止中文乱码
            response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode("用户表.xls","UTF-8"));
            out = response.getOutputStream();
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null!=out){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
