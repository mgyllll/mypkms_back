package com.xxx.server.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.xxx.server.pojo.RespBean;
import com.xxx.server.pojo.Types;
import com.xxx.server.pojo.User;
import com.xxx.server.service.ITypesService;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
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
@RequestMapping("/system/config/types")
public class TypesController {

    @Autowired
    private ITypesService typesService;

    @ApiOperation(value = "获取所有文件类型信息")
    @GetMapping("/")
    public List<Types> getAllTypes(){
        return typesService.list();
    }

    @ApiOperation(value = "添加文件类型信息")
    @PostMapping("/")
    public RespBean addType(@RequestBody Types types){
        if(typesService.save(types)){
            return RespBean.success("添加成功！！");
        }
        return RespBean.error("添加失败！！");
    }

    @ApiOperation(value = "更新文件类型信息")
    @PutMapping("/")
    public RespBean updateType(@RequestBody Types types){
        if(typesService.updateById(types)){
            return RespBean.success("更新成功！！");
        }
        return RespBean.error("更新失败！！");
    }

    @ApiOperation(value = "删除文件类型信息")
    @DeleteMapping("/{id}")
    public RespBean deleteType(@PathVariable Integer id){
        if(typesService.removeById(id)){
            return RespBean.success("删除成功！！");
        }
        return RespBean.error("删除失败！！");
    }

    @ApiOperation(value = "批量删除文件类型信息")
    @DeleteMapping("/")
    public RespBean deleteTypeByIds(Integer[] ids){
        if(typesService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("批量删除成功！！");
        }
        return RespBean.error("批量删除失败！！");
    }

    @ApiOperation(value = "导出数据（文件类型表）")
    @GetMapping("/export")
    public void exportUsers(HttpServletResponse response) {
        List<Types> list = typesService.list();
        ExportParams params = new ExportParams("文件类型表","文件类型表", ExcelType.HSSF);
        System.out.println(list);
        Workbook workbook = ExcelExportUtil.exportExcel(params, Types.class, list);
        ServletOutputStream out = null;
        try {
            // 流形式
            response.setHeader("content-type","application/octet-stream");
            // 防止中文乱码
            response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode("文件类型表.xls","UTF-8"));
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
