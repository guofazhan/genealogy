package com.genealogy.web.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * 菜单实体
 *
 * @author guofazhan
 * @version [版本号, 2018/7/27 0027 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@TableName("sys_menu")
public class MenuEntity extends BaseEntity {

	/**
	 * 菜单ID
	 */
	@TableId(value = "ID")
	private int menuId;

	/**
	 * 父菜单ID
	 */
	@TableField(value = "PARENT_ID")
	private int parentId;

	/**
	 * 菜单名称
	 */
	@TableField(value = "NAME")
	@NotBlank(message = "菜单名不能为空")
	private String menuName;

	/**
	 * 菜单URL
	 */
	@TableField(value = "URL")
	private String menuUrl;

	/**
	 * 授权(多个用逗号分隔，如：user:list,user:create)
	 */
	@TableField(value = "CODE")
	private String code;

	/**
	 * 类型 0：目录 1：菜单 2：按钮
	 */
	@TableField(value = "TYPE")
	@NotNull(message = "菜单类型不能为空")
	@Range(max=2, min=0,message = "菜单类型必须在合法范围")
	private Integer menuType;

	/**
	 * 菜单图标
	 */
	@TableField(value = "ICON")
	private String menuIcon;

	/**
	 * 排序
	 */
	@TableField(value = "SORT")
	private Integer sort;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "MenuEntity{" +
				"menuId=" + menuId +
				", parentId=" + parentId +
				", menuName='" + menuName + '\'' +
				", menuUrl='" + menuUrl + '\'' +
				", code='" + code + '\'' +
				", menuType=" + menuType +
				", menuIcon='" + menuIcon + '\'' +
				", sort=" + sort +
				"} " + super.toString();
	}
}
