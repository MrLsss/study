package com.study.shiro.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Copyright: Copyright (c) 2021
 *
 * <p> Description:
 *
 * @author liushuai
 * @version 1.0.0
 * @createTime 2021年03月18日 22:11
 */
@Data
@TableName("sys_role_permission")
public class SysRolePermission implements Serializable {

    private int id;

    private int roleId;

    private int permissionId;

}
