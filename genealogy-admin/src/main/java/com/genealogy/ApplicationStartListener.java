package com.genealogy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author guofazhan
 * @version [版本号, 2018/7/25 0025 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
public class ApplicationStartListener implements ApplicationRunner {

	/**
	 * 日志
	 */
	private final static Logger logger = LoggerFactory
			.getLogger(ApplicationStartListener.class);

	@Override
	public void run(ApplicationArguments applicationArguments)
			throws Exception {
		logger.info("====================服务启动完成====================");
	}
}
