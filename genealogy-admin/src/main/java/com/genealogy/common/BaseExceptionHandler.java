package com.genealogy.common;

import com.genealogy.common.response.RespCode;
import com.genealogy.common.response.RespHelper;
import com.genealogy.common.response.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局的异常拦截器
 *
 * @author guofazhan
 * @version [版本号, 2018/7/27 0027 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@ControllerAdvice(annotations = Controller.class)
public class BaseExceptionHandler {

	/**
	 * 日志
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(BaseExceptionHandler.class);

	/**
	 * 构建异常返回
	 *
	 * @param e
	 * @param <T>
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	private <T> ResponseMessage<T> exceptionHandler(Exception e) {
		logger.error("---------> runtimeExceptionHandler error!", e);
		return RespHelper.buildResponseMessage(RespCode.COMM_ERROR, null);
	}

	@ExceptionHandler(BaseException.class)
	@ResponseBody
	private <T> ResponseMessage<T> baseExceptionHandler(BaseException e) {
		logger.error("---------> hljBaseExceptionHandler error!", e);
		return RespHelper.buildResponseMessage(e);
	}

	/**
	 * 构建异常返回
	 *
	 * @param e
	 * @param <T>
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	private <T> ResponseMessage<T> illegalParamsExceptionHandler(
			MethodArgumentNotValidException e) {
		logger.error("---------> illegalParamsExceptionHandler request!", e);
		return RespHelper.buildResponseMessage(RespCode.COMM_ERROR, null);
	}

}
