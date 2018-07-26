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
	 * 单位ID
	 */
	private int instituionId;

	/**
	 * z职位ID
	 */
	private int positionId;

	/**
	 * 显示名
	 */
	private String showName;

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

	public int getInstituionId() {
		return instituionId;
	}

	public void setInstituionId(int instituionId) {
		this.instituionId = instituionId;
	}

	public int getPositionId() {
		return positionId;
	}

	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	@Override
	public String toString() {
		return "UserEntity{" + "userId=" + userId + ", loginName='" + loginName
				+ '\'' + ", password='" + password + '\'' + ", isLock=" + isLock
				+ ", instituionId=" + instituionId + ", positionId="
				+ positionId + ", showName='" + showName + '\'' + "} " + super
				.toString();
	}
}
