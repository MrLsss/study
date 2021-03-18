package com.study.shiro.jwt;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright: Copyright (c) 2021
 *
 * <p> Description:
 *
 * @author liushuai
 * @version 1.0.0
 * @createTime 2021年03月18日 22:43
 */
@Configuration
@EnableConfigurationProperties(value = JwtProperties.class)
public class JwtAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(JwtUtils.class)
    public JwtUtils jwtUtils(JwtProperties jwtProperties) {
        return new JwtUtils(jwtProperties);
    }

}
