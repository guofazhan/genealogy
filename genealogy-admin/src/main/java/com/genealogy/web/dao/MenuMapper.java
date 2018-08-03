package com.genealogy.web.dao;

import com.genealogy.web.common.SuperMapper;
import com.genealogy.web.model.MenuEntity;

import java.util.List;
import java.util.Set;

/**
 * 菜单信息数据库操作接口
 *
 * @author guofazhan
 * @version [版本号, 2018/7/27 0027 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface MenuMapper extends SuperMapper<MenuEntity> {

	/**
	 * 根据用户ID查询菜单集合
	 *
	 * @param userId
	 * @return
	 */
	List<MenuEntity> queryMenusByUserId(Integer userId);

	/**
	 * 查询所有菜单信息
	 *
	 * @return
	 */
	List<MenuEntity> queryAll();

	/**
	 * 根据ID查询菜单信息
	 *
	 * @param id
	 * @return
	 */
	MenuEntity get(Integer id);

	/**
	 * 保存菜单信息
	 *
	 * @param entity
	 */
	void save(MenuEntity entity);

	/**
	 * 更新菜单信息
	 *
	 * @param entity
	 * @return
	 */
	int update(MenuEntity entity);

	/**
	 * 删除菜单根据菜单ID(包含删除子菜单信息)
	 *
	 * @param id
	 * @return
	 */
	int delete(Integer id);

	/**
	 * 查询权限编码集合通过用户ID
	 *
	 * @param userId
	 * @return
	 */
	Set<String> queryCodesByUserId(Integer userId);
}
