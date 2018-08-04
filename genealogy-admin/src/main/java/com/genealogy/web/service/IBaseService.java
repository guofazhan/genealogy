package com.genealogy.web.service;

import com.genealogy.common.Page;
import com.genealogy.web.vo.PageReqVo;

/**
 * 基础服务
 * @author G2Y
 * @version [版本号, 2018/8/4 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface IBaseService {

    /**
     * 删除
     * @param id
     * @return
     */
    int delete(Integer id);


    /**
     * 保存
     * @param entity
     * @return
     */
    <T>  int save(T entity);

    /**
     * 更新
     * @param entity
     * @return
     */
    <T> int update(T entity);

    /**
     * 查询单条信息
     * @param id
     * @return
     */
    <T> T get(Integer id);

    /**
     * 分页查询
     * @param vo
     * @param <T>
     * @param <E>
     * @return
     */
    <T, E extends PageReqVo> Page<T> page(E vo);
}
