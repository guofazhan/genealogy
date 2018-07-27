package com.genealogy.admin.web.controller.sys;

import com.genealogy.admin.web.controller.BaseController;
import com.genealogy.admin.web.model.MenuEntity;
import com.genealogy.admin.web.service.IMenuService;
import com.genealogy.common.annotation.ParamVailds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 菜单管理控制层
 *
 * @author guofazhan
 * @version [版本号, 2018/7/27 0027 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@ParamVailds
@Controller
@RequestMapping("/sys/menu")
public class MenuController extends BaseController {

	private static final String PREFIX = "system/menu";

	@Autowired
	private IMenuService menuService;

	/**
	 * 菜单管理入口
	 *
	 * @param model
	 * @return
	 */
	@GetMapping()
	String index(Model model) {
		return PREFIX + "/index";
	}

	@PostMapping(value = "/list")
	@ResponseBody
	public List<MenuEntity> list(Model model) {
		return menuService.queryAll();
	}
}
