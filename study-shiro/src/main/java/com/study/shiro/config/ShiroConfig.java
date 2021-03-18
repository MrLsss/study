package com.study.shiro.config;

import com.study.shiro.jwt.JwtFilter;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Copyright: Copyright (c) 2021
 *
 * <p> Description:
 *
 * @author liushuai
 * @version 1.0.0
 * @createTime 2021年03月18日 21:38
 */
@Configuration
public class ShiroConfig {


    /**
     * application -> subject -> securityManager -> realm
     */

    /**
     * 自定义realm
     */
    @Bean("myRealm")
    public NoJwtRealm myRealm() {
        return new NoJwtRealm();
    }

    /**
     * 自定义的jwt realm
     */
    @Bean("myJwtRealm")
    public JwtRealm jwtRealm() {
        return new JwtRealm();
    }

    /**
     * SecurityManager 安全管理器
     * 负责和其他组件进行交互
     */
    @Bean("securityManager")
//    public DefaultWebSecurityManager securityManager(@Qualifier("myRealm") NoJwtRealm realm) {
    public DefaultWebSecurityManager securityManager(@Qualifier("myJwtRealm") JwtRealm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        /*
         * 关闭shiro自带的session，详情见文档
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;
    }

    @Bean("jwtFilter")
    public JwtFilter jwtFilter() {
        return new JwtFilter();
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager ) {
        ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
        filter.setSecurityManager(securityManager);
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 路径匹配的顺序就是put进去的顺序（最先匹配原则）
        filterChainDefinitionMap.put("/sys/auth/login", "anon");
        filterChainDefinitionMap.put("/sys/auth/jwt/login", "anon");
        filterChainDefinitionMap.put("/", "anon");

        // ============== 用于shiro自带token的情况
//        filterChainDefinitionMap.put("/**", "authc");
//        filter.setLoginUrl("/sys/auth/login");
//        filter.setSuccessUrl("/sys/auth/success");
//        filter.setUnauthorizedUrl("/sys/auth/error");
        // ============== 用于shiro自带token的情况

        // ============== 用于自定义token的情况
        // 添加自己的过滤器并且取名为jwt
        Map<String, Filter> filterMap = new HashMap<String, Filter>(1);
        filterMap.put("jwt", new JwtFilter());
        filter.setFilters(filterMap);
        filterChainDefinitionMap.put("/**", "jwt");

        filter.setUnauthorizedUrl("/sys/auth/error");
        // ============== 用于自定义token的情况
        filter.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return filter;
    }

    /**注册shiro的Filter 拦截请求*/
    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean(SecurityManager securityManager) throws Exception {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter((Filter) Objects.requireNonNull(this.shiroFilterFactoryBean(securityManager).getObject()));
        filterRegistrationBean.addInitParameter("targetFilterLifecycle","true");
        //bean注入开启异步方式
        filterRegistrationBean.setAsyncSupported(true);
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST);
        return filterRegistrationBean;
    }


    /**
     * shiro声明周期
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    // 以下配置开启shiro注解(@RequiresPermissions等)

    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        // https://zhuanlan.zhihu.com/p/29161098
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 启用shiro注解
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }



}
