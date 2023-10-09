package com.xxx.server.pojo;


import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 
 * </p>
 *
 * @author luoyong
 * @since 2021-03-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user")
@ApiModel(value="User对象", description="")
public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    @TableId(value = "UserID", type = IdType.AUTO)
    @Excel(name = "用户编号")
    private Integer UserID;

    @ApiModelProperty(value = "用户姓名 ")
    @Excel(name = "用户姓名")
    private String UserName;

    @ApiModelProperty(value = "密码")
    private String Password;

    @Excel(name = "出生日期", width = 20, format = "yyyy-MM-dd")
    private LocalDate BirthDate;

    @Excel(name = "年龄")
    private Integer Age;

    @Excel(name = "性别")
    private String Gender;

    @Excel(name = "手机号", width = 15)
    private String Phone;

    @Excel(name = "邮箱", width = 20)
    private String Email;

    @TableField("QQ")
    @Excel(name = "QQ号", width = 15)
    private String qq;

    @Excel(name = "微信号", width = 15)
    private String WeChat;

    @Excel(name = "地址", width = 40)
    private String Address;

    @Excel(name = "籍贯")
    private String City;

    @Excel(name = "邮编")
    private String Zip;

    private Integer RoleType;

    @ApiModelProperty(value = "员工照片 ")
    private String Photo;

    @ApiModelProperty(value = "员工简介 ")
    @Excel(name = "员工简介", width = 100)
    private String Notes;

    @ApiModelProperty(value = "角色")
    @TableField(exist = false)
    private List<Role> roles;

    @ApiModelProperty(value = "返回结果，存储过程使用")
    @TableField(exist = false)
    private Integer result;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getUsername() {
        return UserName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
