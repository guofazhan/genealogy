package com.genealogy.admin.web.model;

/**
 * 实体的基础信息
 *
 * @author guofazhan
 * @version [版本号, 2018/7/26 0026 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class BaseEntity {

	/**
	 * 删除标识 0 未删除、1删除
	 */
	private int isDel;

	/**
	 * 创建人
	 */
	private String createBy;

	/**
	 * 创建时间
	 */
	private String createTime;

	/**
	 * 最后修改人
	 */
	private String lastModifyBy;

	/**
	 * 最后修改时间
	 */
	private String lastModifyTime;

	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastModifyBy() {
		return lastModifyBy;
	}

	public void setLastModifyBy(String lastModifyBy) {
		this.lastModifyBy = lastModifyBy;
	}

	public String getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(String lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	@Override
	public String toString() {
		return "BaseEntity{" + "isDel=" + isDel + ", createBy='" + createBy
				+ '\'' + ", createTime='" + createTime + '\''
				+ ", lastModifyBy='" + lastModifyBy + '\''
				+ ", lastModifyTime='" + lastModifyTime + '\'' + '}';
	}
}
