package com.genealogy.admin.web.dao;

import com.genealogy.admin.web.model.AreaEntity;

import java.util.List;

/**
 * @author G2Y
 * @version [版本号, 2018/7/28 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface AreaMapper {

    /**
     * 查询所有地区信息
     *
     * @return
     */
    List<AreaEntity> queryAll();


    /**
     * 根据ID查询地区信息
     * @param id
     * @return
     */
    AreaEntity get(Integer id);

    /**
     * 保存地区信息
     * @param entity
     */
    int save(AreaEntity entity);

    /**
     * 更新地区信息
     * @param entity
     * @return
     */
    int update(AreaEntity entity);

    /**
     * 删除菜单根据地区ID(包含删除子地区信息)
     * @param id
     * @return
     */
    int delete(Integer id);
}
