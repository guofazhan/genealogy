package com.genealogy.web.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 角色菜单关联关系实体
 * @author G2Y
 * @version [版本号, 2018/7/28 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@TableName("sys_role_menu")
public class RoleMenuEntity extends BaseEntity{

    /**
     * ID
     */
    @TableId(value = "ID")
    private Integer id;

    /**
     * 角色ID
     */
    @TableField(value = "ROLE_ID")
    private Integer roleId;

    /**
     * 菜单ID
     */
    @TableField(value = "MENU_ID")
    private Integer menuId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "RoleMenuEntity{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", menuId=" + menuId +
                "} " + super.toString();
    }
}
