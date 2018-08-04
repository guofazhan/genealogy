package com.genealogy.web.controller.sys;

import com.genealogy.common.aspect.annotation.Log;
import com.genealogy.web.model.MenuEntity;
import com.genealogy.web.service.IMenuService;
import com.genealogy.common.Tree;
import com.genealogy.common.aspect.annotation.ParamVailds;
import com.genealogy.common.message.ResponseMessage;
import com.genealogy.common.message.respcode.RespCode;
import com.genealogy.common.message.respcode.RespHelper;
import com.genealogy.web.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 菜单管理控制层
 *
 * @author guofazhan
 * @version [版本号, 2018/7/27 0027 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/sys/menu")
public class MenuController extends BaseController {

	private static final String PREFIX = "system/menu";

	@Autowired
	private IMenuService menuService;

	/**
	 * 菜单添加
	 *
	 * @param model
	 * @param model
	 * @return
	 */
	//@RequiresPermissions("sys:menu:add")
	@GetMapping("/add/{pId}")
	String add(Model model, @PathVariable("pId") Integer parentId) {
		model.addAttribute("parentId", parentId);
		if (parentId == 0) {
			model.addAttribute("parentName", "根目录");
		} else {
			model.addAttribute("parentName",
					menuService.get(parentId).getMenuName());
		}
		return PREFIX + "/add";
	}

	/**
	 * 菜单编辑
	 *
	 * @param model
	 * @param model
	 * @return
	 */
	//@RequiresPermissions("sys:menu:edit")
	@GetMapping("/edit/{id}")
	String edit(Model model, @PathVariable("id") Integer menuId) {
		MenuEntity menu = menuService.get(menuId);
		model.addAttribute("menu", menu);
		model.addAttribute("parentId", menu.getParentId());
		if (menu.getParentId() == 0) {
			model.addAttribute("parentName", "根目录");
		} else {
			model.addAttribute("parentName",
					menuService.get(menu.getParentId()).getMenuName());
		}
		return PREFIX + "/edit";
	}

	@RequiresPermissions("sys:menu:index")
	@GetMapping()
	String index(Model model) {
		return PREFIX + "/index";
	}

	@PostMapping(value = "/list")
	@ResponseBody
	public List<MenuEntity> list(Model model) {
		return menuService.queryAll();
	}

	/**
	 * 添加菜单信息
	 *
	 * @param entity
	 * @return
	 */
	@ParamVailds
	@Log("菜单添加")
	@RequiresPermissions("sys:menu:add")
	@PostMapping(value = "/save")
	@ResponseBody
	public ResponseMessage save(MenuEntity entity) {
		menuService.save(entity);
		return RespHelper.buildResponseMessage(RespCode.SUCCESS, null);
	}

	/**
	 * 更新菜单信息
	 *
	 * @param entity
	 * @return
	 */
	@Log("菜单编辑")
	@RequiresPermissions("sys:menu:edit")
	@PostMapping(value = "/edit")
	@ResponseBody
	public ResponseMessage update(MenuEntity entity) {
		if (menuService.update(entity) <= 0) {
			return RespHelper.buildResponseMessage(RespCode.COMM_FAIL, null);
		}
		return RespHelper.buildResponseMessage(RespCode.SUCCESS, null);
	}

	/**
	 * 删除ID
	 *
	 * @param id
	 * @return
	 */

	@Log("菜单删除")
	@RequiresPermissions("sys:menu:remove")
	@PostMapping(value = "/remove")
	@ResponseBody
	public ResponseMessage remove(@NotNull(message = "ID 不能为空") Integer id) {
		if (menuService.delete(id) <= 0) {
			return RespHelper.buildResponseMessage(RespCode.COMM_FAIL, null);
		}
		return RespHelper.buildResponseMessage(RespCode.SUCCESS, null);
	}

	/**
	 * 获取菜单树
	 *
	 * @return
	 */
	@PostMapping(value = "/tree")
	@ResponseBody
	public ResponseMessage tree() {
		Tree tree = menuService.queryMenuTree();
		return RespHelper.buildResponseMessage(RespCode.SUCCESS, tree);
	}

	/**
	 * 根据角色ID获取菜单树
	 *
	 * @param roleId
	 * @return
	 */
	@PostMapping("/tree/{roleId}")
	@ResponseBody
	ResponseMessage tree(@PathVariable("roleId") Integer roleId) {
		Tree tree = menuService.queryMenuTreeByRoleId(roleId);
		return RespHelper.buildResponseMessage(RespCode.SUCCESS, tree);
	}
}
