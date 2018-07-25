package com.genealogy.admin.configure.web;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author guofazhan
 * @version [版本号, 2018/7/25 0025 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@ConfigurationProperties(prefix = "spring.web.filter", ignoreUnknownFields = false)
public class FilterProperties {

	/**
	 * 会话过滤器配置信息
	 */
	private SessionFilterOptions session;

	public SessionFilterOptions getSession() {
		return session;
	}

	public void setSession(SessionFilterOptions session) {
		this.session = session;
	}
}
