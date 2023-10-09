package com.xxx.server.service.impl;

import com.xxx.server.pojo.Files;
import com.xxx.server.mapper.FilesMapper;
import com.xxx.server.pojo.RespBean;
import com.xxx.server.service.IFilesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luoyong
 * @since 2021-03-28
 */
@Service
public class FilesServiceImpl extends ServiceImpl<FilesMapper, Files> implements IFilesService {

    @Autowired
    private FilesMapper filesMapper;

    @Override
    public List<Files> getFilesByResourceId(Long resourceId) {
        return filesMapper.getFilesByResourceId(resourceId);
    }

    @Override
    public RespBean addFile(Files files) {
        filesMapper.addFile(files);
        if(1==files.getResult()){
            return RespBean.success("附件上传成功！", files);
        }else if(-1==files.getResult()){
            return RespBean.error("不支持该文件类型！");
        }
        return RespBean.error("附件上传失败！");
    }
}
