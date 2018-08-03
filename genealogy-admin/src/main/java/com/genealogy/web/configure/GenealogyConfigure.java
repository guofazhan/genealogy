package com.genealogy.web.configure;

import com.genealogy.web.configure.datasource.DatasourceConfigure;
import com.genealogy.web.configure.mybatis.MybatisConfigure;
import com.genealogy.web.configure.shiro.ShiroConfigure;
import com.genealogy.web.configure.web.WebConfigure;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 配置信息
 *
 * @author guofazhan
 * @version [版本号, 2018/7/25 0025 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Configuration
@Import({ DatasourceConfigure.class, MybatisConfigure.class, WebConfigure.class,
		ShiroConfigure.class })
public class GenealogyConfigure {
}