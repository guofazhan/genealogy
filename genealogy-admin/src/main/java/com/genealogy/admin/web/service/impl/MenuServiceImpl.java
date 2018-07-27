package com.genealogy.admin.web.service.impl;

import com.genealogy.admin.web.dao.MenuMapper;
import com.genealogy.admin.web.model.MenuEntity;
import com.genealogy.admin.web.service.IMenuService;
import com.genealogy.common.Tree;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		//根菜单
		MenuEntity root = new MenuEntity();
		root.setMenuId(0);
		root.setMenuName("root");
		root.setParentId(0);
		root.setMenuType(0);

		//系统管理
		MenuEntity sys = new MenuEntity();
		sys.setMenuId(1);
		sys.setMenuName("系统管理");
		sys.setParentId(0);
		sys.setMenuType(0);
		sys.setMenuIcon("fa fa-desktop");

		MenuEntity user = new MenuEntity();
		user.setMenuId(2);
		user.setMenuName("用户管理");
		user.setMenuType(1);
		user.setParentId(1);
		user.setMenuIcon("fa fa-user");
		user.setMenuUrl("sys/user/");

		MenuEntity menu = new MenuEntity();
		menu.setMenuId(3);
		menu.setMenuName("菜单管理");
		menu.setMenuType(1);
		menu.setParentId(1);
		menu.setMenuIcon("fa fa-th-list");
		menu.setMenuUrl("sys/menu/");

		Map<String, Object> userAttributes = new HashMap<>(1);
		userAttributes.put("icon", user.getMenuIcon());
		userAttributes.put("url", user.getMenuUrl());
		Tree userTree = Tree.newBuilder().setId(user.getMenuId() + "")
				.setText(user.getMenuName()).setAttributes(userAttributes)
				.setParentId(user.getParentId() + "").setHasParent(true)
				.build();

		Map<String, Object> menuAttributes = new HashMap<>(1);
		menuAttributes.put("icon", menu.getMenuIcon());
		menuAttributes.put("url", menu.getMenuUrl());
		Tree menuTree = Tree.newBuilder().setId(menu.getMenuId() + "")
				.setText(menu.getMenuName()).setAttributes(menuAttributes)
				.setParentId(menu.getParentId() + "").setHasParent(true)
				.build();

		Map<String, Object> sysAttributes = new HashMap<>(1);
		sysAttributes.put("icon", sys.getMenuIcon());
		sysAttributes.put("url", sys.getMenuUrl());
		List<Tree> sysChilds = new ArrayList<>();
		sysChilds.add(userTree);
		sysChilds.add(menuTree);
		Tree sysTree = Tree.newBuilder().setId(sys.getMenuId() + "")
				.setText(sys.getMenuName()).setAttributes(sysAttributes)
				.setChildren(sysChilds).setHasParent(true).setHasChildren(true)
				.setParentId(user.getParentId() + "").build();

		Map<String, Object> rootAttributes = new HashMap<>(1);
		rootAttributes.put("icon", root.getMenuIcon());
		rootAttributes.put("url", root.getMenuIcon());
		List<Tree> rootChilds = new ArrayList<>();
		rootChilds.add(sysTree);
		Tree rootTree = Tree.newBuilder().setId(root.getMenuId() + "")
				.setText(root.getMenuName()).setAttributes(rootAttributes)
				.setChildren(rootChilds).setHasParent(false)
				.setHasChildren(true).setParentId(user.getParentId() + "")
				.build();
		return rootTree;
	}

	@Override
	public List<MenuEntity> queryAll() {
		//系统管理
		MenuEntity sys = new MenuEntity();
		sys.setMenuId(1);
		sys.setMenuName("系统管理");
		sys.setParentId(0);
		sys.setMenuType(0);
		sys.setMenuIcon("fa fa-desktop");

		MenuEntity user = new MenuEntity();
		user.setMenuId(2);
		user.setMenuName("用户管理");
		user.setMenuType(1);
		user.setParentId(1);
		user.setMenuIcon("fa fa-user");
		user.setMenuUrl("sys/user/");

		MenuEntity menu = new MenuEntity();
		menu.setMenuId(3);
		menu.setMenuName("菜单管理");
		menu.setMenuType(1);
		menu.setParentId(1);
		menu.setMenuIcon("fa fa-th-list");
		menu.setMenuUrl("sys/menu/");
		List<MenuEntity> list = new ArrayList<>();
		list.add(sys);
		list.add(user);
		list.add(menu);
		return list;
	}
}
