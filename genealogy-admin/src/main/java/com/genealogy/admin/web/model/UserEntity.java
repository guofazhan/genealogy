package com.genealogy.admin.web.model;


/**
 * 用户实体
 *
 * @author guofazhan
 * @version [版本号, 2018/7/26 0026 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class UserEntity extends BaseEntity {

	/**
	 * 用户ID 自增
	 */
	private int userId;

	/**
	 * 登录名
	 */
	private String loginName;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 用户锁标识 0 正常，1锁
	 */
	private int isLock;

	/**
	 * 职位信息
	 */
	private String position;

	/**
	 * 显示名
	 */
	private String showName;

	/**
	 * 邮箱
	 */
	private String email;


	/**
	 * 手机号
	 */
	private String mobile;


	/**
	 * 性别 0 男 1 女
	 */
	private Integer sex;

	/**
	 * 住地
	 */
	private String address;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

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

	public int getIsLock() {
		return isLock;
	}

	public void setIsLock(int isLock) {
		this.isLock = isLock;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}


	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "UserEntity{" +
				"userId=" + userId +
				", loginName='" + loginName + '\'' +
				", password='" + password + '\'' +
				", isLock=" + isLock +
				", position='" + position + '\'' +
				", showName='" + showName + '\'' +
				", email='" + email + '\'' +
				", mobile='" + mobile + '\'' +
				", sex=" + sex +
				", address='" + address + '\'' +
				"} " + super.toString();
	}
}
