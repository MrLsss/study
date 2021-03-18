package com.study.shiro.entity.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Copyright: Copyright (c) 2021
 *
 * <p> Description:
 *
 * @author liushuai
 * @version 1.0.0
 * @createTime 2021年03月18日 21:22
 */
@Data
public class LoginBodyVO {

    @NotNull(message = "用户名不能为空")
    private String username;

    @NotNull(message = "密码不能为空")
    private String password;


}
