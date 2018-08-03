package com.genealogy.web.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 用户角色关系实体
 *
 * @author guofazhan
 * @version [版本号, 2018/8/2 0002 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@TableName("sys_user_role")
public class UserRoleEntity extends BaseEntity{

	/**
	 *
	 */
	@TableId(value = "ID")
	private Integer id;

	/**
	 * 用户ID
	 */
	@TableField(value = "USER_ID")
	private Integer userId;

	/**
	 * 角色ID
	 */
	@TableField(value = "ROLE_ID")
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
