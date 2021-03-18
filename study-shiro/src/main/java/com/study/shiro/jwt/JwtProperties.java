package com.study.shiro.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Copyright: Copyright (c) 2021
 *
 * <p> Description:
 *
 * @author liushuai
 * @version 1.0.0
 * @createTime 2021年03月18日 22:41
 */
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private String secret;

    private Long expiration;

    private Long nbf;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public Long getNbf() {
        return nbf;
    }

    public void setNbf(Long nbf) {
        this.nbf = nbf;
    }
}
