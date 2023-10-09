package com.xxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.server.pojo.Areas;
import com.xxx.server.pojo.Categories;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author luoyong
 * @since 2021-03-28
 */
public interface AreasMapper extends BaseMapper<Areas> {


    /**
     * 获取地区列表
     * @return
     */
    List<Areas> getAreas();
}
