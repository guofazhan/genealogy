package com.genealogy.admin.configure.mybatis;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
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

	@Bean
	public ConfigurationCustomizer configurationCustomizer() {
		return configuration -> {
			//设置驼峰命名规则
			configuration.setMapUnderscoreToCamelCase(true);
		};
	}

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}
}
