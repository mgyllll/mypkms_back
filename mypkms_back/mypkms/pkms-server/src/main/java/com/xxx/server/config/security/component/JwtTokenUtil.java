package com.xxx.server.config.security.component;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Luo Yong
 * @Date: 2021-03-13 8:20
 * @Version 1.0
 */
@Component
public class JwtTokenUtil {

    private static final String CLAIM_KEY_USERNAME="sub";
    private static final String CLAIM_KEY_CREATED="created";
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;


    /**
     * 根据用户信息生成Token
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails, String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, username);
        System.out.println("11用户名："+username);
        claims.put(CLAIM_KEY_CREATED, new Date());
        System.out.println("根据用户生成Token："+generateToken(claims));
        return generateToken(claims);

    }

    /**
     * 从Token中获取登录用户名
     * @param token
     * @return
     */
    public String getUserNameFromToken(String token) {
        System.out.println("Token是否失效："+isTokenExpired(token));
        System.out.println("从Token中获取登录用户名:"+token);
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
            System.out.println("用户名1:"+username);
        } catch (Exception e) {
            username = null;
        }
        System.out.println("用户名2:"+username);
        return username;
    }

    /**
     * 验证token是否有效
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);
        //return  username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        return !isTokenExpired(token);
    }

    /**
     * 判断token是否可以被刷新
     * @param token
     * @return
     */
    private boolean canRefresh(String token) {
        return !isTokenExpired(token);
    }

    /**
     * 刷新token
     * @param token
     * @return
     */
    public String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }


    /**
     * 判断token是否失效
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        Date expireDate = getExpirateDateFromToken(token);
        return expireDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     * @param token
     * @return
     */
    private Date getExpirateDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }


    /**
     * 从token中获取荷载
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                            .setSigningKey(secret)
                            .parseClaimsJws(token)
                            .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("荷载"+claims);
        return claims;
    }


    /**
     * 根据荷载生成JWT TOKEN
     * @param claims
     * @return
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(generateExirationDate())
                    .signWith(SignatureAlgorithm.HS512, secret)
                    .compact();
    }

    /**
     * 生成Token失效时间
     * @return
     */
    private Date generateExirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }
}
