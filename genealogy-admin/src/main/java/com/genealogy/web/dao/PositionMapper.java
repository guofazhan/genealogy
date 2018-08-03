package com.genealogy.web.dao;

import com.genealogy.web.model.PositionEntity;

import java.util.List;

/**
 * 职位数据操作接口
 * @author G2Y
 * @version [版本号, 2018/7/28 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface PositionMapper {

    /**
     * 查询所有职位
     * @return
     */
    List<PositionEntity> queryAll();

    /**
     * 查询职位信息根据ID
     * @param id
     * @return
     */
    PositionEntity get(Integer id);

    /**
     * 保存职位信息
     * @param entity
     */
    void save(PositionEntity entity);

    /**
     * 更新职位信息
     * @param entity
     * @return
     */
    int update(PositionEntity entity);

    /**
     * 删除职位信息
     * @param id
     * @return
     */
    int delete(Integer id);
}
