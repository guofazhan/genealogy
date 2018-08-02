package com.genealogy.common.response;

import com.genealogy.common.exception.BaseException;

/**
 * 响应帮助类
 *
 * @author guofazhan
 * @version [版本号, 2018/7/26 0026 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class RespHelper {

	/**
	 * 构建外部请求响应
	 *
	 * @param respCode
	 * @param data
	 * @param <T>
	 * @return
	 */
	public static <T> ResponseMessage<T> buildResponseMessage(RespCode respCode,
			T data) {
		return new ResponseMessage().setRetCode(respCode.getCode())
				.setRetDesc(respCode.getDesc()).setRspBody(data);
	}

	/**
	 * 根据异常构建请求
	 *
	 * @param exception
	 * @param <T>
	 * @return
	 */
	public static <T> ResponseMessage<T> buildResponseMessage(
			BaseException exception) {
		RespCode code = exception.isRetCode() ?
				exception.getRespCode() :
				RespCode.COMM_ERROR;
		return new ResponseMessage().setRetCode(code.getCode())
				.setRetDesc(code.getDesc());
	}
}
