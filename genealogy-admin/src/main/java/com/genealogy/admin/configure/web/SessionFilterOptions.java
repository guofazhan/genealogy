package com.genealogy.admin.configure.web;

import java.util.List;

/**
 * 会话拦截器参数
 *
 * @author guofazhan
 * @version [版本号, 2018/7/25 0025 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class SessionFilterOptions extends FilterOptions {

	/**
	 * 排序集合
	 */
	private List<String> exclusions;

	public List<String> getExclusions() {
		return exclusions;
	}

	public void setExclusions(List<String> exclusions) {
		this.exclusions = exclusions;
	}

	@Override
	public String toString() {
		return "SessionFilterOptions{" + "exclusions=" + exclusions + "} "
				+ super.toString();
	}
}
