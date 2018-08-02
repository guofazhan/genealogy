package com.genealogy.common.response;

/**
 * @author guofazhan
 * @version [版本号, 2018/7/26 0026 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class RespCode {

	/**
	 * 成功
	 */
	public static RespCode SUCCESS = new RespCode("000000", "success", "成功返回码");

	/**
	 * 异常
	 */
	public static RespCode COMM_ERROR = new RespCode("000001", "系统繁忙，请稍后再试！",
			"异常返回码");

	/**
	 * 网络异常
	 */
	public static RespCode COMM_NET_ERROR = new RespCode("000002",
			"当前网络不可用，请检查网络设置！", "网络异常");

	/**
	 * 校验失败
	 */
	public static RespCode COMM_VAT_ERROR = new RespCode("000003",
			"请求参数校验失败，请检查参数信息！", "校验失败");

	/**
	 * FALLBACK
	 */
	public static RespCode COMM_FALLBACK = new RespCode("000004", "系统繁忙，请稍后再试！",
			"熔断器熔断返回码");

	/**
	 * 正常失败
	 */
	public static RespCode COMM_FAIL = new RespCode("000005", "系统繁忙，请稍后再试！",
			"失败返回码");
	/**
	 * 安全校验失败
	 */
	public static RespCode COMM_SECUR_ERROR = new RespCode("000006",
			"系统繁忙，请稍后再试！", "安全校验失败");

	/**
	 * 账号错误
	 */
	public static RespCode ACCOUNT_ERROR = new RespCode("000007", "账号或密码不正确", "登录失败");

	/**
	 * 账号锁
	 */
	public static RespCode ACCOUNT_LOCK = new RespCode("000008", "账号已被锁定,请联系管理员", "登录失败");

	/**
	 * 返回编码
	 */
	private final String code;

	/**
	 * 返回客戶端描述
	 */
	private final String desc;

	/**
	 * 内部描述
	 */
	private final String remark;

	/**
	 * 返回码
	 *
	 * @param code
	 * @param desc
	 * @param remark
	 */
	private RespCode(String code, String desc, String remark) {
		this.code = code;
		this.desc = desc;
		this.remark = remark;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public String getRemark() {
		return remark;
	}

	@Override
	public String toString() {
		return "RespCode{" + "code='" + code + '\'' + ", desc='" + desc + '\''
				+ ", remark='" + remark + '\'' + '}';
	}
}
