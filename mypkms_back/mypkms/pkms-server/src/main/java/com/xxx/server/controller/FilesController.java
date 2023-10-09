package com.xxx.server.controller;


import com.xxx.server.pojo.Files;
import com.xxx.server.pojo.Resources;
import com.xxx.server.pojo.RespBean;
import com.xxx.server.pojo.Types;
import com.xxx.server.service.IFilesService;
import com.xxx.server.service.IResourcesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/files")
public class FilesController {

    @Autowired
    private IFilesService filesService;

    @ApiOperation(value = "根据ResourceID获取附件列表")
    @GetMapping("/")
    public List<Files> getFilesByResourceId(Long resourceId){
        return filesService.getFilesByResourceId(resourceId);
    }

    @ApiOperation(value = "获取所有附件列表")
    @GetMapping("/all")
    public List<Files> getAllFiles(){
        return filesService.list();
    }

    @ApiOperation(value = "添加附件")
    @PostMapping("/add")
    public RespBean addFile(@RequestBody Files files){
        return filesService.addFile(files);
    }

    // 不推荐使用
    @ApiOperation(value = "更新附件内容")
    @PutMapping("/")
    public RespBean updateFile(@RequestBody Files files){
        if(filesService.updateById(files)){
            return RespBean.success("更新成功！！");
        }
        return RespBean.error("更新失败！！");
    }

    @ApiOperation(value = "删除附件")
    @DeleteMapping("/{id}")
    public RespBean deleteFile(@PathVariable Integer id){
        if(filesService.removeById(id)){
            return RespBean.success("删除成功！！");
        }
        return RespBean.error("删除失败！！");
    }

    @ApiOperation(value = "批量删除文件类型信息")
    @DeleteMapping("/")
    public RespBean deleteFileByIds(Integer[] ids){
        if(filesService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("批量删除成功！！");
        }
        return RespBean.error("批量删除失败！！");
    }

}
