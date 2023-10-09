package com.xxx.server.service;

import com.xxx.server.pojo.Areas;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoyong
 * @since 2021-03-28
 */
public interface IAreasService extends IService<Areas> {

    /**
     * 获取地区列表
     * @return
     */
    List<Areas> getAreas();
}
