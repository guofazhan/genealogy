package com.genealogy.common.utils;

import com.genealogy.common.ValidException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 参数校验帮助类
 *
 * @Null   被注释的元素必须为 null
 * @NotNull    被注释的元素必须不为 null
 * @AssertTrue     被注释的元素必须为 true
 * @AssertFalse    被注释的元素必须为 false
 * @Min(value)     被注释的元素必须是一个数字，其值必须大于等于指定的最小值
 * @Max(value)     被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 * @DecimalMin(value)  被注释的元素必须是一个数字，其值必须大于等于指定的最小值
 * @DecimalMax(value)  被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 * @Size(max=, min=)   被注释的元素的大小必须在指定的范围内
 * @Digits (integer, fraction)     被注释的元素必须是一个数字，其值必须在可接受的范围内
 * @Past   被注释的元素必须是一个过去的日期
 * @Future     被注释的元素必须是一个将来的日期
 * @Pattern(regex=,flag=)  被注释的元素必须符合指定的正则表达式
 * Hibernate Validator 附加的 constraint
 * @NotBlank(message =)   验证字符串非null，且长度必须大于0
 * @Email  被注释的元素必须是电子邮箱地址
 * @Length(min=,max=)  被注释的字符串的大小必须在指定的范围内
 * @NotEmpty   被注释的字符串的必须非空
 * @Range(min=,max=,message=)  被注释的元素必须在合适的范围内
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ValidatorHelper {

	/**
	 * 校验工厂
	 */
	private static ValidatorFactory factory = Validation
			.buildDefaultValidatorFactory();

	public static <T> List<?> validate(T obj) {
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<T>> set = validator.validate(obj);
		List<String> redultList = new ArrayList<>();
		if (set.size() > 0) {
			for (ConstraintViolation<T> val : set) {
				redultList.add(val.getMessage());
			}
		}
		return redultList;
	}

	/**
	 * 分组校验
	 *
	 * @param obj    校验对象
	 * @param groups 组
	 * @param <T>
	 * @return
	 */
	public static <T> List<?> validate(T obj, Class<?>[] groups) {
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<T>> set = validator.validate(obj, groups);
		List<String> redultList = new ArrayList<>();
		if (set.size() > 0) {
			for (ConstraintViolation<T> val : set) {
				redultList.add(val.getMessage());
			}
		}

		return redultList;
	}

	/**
	 * 当校验失败抛出异常
	 *
	 * @param obj
	 * @param groups
	 * @param <T>
	 */
	public static <T> void validator(T obj, Class<?>[] groups) {
		List<?> list = validate(obj, groups);
		if (!list.isEmpty()) {
			throw new ValidException(list.toString());
		}
	}

	public static <T> void validator(T obj) {
		List<?> list = validate(obj);
		if (!list.isEmpty()) {
			throw new ValidException(list.toString());
		}
	}
}
