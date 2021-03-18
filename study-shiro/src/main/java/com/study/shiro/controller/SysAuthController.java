package com.study.shiro.controller;

import com.study.shiro.entity.SysUser;
import com.study.shiro.entity.vo.LoginBodyVO;
import com.study.shiro.service.SysAuthService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Copyright: Copyright (c) 2021
 *
 * <p> Description:
 *
 * @author liushuai
 * @version 1.0.0
 * @createTime 2021年03月18日 21:21
 */
@RestController
@RequestMapping("/sys/auth")
public class SysAuthController {


    @Resource
    private SysAuthService sysAuthService;

    /**
     * 传统登录方式
     */
    @PostMapping("/login")
    public String login(@RequestBody @Validated LoginBodyVO loginBody) {
        sysAuthService.login(loginBody);
        return "SUCCESS";
    }

    /**
     * jwt方式登录
     */
    @PostMapping("/jwt/login")
    public String jwtLogin(@RequestBody @Validated LoginBodyVO loginBody) {
        return sysAuthService.jwtLogin(loginBody);
    }

    @RequiresPermissions("user:info")
    @GetMapping("/info")
    public String info() {
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        return sysUser.toString();
    }

    @RequiresRoles("admin")
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/success")
    public String success() {
        return "login success";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }


}
