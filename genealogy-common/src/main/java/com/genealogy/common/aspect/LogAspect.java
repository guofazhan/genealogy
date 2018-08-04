package com.genealogy.common.aspect;

import com.genealogy.common.aspect.annotation.Log;
import com.genealogy.common.log.ILogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 操作记录AOP
 * @author guofazhan
 * @version [版本号, 2018/7/26 0026 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Aspect
@Component
public class LogAspect {

    @Autowired
    private ILogService logService;


    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory
            .getLogger(ParamVaildAspect.class);

    @Pointcut("@annotation(com.genealogy.common.aspect.annotation.Log)")
    public void log() {

    }


    /**
     * 记录日志
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("log()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = joinPoint.proceed();
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        String operation="NA";
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log syslog = method.getAnnotation(Log.class);
        if (syslog != null) {
            operation = syslog.value();
        }
        logService.log(operation,joinPoint,time);
        return result;
    }
}
