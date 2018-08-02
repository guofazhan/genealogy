package com.genealogy.admin.web.model;

/**
 * 用户角色关系实体
 *
 * @author guofazhan
 * @version [版本号, 2018/8/2 0002 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class UserRoleEntity extends BaseEntity{

	/**
	 *
	 */
	private Integer id;

	/**
	 * 用户ID
	 */
	private Integer userId;

	/**
	 * 角色ID
	 */
	private Integer roleId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "UserRoleEntity{" + "id=" + id + ", userId=" + userId
				+ ", roleId=" + roleId + "} " + super.toString();
	}
}
