package com.xxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.server.pojo.Files;
import com.xxx.server.pojo.RespBean;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author luoyong
 * @since 2021-03-28
 */
public interface FilesMapper extends BaseMapper<Files> {

    /**
     * 根据ResourceID获取附件列表
     * @param resourceId
     * @return
     */
    List<Files> getFilesByResourceId(Long resourceId);

    /**
     * 新增附件
     * @param files
     */
    void addFile(Files files);
}
