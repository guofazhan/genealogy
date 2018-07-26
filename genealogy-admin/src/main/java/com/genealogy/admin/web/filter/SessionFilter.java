package com.genealogy.admin.web.filter;

import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
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
	 * 默认的静态信息
	 */
	private static final String[] STATIC_ARR = { "/plugins/", "/css/",
			"/modules/", "/js/", "/fonts/", "/images/" };

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
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		String reqUrl = request.getRequestURI();
		reqUrl = reqUrl.replace(request.getContextPath(), "");
		if (!match(reqUrl)) {
			logger.info("进入过滤器：" + reqUrl);
			//未匹配到URL
			filterChain.doFilter(servletRequest, servletResponse);
		} else {
			filterChain.doFilter(servletRequest, servletResponse);
		}
	}

	@Override
	public void destroy() {

	}

	public boolean match(String url) {
		boolean match = false;
		for (String sw : STATIC_ARR) {
			if (url.startsWith(sw)) {
				match = true;
				break;
			}
		}
		return match;
	}
}
