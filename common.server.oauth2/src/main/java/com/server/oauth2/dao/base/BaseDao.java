package com.server.oauth2.dao.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


/**
 * 数据库通用类
 * @author zhenjun
 *
 * @param <T>
 */
public class BaseDao<T> extends HibernateDaoSupport implements IBaseDao<T>{
	
	private Class<T> entityClass;
	
	
	@SuppressWarnings("unchecked")
	public BaseDao() {
		Type type = getClass().getGenericSuperclass();
		Type[] trueType = ((ParameterizedType) type).getActualTypeArguments();
		this.entityClass = (Class<T>) trueType[0];
	}

	/**
	 * 保存实体类
	 * @param entity
	 */
	public void save(T entity){
		this.getHibernateTemplate().save(entity);
	}
	
	/**
	 * 更新实体类
	 * @param entity
	 */
	public void update(T entity){
		this.getHibernateTemplate().update(entity);
	}
	
	/**
	 * 删除实体类
	 * @param entity
	 */
	public void delete(T entity){
		this.getHibernateTemplate().delete(entity);
	}
	
	/**
	 * 查询
	 * @param hql
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(String hql,Object...values){
		return (List<T>) this.getHibernateTemplate().find(hql, values);
	}
	
	/**
	 * 查询唯一返回对象
	 * @param hql
	 * @param values
	 * @return
	 */
	public T findUnique(String hql,Object...values){
		List<T> results = this.find(hql, values);
		if(null != results && results.size() > 0){
			return results.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 通过参数查询
	 * @param hql
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByParams(final String hql,final Map<String,Object> params){
		return (List<T>) this.getHibernateTemplate().execute(new HibernateCallback<T>() {

			@SuppressWarnings("rawtypes")
			public T doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				if(null != params){
					for(String key : params.keySet()){
						if(params.get(key) instanceof List){
							query.setParameterList(key, (List) params.get(key));
						}else{
							query.setParameter(key, params.get(key));
						}
					}
				}
				return (T) query.list();
			}
		});
	}
	
	/**
	 * 通过参数执行hql
	 * @param hql
	 * @param params
	 */
	public void executeByParams(final String hql,final Map<String,Object> params){
		this.getHibernateTemplate().execute(new HibernateCallback<T>() {

			@SuppressWarnings("rawtypes")
			public T doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				if(null != params){
					for(String key : params.keySet()){
						if(params.get(key) instanceof List){
							query.setParameterList(key, (List) params.get(key));
						}else{
							query.setParameter(key, params.get(key));
						}
					}
				}
				query.executeUpdate();
				return null;
			}
		});
	}
	
	
	/**
	 * 执行hql
	 * @param hql
	 * @param values
	 */
	public void execute(final String hql,final Object...values){
		this.getHibernateTemplate().execute(new HibernateCallback<T>() {
			public T doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(hql);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}
				query.executeUpdate();
				return null;
			}
		});
	}
	
	/**
	 * 使用sql查询
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findBySql(final String sql){
	    return (List<T>) getHibernateTemplate().execute(new HibernateCallback<T>() {
			public T doInHibernate(Session session) throws HibernateException,
					SQLException {
				return null;
			}
		});
	}
	
	
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public T findById(long id){
		return  this.getHibernateTemplate().get(entityClass, id);
	}
	
	
	
	/**
	 * 通过多个ID查询
	 * @param ids
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByIds(final List<Long> ids){
		final String hql = "from "+this.entityClass.getSimpleName()+" t where t.id in (:ids)";
		return (List<T>) this.getHibernateTemplate().execute(new HibernateCallback<T>() {

			public T doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(hql);
				query.setParameterList("ids", ids);
				return (T) query.list();
			}
		});
	}
	
	
	/**
	 * 根据ID删除
	 * @param id
	 */
	public void delete(final long id){
		final String hql = "delete from "+this.entityClass.getSimpleName()+" t where t.id =:id";
		this.getHibernateTemplate().execute(new HibernateCallback<T>() {

			public T doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(hql);
				query.setParameter("id", id);
				query.executeUpdate();
				return null;
			}
		});
	}
	
	public void deleteByIds(final List<Long> ids){
		final String hql = "delete from "+this.entityClass.getSimpleName()+" t where t.id in (:ids)";
		this.getHibernateTemplate().execute(new HibernateCallback<T>() {
			public T doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(hql);
				query.setParameterList("ids", ids);
				query.executeUpdate();
				return null;
			}
		});
	}
	
	
	
	/**
	 * 查询所有数据
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAll(){
		String hql = "from "+this.entityClass.getSimpleName();
		return (List<T>) this.getHibernateTemplate().find(hql);
	}

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
}


