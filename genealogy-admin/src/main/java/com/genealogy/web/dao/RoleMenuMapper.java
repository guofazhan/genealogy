package com.genealogy.web.dao;

import com.genealogy.web.model.RoleMenuEntity;

import java.util.List;

/**
 * @author G2Y
 * @version [版本号, 2018/7/28 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface RoleMenuMapper {

    /**
     * 批量保存关系信息
     * @param entitys
     * @return
     */
    int batchSave(List<RoleMenuEntity> entitys);

    /**
     * 通过角色ID删除关系
     * @param id
     * @return
     */
    int deleteByRoleId(Integer id);

    /**
     * 通过菜单ID删除关系
     * @param id
     * @return
     */
    int deleteByMenuId(Integer id);

    /**
     * 根据角色ID查询菜单集合
     * @param id
     * @return
     */
    List<Integer> queryMenuIdsByRoleId(Integer id);
}
