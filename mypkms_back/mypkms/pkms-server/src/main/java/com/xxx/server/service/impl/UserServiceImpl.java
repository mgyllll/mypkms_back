package com.xxx.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.server.UserUtils;
import com.xxx.server.config.security.component.JwtTokenUtil;
import com.xxx.server.mapper.RoleMapper;
import com.xxx.server.pojo.Categories;
import com.xxx.server.pojo.RespBean;
import com.xxx.server.mapper.UserMapper;
import com.xxx.server.pojo.Role;
import com.xxx.server.pojo.User;
import com.xxx.server.service.IUserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luoyong
 * @since 2021-03-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * 登录之后返回token
     * @param username
     * @param password
     * @param code
     * @param request
     * @return
     */
    @Override
    public RespBean login(String username, String password, String code, HttpServletRequest request) {
        // System.out.println("客户端用户名："+username);
        String captcha = (String) request.getSession().getAttribute("captcha");
        if (StringUtils.isEmpty(code) || !captcha.equalsIgnoreCase(code)){
            return RespBean.error("验证码输入错误，请重新输入！");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        // System.out.println("111111111111"+userDetails);
        //if (null == userDetails || !passwordEncoder.matches(password, "$2a$10$EKXOFOjn2Bve3t45194KkOdzGzywmeRw3yeekVf.YeURs2Z4.IaHi")){
        //System.out.println("密码匹配是否正确："+passwordEncoder.matches(password, userDetails.getPassword()));
        //System.out.println("用户输入的密码："+password);
        if (null == userDetails || !passwordEncoder.matches(password, userDetails.getPassword())){
            return RespBean.error("用户名或密码不正确");
        }if (!userDetails.isEnabled()){
            return RespBean.error("账号被禁用，请联系管理员");
        }

        //更新security登录用户对象
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication((authenticationToken));


        //生成token
        // System.out.println("111111111111"+userDetails);
        // System.out.println("222222222"+userDetails.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails, username);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return RespBean.success(null,tokenMap);

    }

    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    @Override
    public User getUserByUserName(String username) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
    }

    /**
     * 根据用户id查询角色列表
     * @param userID
     * @return
     */
    @Override
    public List<Role> getRoles(Integer userID) {
        return roleMapper.getRoles(userID);
    }

    /**
     * 获取所有用户
     * @param keywords
     * @return
     */
    @Override
    public List<User> getAllUsers(String keywords) {
        return userMapper.getAllUsers(UserUtils.getCurrentUser().getUserID(), keywords);
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @Override
    public RespBean addUser(User user) {
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        String hashPass = bcryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashPass);
        userMapper.addUser(user);
        if (1==user.getResult()){
//            User user1 = userMapper.getUsers(user.getUserID()).get(0);
//            rabbitTemplate.convertAndSend("mail.welcome", user1);
            return RespBean.success("用户添加成功！");
        }
        return RespBean.error("用户添加失败！");
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @Override
    public RespBean delUser(Integer id) {
        User user = new User();
        user.setUserID(id);
        userMapper.delUser(user);
        if (1==user.getResult()){
            return RespBean.success("用户删除成功！");
        }
        return RespBean.error("用户删除失败！");
    }

    @Override
    public RespBean updateUser(User user) {
        if ("".equals(user.getPassword())){
            user.setPassword("");
        }else {
            BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
            String hashPass = bcryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(hashPass);
        }
        userMapper.updateUser(user);
        if (1==user.getResult()){
            return RespBean.success("用户更新成功！");
        }
        return RespBean.error("用户更新失败！");
    }

    /**
     * 查询用户
     * @param id
     * @return
     */
    @Override
    public List<User> getUsers(Integer id) {
        return userMapper.getUsers(id);
    }


}
