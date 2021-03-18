package com.study.shiro.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Copyright: Copyright (c) 2021
 *
 * <p> Description:
 *
 * @author liushuai
 * @version 1.0.0
 * @createTime 2021年03月18日 21:15
 */
@Data
@TableName("sys_permission")
public class SysPermission {

    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    private String name;

    private String code;

}
