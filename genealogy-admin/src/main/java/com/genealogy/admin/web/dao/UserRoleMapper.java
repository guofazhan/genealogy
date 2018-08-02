package com.genealogy.admin.web.dao;

import com.genealogy.admin.web.model.UserRoleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户角色关系数据操作接口
 *
 * @author guofazhan
 * @version [版本号, 2018/8/2 0002 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface UserRoleMapper {

	/**
	 * 批量保存关系信息
	 *
	 * @param entitys
	 * @return
	 */
	int batchSave(List<UserRoleEntity> entitys);


	/**
	 * 通过角色ID删除关系
	 * @param id
	 * @return
	 */
	int deleteByUserId(Integer id);


	/**
	 * 通过菜单ID删除关系
	 * @param id
	 * @return
	 */
	int deleteByRoleId(Integer id);


	/**
	 * 根据用户ID查询角色集合
	 * @param id
	 * @return
	 */
	List<Integer> queryRolesByUserId(Integer id);
}
