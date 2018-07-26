package com.genealogy.admin.web.service;

import com.genealogy.admin.web.model.UserEntity;

/**
 * 用户服务
 *
 * @author guofazhan
 * @version [版本号, 2018/7/26 0026 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface IUserService {

	/**
	 * 用户登录
	 *
	 * @param loginName
	 * @param password
	 * @return
	 */
	UserEntity login(String loginName, String password);
}
