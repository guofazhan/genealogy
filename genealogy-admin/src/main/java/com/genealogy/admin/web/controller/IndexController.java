package com.genealogy.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 公共请求部分
 *
 * @author guofazhan
 * @version [版本号, 2018/7/25 0025 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
public class IndexController extends BaseController {
	/**
	 * 根路径请求
	 *
	 * @return
	 */
	@GetMapping("/")
	public String index() {
		return "redirect:/login";
	}

	/**
	 * 主页面请求
	 *
	 * @param model
	 * @return
	 */
	@GetMapping({ "/home" })
	public String home(Model model) {
		return "home";
	}
}
