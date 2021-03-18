package com.study.shiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.study.shiro.entity.vo.LoginBodyVO;

/**
 * Copyright: Copyright (c) 2021
 *
 * <p> Description:
 *
 * @author liushuai
 * @version 1.0.0
 * @createTime 2021年03月18日 21:20
 */
public interface SysAuthService {


    void login(LoginBodyVO loginBody);

    String jwtLogin(LoginBodyVO loginBody);
}
