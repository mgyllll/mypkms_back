package com.xxx.server.service;

import com.xxx.server.pojo.Files;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.server.pojo.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoyong
 * @since 2021-03-28
 */
public interface IFilesService extends IService<Files> {

    /**
     * 根据ResourceID获取附件列表
     * @param resourceId
     * @return
     */
    List<Files> getFilesByResourceId(Long resourceId);

    /**
     * 添加附件
     * @param files
     * @return
     */
    RespBean addFile(Files files);
}
