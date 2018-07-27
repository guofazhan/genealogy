package com.genealogy.admin.web.service;

import com.genealogy.admin.web.model.MenuEntity;
import com.genealogy.common.Tree;

import java.util.List;

/**
 * 菜单服务接口
 *
 * @author guofazhan
 * @version [版本号, 2018/7/27 0027 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface IMenuService {

	/**
	 * 查询菜单树根据用户ID
	 *
	 * @param userId
	 * @return
	 */
	Tree queryMenuTreeByUserId(Integer userId);

	/**
	 * 查询所有菜单信息
	 *
	 * @return
	 */
	List<MenuEntity> queryAll();
}
