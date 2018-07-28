package com.genealogy.admin.web.dao;

import com.genealogy.admin.web.model.RoleEntity;

import java.util.List;

/**
 * @author G2Y
 * @version [版本号, 2018/7/28 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface RoleMapper {

    /**
     * 查询所有角色信息
     * @return
     */
    List<RoleEntity> queryAll();

    /**
     * 查询角色信息根据ID
     * @param roleId
     * @return
     */
    RoleEntity get(Integer roleId);

    /**
     * 保存角色信息
     * @param entity
     */
    void save(RoleEntity entity);

    /**
     * 更新角色信息
     * @param entity
     * @return
     */
    int update(RoleEntity entity);

    /**
     * 删除角色信息
     * @param id
     * @return
     */
    int delete(Integer id);
}
