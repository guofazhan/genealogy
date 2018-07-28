package com.genealogy;

import com.genealogy.common.utils.ServiceBanner;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 服务启动入口
 *
 * @author guofazhan
 * @version [版本号, 2018/7/24 0024 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@SpringBootApplication
@MapperScan("com.genealogy.admin.web.dao")
public class GenealogyApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(
				GenealogyApplication.class);
		app.setBannerMode(Banner.Mode.CONSOLE);
		app.setBanner(new ServiceBanner());
		app.run(args);
	}
}
