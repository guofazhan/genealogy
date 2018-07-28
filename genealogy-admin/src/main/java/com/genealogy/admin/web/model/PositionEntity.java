package com.genealogy.admin.web.model;

/**
 * 职位实体
 *
 * @author guofazhan
 * @version [版本号, 2018/7/27 0027 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class PositionEntity extends BaseEntity{

    /**
     * 职位ID
     */
    private Integer positionId;

    /**
     * 职位名称
     */
    private String positionName;

    /**
     * 描述
     */
    private String remark;

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "PositionEntity{" +
                "positionId=" + positionId +
                ", positionName='" + positionName + '\'' +
                ", remark='" + remark + '\'' +
                "} " + super.toString();
    }
}
