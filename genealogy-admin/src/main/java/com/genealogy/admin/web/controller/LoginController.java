package com.genealogy.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 登录控制层
 *
 * @author guofazhan
 * @version [版本号, 2018/7/26 0026 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
public class LoginController extends BaseController {

	/**
	 * 进入登录页面
	 *
	 * @return
	 */
	@GetMapping("/login")
	public String index() {
		return "login";
	}
}
