package com.xxx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.server.pojo.RespBean;
import com.xxx.server.pojo.Role;
import com.xxx.server.pojo.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoyong
 * @since 2021-03-12
 */
public interface IUserService extends IService<User> {
    /**
     *登录之后返回token
     *
     * @param username
     * @param password
     * @param code
     * @param request
     * @return
     */
    RespBean login(String username, String password, String code, HttpServletRequest request);

    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    User getUserByUserName(String username);

    /**
     * 根据用户id查询角色列表
     * @param userID
     * @return
     */
    List<Role> getRoles(Integer userID);


    /**
     * 获取所有用户
     * @param keywords
     * @return
     */
    List<User> getAllUsers(String keywords);

    /**
     * 添加用户
     * @param user
     * @return
     */
    RespBean addUser(User user);

    /**
     * 删除用户
     * @param id
     * @return
     */
    RespBean delUser(Integer id);

    /**
     * 更新用户
     * @param user
     * @return
     */
    RespBean updateUser(User user);

    /**
     * 查询用户
     * @param id
     * @return
     */
    List<User> getUsers(Integer id);
}
