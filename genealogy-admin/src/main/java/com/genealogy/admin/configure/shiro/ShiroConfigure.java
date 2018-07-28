package com.genealogy.admin.configure.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.genealogy.admin.web.shiro.CustomShiroRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * anon 	无参，开放权限，可以理解为匿名用户或游客
 * authc 	无参，需要认证
 * logout 	无参，注销，执行后会直接跳转到shiroFilterFactoryBean.setLoginUrl(); 设置的 url
 * authcBasic 	无参，表示 httpBasic 认证
 * user 	无参，表示必须存在用户，当登入操作时不做检查
 * ssl 	无参，表示安全的URL请求，协议为 https
 * perms[user] 	参数可写多个，表示需要某个或某些权限才能通过，多个参数时写 perms[“user, admin”]，当有多个参数时必须每个参数都通过才算通过
 * roles[admin] 	参数可写多个，表示是某个或某些角色才能通过，多个参数时写 roles[“admin，user”]，当有多个参数时必须每个参数都通过才算通过
 * rest[user] 	根据请求的方法，相当于 perms[user:method]，其中 method 为 post，get，delete 等
 * port[8081] 	当请求的URL端口不是8081时，跳转到schemal://serverName:8081?queryString 其中 schmal 是协议 http 或 https 等等，serverName 是你访问的 Host，8081 是 Port 端口，queryString 是你访问的 URL 里的 ? 后面的参数
 * 注意：anon, authc, authcBasic, user 是第一组认证过滤器，
 * perms, port, rest, roles, ssl 是第二组授权过滤器，
 * 要通过授权过滤器，就先要完成登陆认证操作（即先要完成认证才能前去寻找授权) 才能走第二组授权器
 * （例如访问需要 roles 权限的 url，如果还没有登陆的话，会直接跳转到 shiroFilterFactoryBean.setLoginUrl(); 设置的 url ）
 * @author guofazhan
 * @version [版本号, 2018/7/26 0026 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Configuration
public class ShiroConfigure {

	@Bean
	public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	ShiroFilterFactoryBean shiroFilterFactoryBean(
			SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// setLoginUrl 如果不设置值，默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login" 映射
		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setSuccessUrl("/index");
		// 设置无权限时跳转的 url;
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		// 设置拦截器
		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		//开放接口
		filterChainDefinitionMap.put("/login", "anon");
		filterChainDefinitionMap.put("/signin", "anon");
		filterChainDefinitionMap.put("/plugins/**", "anon");
		filterChainDefinitionMap.put("/modules/**", "anon");
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/fonts/**", "anon");
		filterChainDefinitionMap.put("/images/**", "anon");
		filterChainDefinitionMap.put("/docs/**", "anon");
		filterChainDefinitionMap.put("/druid/**", "anon");
		filterChainDefinitionMap.put("/upload/**", "anon");
		filterChainDefinitionMap.put("/files/**", "anon");
		filterChainDefinitionMap.put("/logout", "logout");
		filterChainDefinitionMap.put("/", "anon");
		//其余接口一律拦截
		//主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
		filterChainDefinitionMap.put("/**", "authc");
		shiroFilterFactoryBean
				.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

		/**
		 * ShiroDialect，为了在thymeleaf里使用shiro的标签的bean
		 *
		 * @return
		 */
		@Bean
		public ShiroDialect shiroDialect() {
			return new ShiroDialect();
		}

		/**
		 * 开启shiro aop注解支持.
		 * 使用代理方式;所以需要开启代码支持;
		 *
		 * @param securityManager
		 * @return
		 */
		@Bean
		public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
				SecurityManager securityManager) {
			AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
			authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
			return authorizationAttributeSourceAdvisor;
		}

	/**
	 * 权限管理，配置主要是Realm的管理认证
	 * @return
	 */
	@Bean
	public SecurityManager securityManager(CustomShiroRealm customShiroRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置realm.
		securityManager.setRealm(customShiroRealm);
		return securityManager;
	}

	@Bean
	public CustomShiroRealm customShiroRealm() {
		CustomShiroRealm customShiroRealm = new CustomShiroRealm();
		return customShiroRealm;
	}
}
