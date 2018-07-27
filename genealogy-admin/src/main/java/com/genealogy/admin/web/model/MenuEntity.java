package com.genealogy.admin.web.model;

/**
 * 菜单实体
 *
 * @author guofazhan
 * @version [版本号, 2018/7/27 0027 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MenuEntity extends BaseEntity {

	/**
	 * 菜单ID
	 */
	private int menuId;

	/**
	 * 父菜单ID
	 */
	private int parentId;

	/**
	 * 菜单名称
	 */
	private String menuName;

	/**
	 * 菜单URL
	 */
	private String menuUrl;

	/**
	 * 授权(多个用逗号分隔，如：user:list,user:create)
	 */
	private String perms;

	/**
	 * 类型 0：目录 1：菜单 2：按钮
	 */
	private Integer menuType;

	/**
	 * 菜单图标
	 */
	private String menuIcon;

	/**
	 * 排序
	 */
	private Integer order;

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}

	public Integer getMenuType() {
		return menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "MenuEntity{" + "menuId=" + menuId + ", parentId=" + parentId
				+ ", menuName='" + menuName + '\'' + ", menuUrl='" + menuUrl
				+ '\'' + ", perms='" + perms + '\'' + ", menuType=" + menuType
				+ ", menuIcon='" + menuIcon + '\'' + ", order=" + order + "} "
				+ super.toString();
	}
}
