package com.genealogy.web.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 地区实体
 * @author G2Y
 * @version [版本号, 2018/7/29 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@TableName("sys_area")
public class AreaEntity extends BaseEntity {

    /**
     * 地区ID
     */
    @TableId(value = "ID",type = IdType.AUTO)
    private Integer areaId;

    /**
     * 地区编码
     */
    @TableField(value = "CODE")
    private String areaCode;

    /**
     * 父ID
     */
    @TableField(value = "PARENT_ID")
    private Integer parentId;

    /**
     * 地区名
     */
    @TableField(value = "NAME")
    private String areaName;

    /**
     * 排序字段
     */
    @TableField(value = "SORT")
    private Integer sort;

    /**
     * 描述
     */
    @TableField(value = "REMARK")
    private String remark;

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "AreaEntity{" +
                "areaId=" + areaId +
                ", areaCode='" + areaCode + '\'' +
                ", parentId=" + parentId +
                ", areaName='" + areaName + '\'' +
                ", sort=" + sort +
                ", remark='" + remark + '\'' +
                "} " + super.toString();
    }
}
