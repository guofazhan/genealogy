package com.genealogy.web.vo;

import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * 角色添加与编辑请求参数
 * @author G2Y
 * @version [版本号, 2018/7/28 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class RoleAddAndEditReqVo extends VO{

    /**
     * 角色对应的菜单ID
     */
    private List<Integer> menuIds;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    private String roleName;

    /**
     * 描述
     */
    private String remark;

    public List<Integer> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Integer> menuIds) {
        this.menuIds = menuIds;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "RoleAddAndEditReqVo{" +
                "menuIds='" + menuIds + '\'' +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", remark='" + remark + '\'' +
                "} " + super.toString();
    }
}
