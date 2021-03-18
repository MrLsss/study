package com.study.shiro.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @createTime 2021年03月18日 22:10
 */
@Data
@TableName("sys_user_role")
public class SysUserRole implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    private int userId;

    private int roleId;

}
