package com.xxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.server.pojo.Resources;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author luoyong
 * @since 2021-03-28
 */
public interface ResourcesMapper extends BaseMapper<Resources> {

    /**
     * 根据用户UserID和类别CategoryID获取资源列表
     * @return
     */
    List<Resources> getResourcesByUserIdAndCategoryId(Integer userID, String categoryID);

    /**
     * 新增资源
     * @param resources
     */
    void addRes(Resources resources);

    /**
     * 更新资源
     * @param resources
     */
    void updateRes(Resources resources);

    /**
     * 关键词keyword查询共享资源
     * @param keyword
     * @return
     */
    List<Resources> getResourcesByKeyword(String keyword, Integer userId);

    /**
     * 根据用户UserID获取资源列表
     * @param userID
     * @return
     */
    List<Resources> getResourcesByUserId(Integer userID, String all);

    /**
     * 资源审核
     * @param resources
     */
    void auditRes(Resources resources);

    /**
     * 知识点赞
     * @param resourceID
     * @param userID
     */
    void likeRes(Long resourceID, Integer userID);

    /**
     * 增加阅读量
     * @param resourceID
     */
    void readRes(Long resourceID);
}
