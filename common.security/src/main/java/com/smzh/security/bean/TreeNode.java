package com.smzh.security.bean;

import java.util.List;

public class TreeNode {

	public static final String STATE_SELECT="";

	/**
	 * 子节点列表。若当前节点为叶子节点或处于关闭状态，则为 null
	 */
	private List<TreeNode> children;

	/**
	 * 附带的数据
	 */
	private Object data;

	/**
	 * 图标
	 */
	private String icon;

	/**
	 * ID。在一棵树内节点 ID 必须唯一
	 */
	private String id;

	/**
	 * 展开状态。对于叶子节点来说为 null
	 */
	private NodeState state;

	/**
	 * 标题
	 */
	private String text;

	public TreeNode() {
	}

	public TreeNode(String id, String text) {
		this.id = id;
		this.text = text;
	}

	public TreeNode getChild(int index) {
		return children.get(index);
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public Object getData() {
		return data;
	}

	public String getIcon() {
		return icon;
	}

	public String getId() {
		return id;
	}

	
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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

	public NodeState getState() {
		return state;
	}

	public void setState(NodeState state) {
		this.state = state;
	}
	
	
}
