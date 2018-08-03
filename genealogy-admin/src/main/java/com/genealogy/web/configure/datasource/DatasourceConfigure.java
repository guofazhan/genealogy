package com.genealogy.web.configure.datasource;

import com.genealogy.common.datasource.SecurityDruidDataSource;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author guofazhan
 * @version [版本号, 2018/7/25 0025 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Configuration
@EnableConfigurationProperties({ DatasourceProperties.class })
public class DatasourceConfigure {
	/**
	 * 模板的配置信息
	 */
	@Autowired
	private DatasourceProperties datasourceProperties;

	/**
	 * 日志
	 */
	private static final org.slf4j.Logger logger = LoggerFactory
			.getLogger(DatasourceConfigure.class);

	/**
	 * 实例化数据源bean
	 *
	 * @return
	 */
	@Bean
	@Qualifier("defaultDataSource")
	public DataSource druidDataSource() {
		Assert.notNull(datasourceProperties.getDefaultDataSource(),
				"DataSource Properties must not be null");
		logger.info("Init DataSource By Properties:{}",
				datasourceProperties.getDefaultDataSource());
		SecurityDruidDataSource datasource = new SecurityDruidDataSource();
		datasource.setUrl(datasourceProperties.getDefaultDataSource().getUrl());
		datasource.setUsername(
				datasourceProperties.getDefaultDataSource().getUsername());
		datasource.setPassword(
				datasourceProperties.getDefaultDataSource().getPassword());
		datasource.setDriverClassName(
				datasourceProperties.getDefaultDataSource()
						.getDriverClassName());
		datasource.setInitialSize(
				datasourceProperties.getDefaultDataSource().getInitialSize());
		datasource.setMinIdle(
				datasourceProperties.getDefaultDataSource().getMinIdle());
		datasource.setMaxActive(
				datasourceProperties.getDefaultDataSource().getMaxActive());
		datasource.setMaxWait(
				datasourceProperties.getDefaultDataSource().getMaxWait());
		datasource.setTimeBetweenEvictionRunsMillis(
				datasourceProperties.getDefaultDataSource()
						.getTimeBetweenEvictionRunsMillis());
		datasource.setMinEvictableIdleTimeMillis(
				datasourceProperties.getDefaultDataSource()
						.getMinEvictableIdleTimeMillis());
		datasource.setValidationQuery(
				datasourceProperties.getDefaultDataSource()
						.getValidationQuery());
		datasource.setTestWhileIdle(
				datasourceProperties.getDefaultDataSource().isTestWhileIdle());
		datasource.setTestOnBorrow(
				datasourceProperties.getDefaultDataSource().isTestOnBorrow());
		datasource.setTestOnReturn(
				datasourceProperties.getDefaultDataSource().isTestOnReturn());
		//添加时是否需要init操作
					try {
						datasource.init();
					} catch (SQLException e) {
						logger.error("Init DataSource Error:",e);
					}

		return datasource;
	}
}
