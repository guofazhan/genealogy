package com.genealogy.common.aspect;

import com.genealogy.common.exception.ValidException;
import com.genealogy.common.utils.ValidatorHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * 参数校验AOP
 *
 * @author guofazhan
 * @version [版本号, 2018/7/27 0027 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Aspect
@Component
@Order(-6)
public class ParamVaildAspect {

	/**
	 * 日志
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(ParamVaildAspect.class);

	@Pointcut("@annotation(com.genealogy.common.aspect.annotation.ParamVailds)")
	public void paramVaild() {

	}

	/**
	 * 参数校验
	 *
	 * @param joinPoint
	 * @throws Throwable
	 */
	@Around("paramVaild()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		Object[] args = joinPoint.getArgs();
		if (args != null) {
			for (Object arg : args) {
				if (arg instanceof BindingResult) {
					checkBindingResult((BindingResult) arg);
				} else {
					ValidatorHelper.validator(arg);
				}
			}
		}
		return joinPoint.proceed();
	}

	/**
	 * 检测校验结果
	 *
	 * @param br
	 */
	protected void checkBindingResult(BindingResult br) throws ValidException {
		if (br.hasErrors()) {
			StringBuffer errorAll = new StringBuffer();
			errorAll.append("CHECK-FAIL:[");
			for (ObjectError one : br.getAllErrors()) {
				errorAll.append(one.getDefaultMessage()).append(";");
			}
			errorAll.append("]");
			throw new ValidException(errorAll.toString());
		}
	}
}
