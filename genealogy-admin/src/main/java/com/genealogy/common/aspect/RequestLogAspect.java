package com.genealogy.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author guofazhan
 * @version [版本号, 2018/7/26 0026 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Aspect
@Component
public class RequestLogAspect {

	private static final Logger logger = LoggerFactory
			.getLogger(RequestLogAspect.class);

	/**
	 * 开始时间
	 */
	ThreadLocal<Long> startTime = new ThreadLocal<>();


	@Pointcut("@within(org.springframework.stereotype.Controller)")
	public void logPointCut() {
	}

	@Before("logPointCut()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		startTime.set(System.currentTimeMillis());
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		String cm = joinPoint.getSignature().getDeclaringType().getSimpleName()
				+ "." + joinPoint.getSignature().getName();
		// 记录下请求内容
		logger.info("[URL:{}] [METHOD:{}] [ST:{}] [CLASS_METHOD:{}] [ARGS:{}]",
				request.getRequestURL().toString(), request.getMethod(),
				startTime.get(), cm, Arrays.toString(joinPoint.getArgs()));

	}

	@AfterReturning(returning = "ret", pointcut = "logPointCut()")
	public void doAfterReturning(JoinPoint joinPoint, Object ret)
			throws Throwable {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		logger.info("[URL:{}] [METHOD:{}]  [CT:{}] [RES:{}]",
				request.getRequestURL().toString(), request.getMethod(),
				(System.currentTimeMillis() - startTime.get()), ret);
	}
}
