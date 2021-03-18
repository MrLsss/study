package com.study.shiro.jwt;

import com.study.shiro.entity.SysUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Copyright: Copyright (c) 2021
 *
 * <p> Description:
 *
 * @author liushuai
 * @version 1.0.0
 * @createTime 2021年03月18日 22:45
 */
@Component
public class JwtUtils {

    private final JwtProperties jwtProperties;

    public JwtUtils(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    /**
     * 签发token
     */
    public String sign(SysUser sysUser) {
        final Date now = new Date();
        final Date date = new Date(now.getTime() + jwtProperties.getExpiration());
        JwtBuilder builder = Jwts.builder();

        builder.setId(UUID.randomUUID().toString());

        builder.setIssuedAt(now);

        builder.setExpiration(date);
        // 在什么时间之前，该jwt是不可用的，设置为当前时间，即可生效
        builder.setNotBefore(new Date(now.getTime() + jwtProperties.getNbf()));
        builder.setIssuer(sysUser.getUsername());

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", sysUser.getUsername());
        claims.put("userId", sysUser.getId());

        return builder
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret())
                .compact();
    }

    /**
     * token 是否过期
     *
     * @param token token
     * @return Boolean
     */
    public Boolean isTokenExpired(String token) {
        final Date expiration = getClaimsFromToken(token).getExpiration();
        if (expiration == null) {
            return true;
        }
        else {
            return expiration.before(new Date());
        }
    }

    /**
     * 获取 token 声明
     * @param token token
     * @return Claims {@link Claims}
     */
    public Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(jwtProperties.getSecret())
                    .parseClaimsJws(token)
                    .getBody();
        }
        catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 简单验证 token 是否有效
     * @param token token
     * @return boolean
     */
    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

}
