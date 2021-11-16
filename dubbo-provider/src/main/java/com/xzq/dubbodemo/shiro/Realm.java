package com.xzq.dubbodemo.shiro;

import com.xzq.dubbodemo.bean.TPermission;
import com.xzq.dubbodemo.bean.TRole;
import com.xzq.dubbodemo.bean.TUser;
import com.xzq.dubbodemo.dao.TPermissionMapper;
import com.xzq.dubbodemo.dao.TRoleMapper;
import com.xzq.dubbodemo.dao.TUserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author XZQ
 * @Date 2021/11/9 19:19
 **/
public class Realm extends AuthorizingRealm {
    @Autowired
    TUserMapper tusermapper;
    @Autowired
    TRoleMapper tRoleMapper;
    @Autowired
    TPermissionMapper tPermissionMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        TUser user = (TUser) SecurityUtils.getSubject().getPrincipal();
        String userName = user.getUsername();

        System.out.println("用户" + userName + "获取权限-----ShiroRealm.doGetAuthorizationInfo");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        // 获取用户角色集
        List<TRole> roleList = tRoleMapper.findByUserName(userName);
        Set<String> roleSet = new HashSet<String>();
        for (TRole r : roleList) {
            roleSet.add(r.getName());
        }
        simpleAuthorizationInfo.setRoles(roleSet);

        // 获取用户权限集
        List<TPermission> permissionList = tPermissionMapper.findByUserName(userName);
        Set<String> permissionSet = new HashSet<String>();
        for (TPermission p : permissionList) {
            permissionSet.add(p.getName());
        }
        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String userName = (String) authenticationToken.getPrincipal();
        //String password = new String((char[]) authenticationToken.getCredentials());
        char[] ch = (char[]) authenticationToken.getCredentials();
        String password=String.copyValueOf(ch);
        System.out.println("用户" + userName + "认证-----ShiroRealm.doGetAuthenticationInfo");
        TUser user = tusermapper.select(userName,password);

        if (user == null) {
            throw new UnknownAccountException("用户名或密码错误！");
        }
        if (!password.equals(user.getPasswd())) {
            throw new IncorrectCredentialsException("用户名或密码错误！");
        }
        if (user.getStatus().equals("0")) {
            throw new LockedAccountException("账号已被锁定,请联系管理员！");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }
}
