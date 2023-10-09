package com.xxx.server.config.security.component;

import com.xxx.server.pojo.Categories;
import com.xxx.server.pojo.Role;
import com.xxx.server.service.ICategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 权限控制
 * 根据请求URL分析所需角色
 * @Author: Luo Yong
 * @Date: 2021-03-28 10:18
 * @Version 1.0
 */
@Component
public class CustomFilter implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private ICategoriesService categoriesService;

    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //获取请求的URL
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        List<Categories> categoriesList = categoriesService.getCategoriesByRoleType();
        for(Categories categories : categoriesList){
            String[] str = categories.getRole().stream().map(Role::getRoleName).toArray(String[]::new);
            return SecurityConfig.createList(str);
        }
        //没匹配的URL默认登录即可访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
