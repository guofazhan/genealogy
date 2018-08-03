package com.genealogy.web.configure.web;

/**
 * @author guofazhan
 * @version [版本号, 2018/7/25 0025 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class FilterOptions {

	/**
	 * 名称
	 */
	private String name;

	/**
	 * URL
	 */
	private String urlPatterns;

	/**
	 *
	 */
	private int order = 1;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrlPatterns() {
		return urlPatterns;
	}

	public void setUrlPatterns(String urlPatterns) {
		this.urlPatterns = urlPatterns;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "FilterOptions{" + "name='" + name + '\'' + ", urlPatterns='"
				+ urlPatterns + '\'' + ", order=" + order + '}';
	}
}
