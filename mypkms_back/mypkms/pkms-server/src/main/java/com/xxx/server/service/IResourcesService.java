package com.xxx.server.service;

import com.xxx.server.pojo.Resources;
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
public interface IResourcesService extends IService<Resources> {

    /**
     * 根据用户UserID和类别CategoryID获取资源列表
     * @return
     */
    List<Resources> getResourcesByUserIdAndCategoryId(String categoryID);

    /**
     * 添加资源
     * @param resources
     * @return
     */
    RespBean addRes(Resources resources);

    /**
     * 更新资源
     * @param resources
     * @return
     */
    RespBean updateRes(Resources resources);

    /**
     * 关键词keyword查询共享资源
     * @param keyword
     * @return
     */
    List<Resources> getResourcesByKeyword(String keyword);

    /**
     * 根据用户UserID获取资源列表
     * @return
     */
    List<Resources> getResourcesByUserId(String all);

    /**
     * 资源审核
     * @param resources
     * @return
     */
    RespBean auditRes(Resources resources);

    /**
     * 知识点赞
     * @param resourceID
     */
    void likeRes(Long resourceID);

    /**
     * 增加阅读量
     * @param resourceID
     */
    void readRes(Long resourceID);
}
