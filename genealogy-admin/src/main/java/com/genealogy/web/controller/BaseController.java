package com.genealogy.web.controller;

import com.genealogy.web.common.shiro.ShiroHelper;
import com.genealogy.web.model.UserEntity;

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
