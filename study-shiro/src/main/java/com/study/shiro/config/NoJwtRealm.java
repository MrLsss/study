package com.study.shiro.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.shiro.entity.*;
import com.study.shiro.mapper.*;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Copyright: Copyright (c) 2021
 *
 * <p> Description: 自定义realm
 *
 * @author liushuai
 * @version 1.0.0
 * @createTime 2021年03月18日 21:18
 */
@Component
public class NoJwtRealm extends AuthorizingRealm {


    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysRolePermissionMapper sysRolePermissionMapper;
    @Resource
    private SysPermissionMapper sysPermissionMapper;




    /**
     * 登录认证
     * @param token token
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        Object principal = token.getPrincipal();
        if (principal == null) {
            throw new RuntimeException("token不合法");
        }
        String username = principal.toString();
        SysUser sysUser = sysUserMapper.selectOne(new QueryWrapper<SysUser>().lambda()
                .eq(SysUser::getUsername, username)
        );
        if (sysUser == null) {
            throw new AuthenticationException("用户 " + username + " 不存在");
        }
        if (!sysUser.getPassword().equalsIgnoreCase(new String((char[]) token.getCredentials()))) {
            throw new AuthenticationException("密码错误");
        }

        // ... 其他状态判断
        return new SimpleAuthenticationInfo(
                sysUser,
                sysUser.getPassword(),
                super.getName()
        );
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        SysUser sysUser = (SysUser) principalCollection.getPrimaryPrincipal(); // 获取的是认证方法返回的SimpleAuthenticationInfo 第一个参数
        int userId = sysUser.getId();

        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectList(new QueryWrapper<SysUserRole>().lambda()
                .eq(SysUserRole::getUserId, userId));
        Set<String> roles = new HashSet<>();
        Set<String> permissions = new HashSet<>();
        sysUserRoles.forEach(roleId -> {
            SysRole sysRole = sysRoleMapper.selectOne(new QueryWrapper<SysRole>().lambda()
                    .eq(SysRole::getId, roleId.getRoleId()));
            roles.add(sysRole.getCode());

            List<SysRolePermission> sysRolePermissions = sysRolePermissionMapper.selectList(new QueryWrapper<SysRolePermission>().lambda()
                    .eq(SysRolePermission::getRoleId, roleId.getRoleId()));
            sysRolePermissions.forEach(permId -> {
                SysPermission sysPermission = sysPermissionMapper.selectOne(new QueryWrapper<SysPermission>().lambda()
                        .eq(SysPermission::getId, permId.getPermissionId()));
                permissions.add(sysPermission.getCode());
            });
        });

        info.setStringPermissions(permissions);
        info.setRoles(roles);
        return info;
    }
}
