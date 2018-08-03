package com.genealogy.web.service;

import com.genealogy.web.model.UserEntity;
import com.genealogy.web.vo.UserReqVo;
import com.genealogy.common.Page;

import java.util.List;

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


	/**
	 * 查询所有用户
	 * @return
	 */
	List<UserEntity> queryAll();


	/**
	 * 分页查询
	 * @param vo
	 * @return
	 */
	Page<UserEntity> page(UserReqVo vo);

	/**
	 * 根据ID查询用户
	 * @param id
	 * @return
	 */
	UserEntity get(Integer id);

	/**
	 * 根据登录用户名查询用户
	 * @param loginName
	 * @return
	 */
	UserEntity queryUserByName(String loginName);

	/**
	 * 保存用户
	 * @param entity
	 * @return
	 */
	int save(UserEntity entity);

	/**
	 * 更新用户
	 * @param entity
	 * @return
	 */
	int update(UserEntity entity);

	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	int delete(Integer id);
}
