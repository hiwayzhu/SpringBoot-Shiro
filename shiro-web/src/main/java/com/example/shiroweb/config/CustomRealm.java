package com.example.shiroweb.config;

import com.example.shiroweb.entity.Users;
import com.example.shiroweb.service.UserRolesService;
import com.example.shiroweb.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {

    @Resource
    UserService userService;

    @Resource
    UserRolesService userRolesService;
    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        Set<String> roles = getRolesByUserName(username);

        Set<String> permissions = getPermissionsByUserName(username);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permissions);
        info.setRoles(roles);
        return info;
    }

    private Set<String> getPermissionsByUserName(String username) {
        System.out.println("从数据库中取数据");
        Set<String> sets =new HashSet<>();
        sets.add("user:delete");
        return sets;
    }

    private Set<String> getRolesByUserName(String username) {
        System.out.println("从数据库中取数据");
        Set<String> sets = userRolesService.getRolesByUsername(username);
        return sets;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1.从主体传过来的认证信息中，获得用户名
        String username = (String) token.getPrincipal();
        
        Users users = userService.getPasswordByUsername(username);
        if(users == null){
            return null;
        }
        String password = users.getPassword();

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,password,getName());
        //加盐
        //info.setCredentialsSalt(ByteSource.Util.bytes("Hiway"));
        return info;
    }
}
