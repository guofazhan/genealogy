package com.genealogy.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 树结构实体
 *
 * @author guofazhan
 * @version [版本号, 2018/7/27 0027 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public final class Tree {
	/**
	 * 节点ID
	 */
	private String id;

	/**
	 * 节点显示文本
	 */
	private String text;

	/**
	 * 是否选中
	 */
	private boolean checked = false;

	/**
	 * 是否有父节点
	 */
	private boolean hasParent = false;

	/**
	 * 是否有子节点
	 */
	private boolean hasChildren = false;

	/**
	 * 父节点ID
	 */
	private String parentId;

	/**
	 * 子节点集合
	 */
	private List<Tree> children = new ArrayList<>();

	/**
	 * 属性信息
	 */
	private Map<String, Object> attributes;

	/**
	 * 节点状态，open closed
	 */
	private Map<String, Object> state;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isHasParent() {
		return hasParent;
	}

	public void setHasParent(boolean hasParent) {
		this.hasParent = hasParent;
	}

	public boolean isHasChildren() {
		return hasChildren;
	}

	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public List<Tree> getChildren() {
		return children;
	}

	public void setChildren(List<Tree> children) {
		this.children = children;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public Map<String, Object> getState() {
		return state;
	}

	public void setState(Map<String, Object> state) {
		this.state = state;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	protected Tree(String id, String text, Map<String, Object> state,
			boolean checked, Map<String, Object> attributes,
			List<Tree> children, boolean isParent, boolean isChildren,
			String parentID) {
		super();
		this.id = id;
		this.text = text;
		this.state = state;
		this.checked = checked;
		this.attributes = attributes;
		this.children = children;
		this.hasParent = isParent;
		this.hasChildren = isChildren;
		this.parentId = parentID;
	}

	@Override
	public String toString() {
		return "Tree{" + "id='" + id + '\'' + ", text='" + text + '\''
				+ ", checked=" + checked + ", hasParent=" + hasParent
				+ ", hasChildren=" + hasChildren + ", parentId='" + parentId
				+ '\'' + ", children=" + children + ", attributes=" + attributes
				+ ", state=" + state + '}';
	}

	/**
	 * 构建器
	 */
	public static class Builder {
		private String id;
		private String text;
		private boolean checked = false;
		private boolean hasParent = false;
		private boolean hasChildren = false;
		private String parentId;
		private List<Tree> children = new ArrayList<>();
		private Map<String, Object> attributes;
		private Map<String, Object> state;

		protected Builder() {

		}

		public Builder setId(String id) {
			this.id = id;
			return this;
		}

		public Builder setText(String text) {
			this.text = text;
			return this;
		}

		public Builder setChecked(boolean checked) {
			this.checked = checked;
			return this;
		}

		public Builder setHasParent(boolean hasParent) {
			this.hasParent = hasParent;
			return this;
		}

		public Builder setHasChildren(boolean hasChildren) {
			this.hasChildren = hasChildren;
			return this;
		}

		public Builder setParentId(String parentId) {
			this.parentId = parentId;
			return this;
		}

		public Builder setChildren(List<Tree> children) {
			this.children = children;
			return this;
		}

		public Builder setAttributes(Map<String, Object> attributes) {
			this.attributes = attributes;
			return this;
		}

		public Builder setState(Map<String, Object> state) {
			this.state = state;
			return this;
		}

		/**
		 * 构建器
		 *
		 * @return
		 */
		public Tree build() {
			return new Tree(id, text, state, checked, attributes, children,
					hasParent, hasChildren, parentId);
		}
	}
}
