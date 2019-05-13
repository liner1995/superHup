package cn.hups.common.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro配置类
 */

@Configuration
public class ShiroConfiguration {

    /**
     * 引入自定义的Realm
     * @return
     */
    @Bean
    public CustomRealm myShiroRealm() {
        CustomRealm myShiroRealm = new CustomRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了)
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }

    /**
     * 自定义sessionManager
     * @return
     */
    @Bean
    public SessionManager sessionManager() {
        HupSessionManager mySessionManager = new HupSessionManager();
        return mySessionManager;
    }

    /**
     * session获取工具类
     * @return
     */
    @Bean
    public HupSessionManager myHupSessionManager(){
        HupSessionManager mySessionManager = new HupSessionManager();
        return mySessionManager;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        // 自定义session管理
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    /**
     * Filter工厂，设置对应的过滤条件和跳转条件
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> map = new LinkedHashMap<String, String>();
        // 登出
        map.put("/logout","logout");
        // 不过滤anon 不需要认证 authc 需要认证
        // 对静态资源设置匿名访问
        map.put("/bill/**","anon");
        map.put("/static/**","anon");
        map.put("/","anon");
        // 登可以匿名访问页面
        map.put("/systemController/login.do","anon");
        //对所有用户认证
        map.put("/**","anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    //加入注解的使用，不加入这个注解不生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
