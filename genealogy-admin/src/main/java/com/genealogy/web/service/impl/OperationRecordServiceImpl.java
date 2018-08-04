package com.genealogy.web.service.impl;

import com.genealogy.common.Page;
import com.genealogy.web.dao.OperationRecordMapper;
import com.genealogy.web.model.EntityHelper;
import com.genealogy.web.model.OperationRecordEntity;
import com.genealogy.web.service.IOperationRecordService;
import com.genealogy.web.vo.OperationRecordReqVo;
import com.genealogy.web.vo.PageReqVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 操作记录服务实现类
 * @author G2Y
 * @version [版本号, 2018/8/4 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("operationRecordService")
public class OperationRecordServiceImpl implements IOperationRecordService {

    @Resource
    private OperationRecordMapper operationRecordMapper;

    @Override
    public int delete(Integer id) {
        return operationRecordMapper.deleteById(id);
    }

    @Override
    public <T> int save(T entity) {
        //保存操作记录
        EntityHelper.compleOperationRecord((OperationRecordEntity)entity);
        return operationRecordMapper.insert((OperationRecordEntity)entity);
    }

    @Override
    public <T> int update(T entity) {
        return 0;
    }

    /**
     * 分页查询
     * @param vo
     * @param <T>
     * @param <E>
     * @return
     */
    @Override
    public <T, E extends PageReqVo> Page<T> page(E vo) {
       List<OperationRecordEntity> list =  operationRecordMapper.list((OperationRecordReqVo)vo);
       int total = operationRecordMapper.count((OperationRecordReqVo)vo);
        return (Page<T>) new Page<>(list, total, 0, vo.getLimit());
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public OperationRecordEntity get(Integer id) {
        return operationRecordMapper.selectById(id);
    }
}
