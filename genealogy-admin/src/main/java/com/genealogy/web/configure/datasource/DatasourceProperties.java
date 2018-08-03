package com.genealogy.web.configure.datasource;

import com.genealogy.common.datasource.DatasourceOptions;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author guofazhan
 * @version [版本号, 2018/7/25 0025 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@ConfigurationProperties(prefix = "spring.datasource", ignoreUnknownFields = false)
public class DatasourceProperties {

	/**
	 * 默认数据源
	 */
	private DatasourceOptions defaultDataSource;

	public DatasourceOptions getDefaultDataSource() {
		return defaultDataSource;
	}

	public void setDefaultDataSource(DatasourceOptions defaultDataSource) {
		this.defaultDataSource = defaultDataSource;
	}

	@Override
	public String toString() {
		return "DatasourceProperties{" + "defaultDataSource="
				+ defaultDataSource + '}';
	}
}
