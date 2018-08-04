package com.genealogy.web.vo;

/**
 * 操作记录的分页查询实体
 * @author G2Y
 * @version [版本号, 2018/8/4 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class OperationRecordReqVo extends PageReqVo{

    /**
     * 操作类型
     */
    private String operation;

    /**
     * 操作人
     */
    private String createBy;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Override
    public String toString() {
        return "OperationRecordReqVo{" +
                "operation='" + operation + '\'' +
                ", createBy='" + createBy + '\'' +
                "} " + super.toString();
    }
}
