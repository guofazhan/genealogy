package com.genealogy.admin.web.service.impl;

import com.genealogy.admin.web.dao.MenuMapper;
import com.genealogy.admin.web.model.EntityHelper;
import com.genealogy.admin.web.model.MenuEntity;
import com.genealogy.admin.web.service.IMenuService;
import com.genealogy.common.Tree;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 菜单服务
 *
 * @author guofazhan
 * @version [版本号, 2018/7/27 0027 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("menuService")
public class MenuServiceImpl implements IMenuService {

	@Resource
	private MenuMapper menuMapper;

	@Override
	public Tree queryMenuTreeByUserId(Integer userId) {
		//根据用户ID查询菜单信息
		List<MenuEntity> menus = menuMapper.queryMenusByUserId(0);
		return builMenuTree(menus);
	}

	@Override
	public List<MenuEntity> queryAll() {
		return menuMapper.queryAll();
	}

	@Override
	public MenuEntity get(Integer id) {
		return menuMapper.get(id);
	}

	@Override
	public void save(MenuEntity entity) {
		//补全实体信息
		EntityHelper.compleSaveEntity(entity);
		if(entity.getSort() == null){
			entity.setSort(0);
		}
		menuMapper.save(entity);
	}

	@Override
	public int update(MenuEntity entity) {
		EntityHelper.compleSaveEntity(entity);
		if(entity.getSort() == null){
			entity.setSort(0);
		}
		return menuMapper.update(entity);
	}

	@Override
	public int delete(Integer id) {
		return menuMapper.delete(id);
	}

	/**
	 * 构建左菜单栏树结构
	 * @param menus
	 * @return
	 */
	private Tree builMenuTree(List<MenuEntity> menus){
		return buildTree(getRoot(),menus);
	}

	/**
	 * 构建树结构
	 * @param current
	 * @param menus
	 * @return
	 */
	private Tree buildTree(MenuEntity current, List<MenuEntity> menus){
		Map<String, Object> attributes = new HashMap<>(1);
		attributes.put("icon", current.getMenuIcon());
		attributes.put("url", current.getMenuUrl());

		if(null == menus || menus.isEmpty()){
			return Tree.newBuilder().setId(current.getMenuId() + "")
					.setText(current.getMenuName()).setAttributes(attributes)
					.setChildren(Collections.emptyList()).setHasParent(false)
					.setHasChildren(false).setParentId(current.getParentId() + "")
					.build();
		}
		List<Tree> currentChilds = new ArrayList<>();
		for(MenuEntity menu:menus){
			if(menu.getParentId() ==current.getMenuId()){
				//继续查找子节点
				currentChilds.add(buildTree(menu,menus));
			}
		}
		//构建树结构
		return Tree.newBuilder().setId(current.getMenuId() + "")
				.setText(current.getMenuName()).setAttributes(attributes)
				.setChildren(currentChilds).setHasParent(true)
				.setHasChildren(currentChilds.isEmpty()?false:true).setParentId(current.getParentId() + "")
				.build();
	}


	/**
	 * 创建树根级节点
	 * @return
	 */
	private MenuEntity getRoot(){
		//根菜单
		MenuEntity root = new MenuEntity();
		root.setMenuId(0);
		root.setMenuName("root");
		root.setParentId(0);
		root.setMenuType(0);
		return root;
	}



}
