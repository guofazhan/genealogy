package com.genealogy.admin.web.filter;

import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;
import java.util.List;

/**
 * 会话过滤器
 *
 * @author guofazhan
 * @version [版本号, 2018/7/25 0025 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class SessionFilter implements Filter {

	/**
	 * 日志
	 */
	private static final org.slf4j.Logger logger = LoggerFactory
			.getLogger(SessionFilter.class);

	/**
	 * 配置参数
	 */
	private List<String> exclusions;

	public SessionFilter(List<String> exclusions) {
		this.exclusions = exclusions;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		logger.info(
				"进入过滤器：" + servletRequest.getServletContext().getContextPath());
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {

	}
}
