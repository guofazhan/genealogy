package com.genealogy.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志注解
 *
 * @author guofazhan
 * @version [版本号, 2018/7/26 0026 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
	String value() default "";
}