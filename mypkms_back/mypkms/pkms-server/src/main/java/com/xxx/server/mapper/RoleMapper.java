package com.xxx.server.mapper;

import com.xxx.server.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author luoyong
 * @since 2021-03-28
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户id查询角色列表
     * @param userID
     * @return
     */
    List<Role> getRoles(Integer userID);
}
