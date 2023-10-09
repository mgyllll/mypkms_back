package com.xxx.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.xxx.server.UserUtils;
import com.xxx.server.pojo.RespBean;
import com.xxx.server.service.ITypesService;
import com.xxx.server.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: Luo Yong
 * @Date: 2021-04-01 12:03
 * @Version 1.0
 */
@RestController
@RequestMapping("/sys/upload")
public class UploadController {

    @Autowired
    private ITypesService typesService;

    /**
     * 上传附件
     * @param file
     * @param request
     * @throws IOException
     */
    @RequestMapping( value = "/uploadFile")
    @ResponseBody
    public RespBean uploadFile(@RequestParam(value="resID",required=false) long resID, @RequestParam(value="uID",required=false) int uID, @RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) throws IOException {
        //目前这里是写死的本地硬盘路径
        String path = "E:/res/pkms_files/" + uID + "/" +resID + "/";
        // System.out.println(UserUtils.getCurrentUser().getUserID()+"-------------------------");
        //获取文件名称
        String fileName = file.getOriginalFilename();
        //获取文件名后缀
        Calendar currTime = Calendar.getInstance();
        String time = String.valueOf(currTime.get(Calendar.YEAR))+String.valueOf((currTime.get(Calendar.MONTH)+1));
        //获取文件名后缀
        String suffix = fileName.substring(file.getOriginalFilename().lastIndexOf("."));
        suffix = suffix.toLowerCase();
        path = path + "pkms_" + suffix.substring(1);
        fileName = UUID.randomUUID().toString()+suffix;
        File targetFile = new File(path, fileName);
        if(!targetFile.getParentFile().exists()){    //注意，判断父级路径是否存在
            targetFile.getParentFile().mkdirs();
        }
        long size = 0;
        //保存
        try {
            file.transferTo(targetFile);
            size = file.getSize();
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("上传失败！");
        }
        //项目url，这里可以使用常量或者去数据字典获取相应的url前缀；
        String fileUrl="http://localhost:8080";
        //文件获取路径
        fileUrl = fileUrl + request.getContextPath() + "/img/" + fileName;
        JSONObject object = new JSONObject();
        //string
        object.put("fileurl",fileUrl);
        //int
        object.put("filename",fileName);
        object.put("resid", resID);
        object.put("uid", uID);
        return RespBean.success("data", object);
    }

    /**
     * 上传封面
     * @param file
     * @param request
     * @throws IOException
     */
    @RequestMapping( value = "/uploadCover")
    @ResponseBody
    public RespBean uploadCover(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) throws IOException {
        //目前这里是写死的本地硬盘路径
        String path = "E:/res/pkms_cover";
        //获取文件名称
        String fileName = file.getOriginalFilename();
        //获取文件名后缀
        Calendar currTime = Calendar.getInstance();
        String time = String.valueOf(currTime.get(Calendar.YEAR))+String.valueOf((currTime.get(Calendar.MONTH)+1));
        //获取文件名后缀
        String suffix = fileName.substring(file.getOriginalFilename().lastIndexOf("."));
        suffix = suffix.toLowerCase();
        fileName = UUID.randomUUID().toString()+suffix;
        File targetFile = new File(path, fileName);
        if(!targetFile.getParentFile().exists()){    //注意，判断父级路径是否存在
            targetFile.getParentFile().mkdirs();
        }
        long size = 0;
        //保存
        try {
            file.transferTo(targetFile);
            size = file.getSize();
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("上传失败！");
        }
        //项目url，这里可以使用常量或者去数据字典获取相应的url前缀；
        String fileUrl="http://localhost:8080";
        //文件获取路径
        fileUrl = fileUrl + request.getContextPath() + "/img/" + fileName;
        JSONObject object = new JSONObject();
        //string
        object.put("fileurl",fileUrl);
        //int
        object.put("filename",fileName);
        return RespBean.success("data", object);
    }

    /**
     * 上传封面
     * @param file
     * @param request
     * @throws IOException
     */
    @RequestMapping( value = "/uploadAvatar")
    @ResponseBody
    public RespBean uploadAvatar(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) throws IOException {
        //目前这里是写死的本地硬盘路径
        String path = "E:/res/pkms_avatar/";
        //获取文件名称
        String fileName = file.getOriginalFilename();
        //获取文件名后缀
        Calendar currTime = Calendar.getInstance();
        String time = String.valueOf(currTime.get(Calendar.YEAR))+String.valueOf((currTime.get(Calendar.MONTH)+1));
        //获取文件名后缀
        String suffix = fileName.substring(file.getOriginalFilename().lastIndexOf("."));
        suffix = suffix.toLowerCase();
        fileName = UUID.randomUUID().toString()+suffix;
        File targetFile = new File(path, fileName);
        if(!targetFile.getParentFile().exists()){    //注意，判断父级路径是否存在
            targetFile.getParentFile().mkdirs();
        }
        long size = 0;
        //保存
        try {
            file.transferTo(targetFile);
            size = file.getSize();
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("上传失败！");
        }
        //项目url，这里可以使用常量或者去数据字典获取相应的url前缀；
        String fileUrl="http://localhost:8080";
        //文件获取路径
        fileUrl = fileUrl + request.getContextPath() + "/img/" + fileName;
        JSONObject object = new JSONObject();
        //string
        object.put("fileurl",fileUrl);
        //int
        object.put("filename",fileName);
        return RespBean.success("data", object);
    }
}