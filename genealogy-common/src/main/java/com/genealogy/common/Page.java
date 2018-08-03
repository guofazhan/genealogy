package com.genealogy.common;

import java.util.List;

/**
 * 分页结果返回实体
 *
 * @author guofazhan
 * @version [版本号, 2018/8/3 0003 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class Page<T> {

	/**
	 * 分页数据
	 */
	List<T> rows;

	/**
	 * 总数
	 */
	private int total;

	/**
	 * 当前页
	 */
	private int index;

	/**
	 * 页大小
	 */
	private int pageSize;

	public Page(List<T> rows, int total, int index, int pageSize) {
		this.rows = rows;
		this.total = total;
		this.index = index;
		this.pageSize = pageSize;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "Page{" + "rows=" + rows + ", total=" + total + ", index="
				+ index + ", pageSize=" + pageSize + '}';
	}
}
