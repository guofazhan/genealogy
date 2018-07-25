package com.genealogy.admin.configure;

import com.genealogy.admin.configure.datasource.DatasourceConfigure;
import com.genealogy.admin.configure.web.WebConfigure;
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
@Import({ DatasourceConfigure.class, WebConfigure.class })
public class GenealogyConfigure {
}
