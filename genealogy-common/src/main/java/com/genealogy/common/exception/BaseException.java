package com.genealogy.common.exception;

import com.genealogy.common.message.respcode.RespCode;

/**
 * 异常类
 *
 * @author guofazhan
 * @version [版本号, 2018/7/26 0026 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class BaseException extends RuntimeException {

	/**
	 *
	 */
	private RespCode respCode;

	public BaseException(String message) {
		this(RespCode.COMM_ERROR, message);
	}

	public BaseException(String message, Throwable e) {
		this(RespCode.COMM_ERROR, message, e);
	}

	public BaseException(RespCode respCode) {
		this(respCode,
				"code:" + respCode.getCode() + ",desc:" + respCode.getDesc());
	}

	public BaseException(RespCode respCode, Throwable e) {
		this(respCode,
				"code:" + respCode.getCode() + ",desc:" + respCode.getDesc(),
				e);
	}

	public BaseException(RespCode respCode, String message) {
		super(message);
		this.respCode = respCode;
	}

	public BaseException(RespCode respCode, String message, Throwable e) {
		super(message, e);
		this.respCode = respCode;
	}

	public RespCode getRespCode() {
		return respCode;
	}

	public void setRespCode(RespCode respCode) {
		this.respCode = respCode;
	}

	@Override
	public String toString() {
		return "HljBaseException{" + "respCode=" + respCode + '}';
	}

	public boolean isRetCode() {
		return null != respCode;
	}
}
