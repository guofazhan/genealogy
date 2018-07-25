package com.genealogy.admin.web.listener;

import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 初始化监听器
 *
 * @author guofazhan
 * @version [版本号, 2018/7/25 0025 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class InitListener implements ServletContextListener {

	/**
	 * 日志
	 */
	private static final org.slf4j.Logger logger = LoggerFactory
			.getLogger(InitListener.class);

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext context = servletContextEvent.getServletContext();
		logger.info("服务根目录：" + context.getContextPath());
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {

	}
}
