package com.genealogy.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * @author guofazhan
 * @version [版本号, 2018/7/25 0025 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
public class IndexController {

	@GetMapping("/")
	public String index(Map<String,Object> map) {
		map.put("msg", "Hello Thymeleaf");
		return "index";
	}
}
