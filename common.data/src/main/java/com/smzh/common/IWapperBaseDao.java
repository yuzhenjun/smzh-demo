package com.smzh.common;

import java.util.List;

/**
 * 分页
 * @author zhenjun
 *
 */
public interface IWapperBaseDao<T> {

	/**
	 * 实现查询分页
	 * @param hql
	 * @param firstResult
	 * @param maxResults
	 * @return
	 */
	public List<T> find(String hql,int firstResult,int maxResults,Object ...values);
	/**
	 * 获取总条目数
	 * @param hql
	 * @return
	 */
	public int getCount(String hql);
}
