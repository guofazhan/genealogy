package com.genealogy.web.service.impl;

import com.genealogy.web.dao.RoleMapper;
import com.genealogy.web.dao.RoleMenuMapper;
import com.genealogy.web.model.EntityHelper;
import com.genealogy.web.model.RoleEntity;
import com.genealogy.web.model.RoleMenuEntity;
import com.genealogy.web.service.IRoleService;
import com.genealogy.web.vo.RoleAddAndEditReqVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author G2Y
 * @version [版本号, 2018/7/28 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("roleService")
public class RoleServiceImpl implements IRoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Override
    public List<RoleEntity> queryAll() {
        return roleMapper.queryAll();
    }

    @Override
    public RoleEntity get(Integer roleId) {
        return roleMapper.get(roleId);
    }

    @Transactional(rollbackFor={Exception.class})
    @Override
    public void save(RoleAddAndEditReqVo vo) {
        RoleEntity roleEntity = buildRole(vo);
        //补全实体信息
        EntityHelper.compleSaveEntity(roleEntity);
        roleMapper.save(roleEntity);
        batchSaveRoleMenuEntity(vo.getMenuIds(),roleEntity.getRoleId());
    }

    @Transactional(rollbackFor={Exception.class})
    @Override
    public int update(RoleAddAndEditReqVo vo) {
        RoleEntity roleEntity = buildRole(vo);
        //补全信息
        EntityHelper.compleUpdateEntity(roleEntity);
        int update = roleMapper.update(roleEntity);
        roleMenuMapper.deleteByRoleId(roleEntity.getRoleId());
        batchSaveRoleMenuEntity(vo.getMenuIds(),roleEntity.getRoleId());
        return update;
    }

    @Transactional(rollbackFor={Exception.class})
    @Override
    public int delete(Integer id) {
        int del = roleMapper.delete(id);
        roleMenuMapper.deleteByRoleId(id);
        return del;
    }

    /**
     * 批量保存关联关系
     * @param menuIds
     * @param roleId
     */
    protected void batchSaveRoleMenuEntity(List<Integer> menuIds,Integer roleId){
        if(null != menuIds && !menuIds.isEmpty()){
            //添加关系表
            List<RoleMenuEntity> rms = new ArrayList<>();
            for (Integer menuId : menuIds) {
                //除去顶级虚拟节点
                if(menuId == 0){
                    continue;
                }
                RoleMenuEntity roleMenuEntity = new RoleMenuEntity();
                roleMenuEntity.setRoleId(roleId);
                roleMenuEntity.setMenuId(menuId);
                EntityHelper.compleSaveEntity(roleMenuEntity);
                rms.add(roleMenuEntity);
            }
            //批量添加关系
            roleMenuMapper.batchSave(rms);
        }
    }

    /**
     * 构建角色信息
     * @param vo
     * @return
     */
    protected RoleEntity buildRole(RoleAddAndEditReqVo vo){
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setIsLock(0);
        roleEntity.setRemark(vo.getRemark());
        roleEntity.setRoleName(vo.getRoleName());
        roleEntity.setRoleId(vo.getRoleId());
        return roleEntity;
    }
}
