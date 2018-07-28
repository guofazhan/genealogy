package com.genealogy.admin.web.dao;

import com.genealogy.admin.web.model.MenuEntity;

import java.util.List;

/**
 * 菜单信息数据库操作接口
 *
 * @author guofazhan
 * @version [版本号, 2018/7/27 0027 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface MenuMapper {

	/**
	 * 根据用户ID查询菜单集合
	 *
	 * @param userId
	 * @return
	 */
	List<MenuEntity> queryMenusByUserId(Integer userId);
}
