package com.xxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.server.pojo.Categories;
import com.xxx.server.pojo.RespBean;
import com.xxx.server.pojo.User;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author luoyong
 * @since 2021-03-12
 */
public interface UserMapper extends BaseMapper<User> {


    /**
     *获取所有用户（带条件查询keyword）
     * @param userID
     * @param keywords
     * @return
     */
    List<User> getAllUsers(Integer userID, String keywords);

    /**
     * 添加用户
     * @param user
     * @return
     */
    void addUser(User user);

    /**
     * 删除用户
     * @param user
     * @return
     */
    void delUser(User user);

    /**
     * 更新用户
     * @param user
     */
    void updateUser(User user);

    /**
     * 查询用户
     * @param id
     * @return
     */
    List<User> getUsers(Integer id);
}
