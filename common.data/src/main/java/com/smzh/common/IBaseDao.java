package com.smzh.common;

import java.util.List;
import java.util.Map;
/**
 * 接口
 * @author zhenjun
 *
 * @param <T>
 */
public interface IBaseDao<T> {

	/**
	 * 保存实体类
	 * @param entity
	 */
	public void save(T entity);
	
	/**
	 * 更新实体类
	 * @param entity
	 */
	public void update(T entity);
	/**
	 * 删除实体类
	 * @param entity
	 */
	public void delete(T entity);
	
	/**
	 * 查询
	 * @param hql
	 * @param values
	 * @return
	 */
	public List<T> find(String hql,Object...values);
	
	/**
	 * 查询唯一返回对象
	 * @param hql
	 * @param values
	 * @return
	 */
	public T findUnique(String hql,Object...values);
	
	
	/**
	 * 执行hql
	 * @param hql
	 * @param values
	 */
	public void execute(final String hql,final Object...values);
	
	/**
	 * 通过参数执行hql
	 * @param hql
	 * @param params
	 */
	public void executeByParams(final String hql,final Map<String,Object> params);
	
	/**
	 * 使用sql查询
	 * @param sql
	 * @return
	 */
	public List<T> findBySql(final String sql);
	
	
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public T findById(long id);
	
	
	
	/**
	 * 通过多个ID查询
	 * @param ids
	 * @return
	 */
	public List<T> findByIds(final List<Long> ids);
	
	/**
	 * 通过参数查询
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<T> findByParams(final String hql,final Map<String,Object> params);
	
	
	/**
	 * 根据ID删除
	 * @param id
	 */
	public void delete(final long id);
	
	/**
	 * 根据ID删除
	 * @param ids
	 */
	public void deleteByIds(final List<Long> ids);
	
	
	
	/**
	 * 查询所有数据
	 * @return
	 */
	public List<T> getAll();
}
