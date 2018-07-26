package com.genealogy.admin.configure.web;

import com.genealogy.admin.web.filter.SessionFilter;
import com.genealogy.admin.web.filter.XssFilter;
import com.genealogy.admin.web.listener.InitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * WEB 配置信息
 *
 * @author guofazhan
 * @version [版本号, 2018/7/25 0025 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Configuration
@EnableConfigurationProperties({ FilterProperties.class })
public class WebConfigure {

	@Autowired
	private FilterProperties filterProperties;

	@Bean
	public ServletListenerRegistrationBean initListenerRegister() {
		ServletListenerRegistrationBean registrationBean = new ServletListenerRegistrationBean();
		registrationBean.setListener(new InitListener());
		return registrationBean;
	}

	/**
	 * xss过滤拦截器
	 */
	@Bean
	public FilterRegistrationBean xssFilterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new XssFilter());
		filterRegistrationBean.setOrder(2);
		filterRegistrationBean.setEnabled(false);
		filterRegistrationBean.addUrlPatterns("/*");
		Map<String, String> initParameters = new HashMap<>();
		initParameters.put("excludes", "/favicon.ico,/img/*,/js/*,/css/*");
		initParameters.put("isIncludeRichText", "true");
		filterRegistrationBean.setInitParameters(initParameters);
		return filterRegistrationBean;
	}

	@Bean
	public FilterRegistrationBean sessionFilterRegister() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		if (null == filterProperties.getSession()) {
			//注入过滤器
			registration.setFilter(new SessionFilter(null));
			//拦截规则
			registration.addUrlPatterns("/*");
			//过滤器名称
			registration.setName("sessionFilter");
			//过滤器顺序
			registration.setOrder(1);
		} else {
			//注入过滤器
			registration.setFilter(new SessionFilter(
					filterProperties.getSession().getExclusions()));
			//拦截规则
			registration.addUrlPatterns(
					filterProperties.getSession().getUrlPatterns() == null ?
							"/*" :
							filterProperties.getSession().getUrlPatterns());
			//过滤器名称
			registration.setName(
					filterProperties.getSession().getName() == null ?
							"sessionFilter" :
							filterProperties.getSession().getName());
			//过滤器顺序
			registration.setOrder(filterProperties.getSession().getOrder());
		}

		return registration;
	}
}
