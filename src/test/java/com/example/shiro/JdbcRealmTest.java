package com.example.shiro;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class JdbcRealmTest {

    DruidDataSource dataSource = new DruidDataSource();
    {
        dataSource.setUrl("jdbc:mysql://localhost:3307/shiro");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
    }

    /**
     * JdbcRealm测试
     */
    @Test
    public void testJdbcRealm(){

        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        jdbcRealm.setPermissionsLookupEnabled(true);

        String sql = "select password from test_user where user_name = ?";
        jdbcRealm.setAuthenticationQuery(sql);

        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);

        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        //AuthenticationToken token = new UsernamePasswordToken("Hiway","123456");
        AuthenticationToken token = new UsernamePasswordToken("xiaoming","654321");

        subject.login(token);

        System.out.println("isAuthentication:"+subject.isAuthenticated());

/*        subject.checkRoles("admin");

       subject.checkPermission("user:select");*/

    }
}
