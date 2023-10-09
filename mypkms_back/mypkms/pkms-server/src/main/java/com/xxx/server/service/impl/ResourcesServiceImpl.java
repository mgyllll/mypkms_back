package com.xxx.server.service.impl;

import com.xxx.server.UserUtils;
import com.xxx.server.pojo.Resources;
import com.xxx.server.mapper.ResourcesMapper;
import com.xxx.server.pojo.RespBean;
import com.xxx.server.pojo.User;
import com.xxx.server.service.IResourcesService;
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
public class ResourcesServiceImpl extends ServiceImpl<ResourcesMapper, Resources> implements IResourcesService {

    @Autowired
    private ResourcesMapper resourcesMapper;

    @Override
    public List<Resources> getResourcesByUserIdAndCategoryId(String categoryID) {
        return resourcesMapper.getResourcesByUserIdAndCategoryId(UserUtils.getCurrentUser().getUserID(), categoryID);
    }

    @Override
    public RespBean addRes(Resources resources) {
        resources.setUserID(UserUtils.getCurrentUser().getUserID());
        resourcesMapper.addRes(resources);
        if (1==resources.getResult()){
            return RespBean.success("新增成功！", resources);
        }
        return RespBean.error("新增失败！");
    }

    @Override
    public RespBean updateRes(Resources resources) {
        resourcesMapper.updateRes(resources);
        if (1==resources.getResult()){
            return RespBean.success("更新成功！", resources);
        }
        return RespBean.error("更新失败！");
    }

    /**
     * 关键词keyword查询共享资源
     * @param keyword
     * @return
     */
    @Override
    public List<Resources> getResourcesByKeyword(String keyword) {
        return resourcesMapper.getResourcesByKeyword(keyword, UserUtils.getCurrentUser().getUserID());
    }

    /**
     * 根据用户UserID获取资源列表
     * @return
     */
    @Override
    public List<Resources> getResourcesByUserId(String all) {
        return resourcesMapper.getResourcesByUserId(UserUtils.getCurrentUser().getUserID(), all);
    }

    /**
     * 资源审核
     * @param resources
     * @return
     */
    @Override
    public RespBean auditRes(Resources resources) {
        resourcesMapper.auditRes(resources);
        if (1==resources.getResult()){
            return RespBean.success("审核成功！", resources);
        }
        return RespBean.error("审核失败！");
    }

    /**
     * 知识点赞
     * @param resourceID
     */
    @Override
    public void likeRes(Long resourceID) {
        resourcesMapper.likeRes(resourceID, UserUtils.getCurrentUser().getUserID());
    }

    /**
     * 增加阅读量
     * @param resourceID
     */
    @Override
    public void readRes(Long resourceID) {
        resourcesMapper.readRes(resourceID);
    }
}
