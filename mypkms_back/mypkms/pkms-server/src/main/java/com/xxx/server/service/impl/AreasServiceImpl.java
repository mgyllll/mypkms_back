package com.xxx.server.service.impl;

import com.xxx.server.mapper.CategoriesMapper;
import com.xxx.server.pojo.Areas;
import com.xxx.server.mapper.AreasMapper;
import com.xxx.server.pojo.Categories;
import com.xxx.server.pojo.User;
import com.xxx.server.service.IAreasService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class AreasServiceImpl extends ServiceImpl<AreasMapper, Areas> implements IAreasService {

    @Autowired
    private AreasMapper areasMapper;

    /**
     * 获取地区列表
     * @return
     */
    @Override
    public List<Areas> getAreas() {
        return areasMapper.getAreas();
    }
}
