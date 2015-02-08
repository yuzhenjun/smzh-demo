package com.smzh.security.bean;


public class NodeState {
	/**
	 * 节点处于关闭状态，这表示节点为非叶子节点
	 */
	public static final Boolean STATE_CLOSED = false;

	/**
	 * 节点处于打开状态，这表示节点为非叶子节点
	 */
	public static final Boolean STATE_OPEN = true;

	/**
	 * 节点选中
	 */
	public static final Boolean STATE_SELECTED = true;

	/**
	 * 节点选中
	 */
	public static final Boolean STATE_UNSELECTED = false;
	
	
	private boolean selected=false; 
	
	private boolean opened;

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isOpened() {
		return opened;
	}

	public void setOpened(boolean opened) {
		this.opened = opened;
	}

	



}
