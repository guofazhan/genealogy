package com.genealogy.common.log;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 日志接口
 *
 * @author guofazhan
 * @version [版本号, 2018/8/3 0003 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface ILogService {

	/**
	 * 日志统计接口
	 *
	 * @param operation 操作描述
	 * @param point
	 * @param time 耗时
	 */
	void log(String operation, ProceedingJoinPoint point, long time);
}
