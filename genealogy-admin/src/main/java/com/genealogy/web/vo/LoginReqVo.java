package com.genealogy.web.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author guofazhan
 * @version [版本号, 2018/7/26 0026 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class LoginReqVo extends VO {

	/**
	 * 登录名
	 */
	@NotBlank(message = "登录用户名不能为空")
	private String loginName;

	/**
	 * 登录密码
	 */
	@NotBlank(message = "密码不能为空")
	@Length(min = 8, message = "密码长度不能低于8位")
	private String password;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginReqVo{" + "loginName='" + loginName + '\'' + ", password='"
				+ password + '\'' + '}';
	}
}
