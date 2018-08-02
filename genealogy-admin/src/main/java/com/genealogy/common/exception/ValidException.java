package com.genealogy.common.exception;

import com.genealogy.common.response.RespCode;

/**
 * 校验异常
 *
 * @author guofazhan
 * @version [版本号, 2018/7/27 0027 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ValidException extends BaseException {

	public ValidException(String message) {
		super(RespCode.COMM_VAT_ERROR, message);
	}

	public ValidException(String message, Throwable e) {
		super(RespCode.COMM_VAT_ERROR, message, e);
	}

	public ValidException(RespCode code, String message) {
		super(code, message);
	}

	public ValidException(RespCode code, String message, Throwable e) {
		super(code, message, e);
	}
}
