package com.genealogy.admin.web.controller;

import com.genealogy.admin.web.model.UserEntity;
import com.genealogy.admin.web.shiro.ShiroHelper;

/**
 * @author guofazhan
 * @version [版本号, 2018/7/26 0026 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class BaseController {

	public UserEntity getLoginUser() {
		return ShiroHelper.getUser();
	}


}
