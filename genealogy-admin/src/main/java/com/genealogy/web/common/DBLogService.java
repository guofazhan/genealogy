package com.genealogy.web.common;

import com.genealogy.common.log.ILogService;
import com.genealogy.web.model.OperationRecordEntity;
import com.genealogy.web.service.IOperationRecordService;
import com.genealogy.web.vo.LoginReqVo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

/**
 * 操作记录写入库
 * @author G2Y
 * @version [版本号, 2018/8/4 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class DBLogService implements ILogService {

    /**
     * 操作记录服务
     */
    private IOperationRecordService operationRecordService;

    public DBLogService(IOperationRecordService operationRecordService){
       this.operationRecordService = operationRecordService;
    }



    /**
     * @param operation 操作描述
     * @param point
     * @param time 耗时
     */
    @Async
    @Override
    public void log(String operation, ProceedingJoinPoint point, long time) {

        try {
            //构建日志实体
            OperationRecordEntity operationRecordEntity = new OperationRecordEntity();
            operationRecordEntity.setOperation(operation);
            operationRecordEntity.setRemark(operation);
            operationRecordEntity.setSpend(time);
            Object[] args = point.getArgs();
            if(null != args){
                for(Object arg : args){
                    if(arg instanceof LoginReqVo){
                        operationRecordEntity.setCreateBy(((LoginReqVo)arg).getLoginName());
                        operationRecordEntity.setLastModifyBy(((LoginReqVo)arg).getLoginName());
                    }
                }
            }
            //记录日志信息
            operationRecordService.save(operationRecordEntity);
        }catch (Throwable e){
            LoggerFactory.getLogger(DBLogService.class).error("DB Log:",e);
        }

    }
}
