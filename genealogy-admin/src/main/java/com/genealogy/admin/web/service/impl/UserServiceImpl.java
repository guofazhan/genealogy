package com.genealogy.admin.web.service.impl;

import com.genealogy.admin.web.dao.UserMapper;
import com.genealogy.admin.web.model.UserEntity;
import com.genealogy.admin.web.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户服务实现类
 *
 * @author guofazhan
 * @version [版本号, 2018/7/26 0026 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

	@Resource
	private UserMapper userMapper;

	@Override
	public UserEntity login(String loginName, String password) {
		UserEntity userEntity = new UserEntity();
		userEntity.setLoginName(loginName);
		userEntity.setIsLock(0);
		userEntity.setPassword(password);

		return userEntity;
		//return userMapper.queryUserByNameAndPassword(loginName, password);
	}
}
