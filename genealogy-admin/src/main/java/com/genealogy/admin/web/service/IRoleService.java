package com.genealogy.admin.web.service;

import com.genealogy.admin.web.model.RoleEntity;
import com.genealogy.admin.web.vo.RoleAddAndEditReqVo;

import java.util.List;

/**
 * 角色服务
 * @author G2Y
 * @version [版本号, 2018/7/28 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface IRoleService {

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
     * @param vo
     */
    void save(RoleAddAndEditReqVo vo);

    /**
     * 更新角色信息
     * @param vo
     * @return
     */
    int update(RoleAddAndEditReqVo vo);

    /**
     * 删除角色信息
     * @param id
     * @return
     */
    int delete(Integer id);
}
