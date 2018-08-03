package com.genealogy.web.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 角色实体类
 * @author G2Y
 * @version [版本号, 2018/7/28 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@TableName("sys_role")
public class RoleEntity extends BaseEntity{

    /**
     * 角色ID
     */
    @TableId(value = "ID")
    private Integer roleId;

    /**
     * 角色名称
     */
    @TableField(value = "NAME")
    private String roleName;

    /**
     * 锁标示 0，正常；1禁用
     */
    @TableField(value = "IS_LOCK")
    private Integer isLock;

    /**
     * 描述
     */
    @TableField(value = "REMARK")
    private String remark;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getIsLock() {
        return isLock;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", isLock=" + isLock +
                ", remark='" + remark + '\'' +
                "} " + super.toString();
    }
}
