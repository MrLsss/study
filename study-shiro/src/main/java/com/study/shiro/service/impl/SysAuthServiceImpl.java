package com.study.shiro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.shiro.entity.SysUser;
import com.study.shiro.entity.vo.LoginBodyVO;
import com.study.shiro.jwt.JwtUtils;
import com.study.shiro.mapper.SysUserMapper;
import com.study.shiro.service.SysAuthService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

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
@Service
public class SysAuthServiceImpl implements SysAuthService {

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private JwtUtils jwtUtils;

    @Override
    public void login(LoginBodyVO loginBody) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(
                loginBody.getUsername(),
                loginBody.getPassword()
        );
        subject.login(token);
    }

    @Override
    public String jwtLogin(LoginBodyVO loginBody) {
        SysUser sysUser = sysUserMapper.selectOne(new QueryWrapper<SysUser>().lambda()
                .eq(SysUser::getUsername, loginBody.getUsername()));
        if (sysUser == null) {
            return "用户不存在";
        }
        if (!sysUser.getPassword().equalsIgnoreCase(loginBody.getPassword())) {
            return "密码错误";
        }
        return jwtUtils.sign(sysUser);
    }
}
