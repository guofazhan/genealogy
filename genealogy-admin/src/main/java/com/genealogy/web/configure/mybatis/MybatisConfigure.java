package com.genealogy.web.configure.mybatis;

import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

/**
 * @author guofazhan
 * @version [版本号, 2018/7/26 0026 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@EnableTransactionManagement
@Configuration
public class MybatisConfigure implements TransactionManagementConfigurer {

	/**
	 * 日志
	 */
	private static final org.slf4j.Logger logger = LoggerFactory
			.getLogger(MybatisConfigure.class);

	@Autowired
	private DataSource dataSource;

	/**
	 * mybatis-plus SQL执行效率插件
	 */
	@Bean
	public PerformanceInterceptor performanceInterceptor() {
		return new PerformanceInterceptor();
	}

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}
}
