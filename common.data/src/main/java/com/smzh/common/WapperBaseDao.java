package com.smzh.common;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * 实现分页
 * @author zhenjun
 *
 * @param <T>
 */
public class WapperBaseDao<T> extends BaseDao<T> implements IWapperBaseDao<T>{

	public WapperBaseDao(){
		super();
	}

	public List<T> find(final String hql, final int firstResult, final int maxResults,final Object ...values) {
	     return this.getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
			@SuppressWarnings("unchecked")
			public List<T> doInHibernate(Session session)throws HibernateException, SQLException {
				 Query query = session.createQuery(hql);  
				 if (values != null) {
						for (int i = 0; i < values.length; i++) {
							query.setParameter(i, values[i]);
						}
					}
	                query.setMaxResults(maxResults);  
	                //设置起点  
	                query.setFirstResult(firstResult);  
	                return query.list();  
			}
		});
	}

	@SuppressWarnings("unchecked")
	public int getCount(String hql) {
		List<T>list=this.getHibernateTemplate().find(hql);
		if(list!=null&&list.size()>0){
			return list.size();
		}
		return 0;
	}
}
