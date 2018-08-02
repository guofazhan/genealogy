package com.genealogy.admin.web.dao;

import com.genealogy.admin.web.model.UserEntity;
import com.genealogy.admin.web.vo.UserReqVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

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
	 * @param loginMap
	 * @return
	 */
	UserEntity queryUserByNameAndPassword(Map<String,String> loginMap);

	/**
	 * 根据参数查询
	 * @param vo
	 * @return
	 */
	List<UserEntity> list(UserReqVo vo );

	/**
	 * 查询总数
	 * @param vo
	 * @return
	 */
	int count(UserReqVo vo);

	/**
	 * 查询所有用户
	 * @return
	 */
	List<UserEntity> queryAll();

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
