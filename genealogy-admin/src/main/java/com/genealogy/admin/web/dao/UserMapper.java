package com.genealogy.admin.web.dao;

import com.genealogy.admin.web.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息数据库操作接口
 *
 * @author guofazhan
 * @version [版本号, 2018/7/26 0026 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface UserMapper {

	/**
	 * 查询用户根据用户名以及密码
	 *
	 * @param loginName
	 * @param password
	 * @return
	 */
	UserEntity queryUserByNameAndPassword(String loginName, String password);
}
