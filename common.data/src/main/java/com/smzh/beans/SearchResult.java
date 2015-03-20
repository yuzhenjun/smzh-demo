package com.smzh.beans;

import java.util.List;

/**
 * 返回页面表格数据<br>
 * 
 */

public class SearchResult<T> {
	private int total;
	private List<T> rows;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
}
