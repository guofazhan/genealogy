package com.genealogy.web.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 操作记录表
 * @author G2Y
 * @version [版本号, 2018/8/4 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@TableName("sys_operation_record")
public class OperationRecordEntity extends BaseEntity{

    /**
     * ID
     */
    @TableId(value = "ID")
    private Integer recordId;


    /**
     * 耗时
     */

    @TableField(value = "SPEND")
    private Long spend;

    /**
     * 操作类型
     */
    @TableField(value = "OPERATION")
    private String operation;

    /**
     * 描述
     */
    @TableField(value = "REMARK")
    private String remark;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Long getSpend() {
        return spend;
    }

    public void setSpend(Long spend) {
        this.spend = spend;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "OperationRecordEntity{" +
                "recordId=" + recordId +
                ", spend=" + spend +
                ", operation='" + operation + '\'' +
                ", remark='" + remark + '\'' +
                "} " + super.toString();
    }
}
