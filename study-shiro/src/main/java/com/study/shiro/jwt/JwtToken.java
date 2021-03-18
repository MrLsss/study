package com.study.shiro.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Copyright: Copyright (c) 2021
 *
 * <p> Description:
 *
 * @author liushuai
 * @version 1.0.0
 * @createTime 2021年03月18日 22:49
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
