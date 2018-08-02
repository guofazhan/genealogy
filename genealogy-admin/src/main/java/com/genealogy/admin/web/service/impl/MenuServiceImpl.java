package com.genealogy.admin.web.service.impl;

import com.genealogy.admin.web.dao.MenuMapper;
import com.genealogy.admin.web.dao.RoleMenuMapper;
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

	@Resource
	private RoleMenuMapper roleMenuMapper;

	@Override
	public Tree queryMenuTreeByUserId(Integer userId) {
		//根据用户ID查询菜单信息
		List<MenuEntity> menus = menuMapper.queryMenusByUserId(0);
		return builMenuTree(menus,null);
	}

	@Override
	public Tree queryMenuTreeByRoleId(Integer roleId) {
		//查询所有菜单
		List<MenuEntity> menus = menuMapper.queryAll();
		//构建树信息
		// 查询给定角色ID的所有菜单ID
		List<Integer> menuIds =  roleMenuMapper.queryMenuIdsByRoleId(roleId);
//		List<Integer> temp = menuIds;
//		for(MenuEntity menuEntity: menus){
//			if (temp.contains(menuEntity.getParentId())) {
//				menuIds.remove(menuEntity.getParentId());
//			}
//		}
        System.out.println("角色对应的菜单ID：" + menuIds);
		Tree tree = builMenuTree(menus,menuIds);
		return tree;
	}

	@Override
	public Tree queryMenuTree() {
		//查询所有菜单
		List<MenuEntity> menus = menuMapper.queryAll();
		return builMenuTree(menus,null);
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

	@Override
	public Set<String> queryCodesByUserId(Integer userId) {
		return menuMapper.queryCodesByUserId(userId);
	}

	/**
	 * 构建左菜单栏树结构
	 * @param menus
	 * @param menuIds
	 * @return
	 */
	private Tree builMenuTree(List<MenuEntity> menus,List<Integer> menuIds){
		return buildTree(getRoot(),menus, menuIds);
	}

	/**
	 * 构建树结构
	 * @param current
	 * @param menus
	 * @return
	 */
	private Tree buildTree(MenuEntity current, List<MenuEntity> menus,List<Integer> menuIds){
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
				currentChilds.add(buildTree(menu,menus,menuIds));
			}
		}

		if(current.getMenuId() == 0){
			//构建是否选中状态与打开状态
			Map<String, Object> state = new HashMap<>(1);
			state.put("opened", true);
			//构建树结构
			return Tree.newBuilder().setId(current.getMenuId() + "").setState(state).setChecked(true)
					.setText(current.getMenuName()).setAttributes(attributes)
					.setChildren(currentChilds).setHasParent(false)
					.setHasChildren(currentChilds.isEmpty()?false:true).setParentId(current.getParentId() + "")
					.build();
		}

		//构建是否选中状态
		Map<String, Object> state = new HashMap<>(1);

		if(currentChilds.isEmpty()){
			if(menuIds != null && !menuIds.isEmpty() && menuIds.contains(current.getMenuId())){
				state.put("selected", true);
			}
			else{
				state.put("selected", false);
			}
		}
		//构建树结构
		return Tree.newBuilder().setId(current.getMenuId() + "").setState(state)
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
