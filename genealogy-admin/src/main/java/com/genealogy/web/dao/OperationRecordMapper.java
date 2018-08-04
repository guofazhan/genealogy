package com.genealogy.web.dao;

import com.genealogy.web.common.SuperMapper;
import com.genealogy.web.model.OperationRecordEntity;
import com.genealogy.web.vo.OperationRecordReqVo;

import java.util.List;

/**
 * 操作记录数据接口
 * @author G2Y
 * @version [版本号, 2018/8/4 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface OperationRecordMapper  extends SuperMapper<OperationRecordEntity> {


    /**
     * 根据参数查询
     *
     * @param vo
     * @return
     */
    List<OperationRecordEntity> list(OperationRecordReqVo vo);


    /**
     * 查询总数
     *
     * @param vo
     * @return
     */
    int count(OperationRecordReqVo vo);
}
