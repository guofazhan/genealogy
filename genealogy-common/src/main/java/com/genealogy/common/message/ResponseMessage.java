package com.genealogy.common.message;

/**
 * 公共的响应体
 *
 * @author guofazhan
 * @version [版本号, 2018/7/26 0026 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ResponseMessage<T> extends Message {

	/**
	 * 响应状态码
	 */
	private String retCode;

	/**
	 * 响应中文描述，用于提示用户
	 */
	private String retDesc;

	/**
	 * 返回数据
	 */
	private T rspBody;

	public String getRetCode() {
		return retCode;
	}

	public ResponseMessage setRetCode(String retCode) {
		this.retCode = retCode;
		return this;
	}

	public String getRetDesc() {
		return retDesc;
	}

	public ResponseMessage setRetDesc(String retDesc) {
		this.retDesc = retDesc;
		return this;
	}

	public T getRspBody() {
		return rspBody;
	}

	public ResponseMessage setRspBody(T rspBody) {
		this.rspBody = rspBody;
		return this;
	}

	@Override
	public String toString() {
		return "OutsideResponseMessage{" + "rspBody=" + rspBody + ", retCode='"
				+ retCode + '\'' + ", retDesc='" + retDesc + '\'' + '}';
	}
}
