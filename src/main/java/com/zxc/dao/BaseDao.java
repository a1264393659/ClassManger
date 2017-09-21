package com.zxc.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.zxc.query.BaseQuery;
import com.zxc.query.PageList;

/**
 * 
 * 公共的dao类，不能写成抽象类，因为需要实例化
 * 
 * 
 */
public class BaseDao<T> extends HibernateDaoSupport {
	public void save(T t) {
		getHibernateTemplate().save(t);
	}

	public void update(T t) {
		getHibernateTemplate().update(t);
	}

	/**
	 * 
	 * @param Serializable
	 *            id:通用一些，Long,String
	 */
	public void delete(Class<T> entityClass, Serializable id) {
		T t = get(entityClass, id);
		if (t != null) {
			getHibernateTemplate().delete(t);// 不能写成delete(id)
		}
	}

	public T get(Class<T> entityClass, Serializable id) {
		return getHibernateTemplate().get(entityClass, id);
	}

	public List<T> getAll(Class<T> entityClass) {
		return getHibernateTemplate().loadAll(entityClass);
	}

	public PageList findByQuery(final BaseQuery baseQuery) {

		Long count = getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback<Long>() {

					@Override
					public Long doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(baseQuery
								.getCountHql());
						// 添加查询条件
						List paramList = baseQuery.getParamList();
						for (int i = 0; i < paramList.size(); i++) {
							// jdbc.setObject(i+1,)
							query.setParameter(i, paramList.get(i));
						}
						return (Long) query.uniqueResult();
					}

				});
		// 4.优化
		if (count.intValue() == 0) {
			return new PageList();
		}
		final PageList pageList = new PageList(baseQuery.getCurrentPage(),
				baseQuery.getPageSize(), count.intValue());
		// 5.limit
		List rows = getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback<List>() {

					@Override
					public List doInHibernate(Session session)
							throws HibernateException, SQLException {
						// ***************修改为limit******************
						Query query = session.createQuery(baseQuery
								.getLimitHql());
						// 添加查询条件
						List paramList = baseQuery.getParamList();
						for (int i = 0; i < paramList.size(); i++) {
							// jdbc.setObject(i+1,)
							query.setParameter(i, paramList.get(i));
						}
						// 处理分页
						// 添加2个方法
						// **********************必须使用经过处理后的pageList*************************
						int firstResult = (pageList.getCurrentPage() - 1)
								* pageList.getPageSize();// 从哪个索引取数据
						int maxResults = pageList.getPageSize();// 取多少条
						query.setFirstResult(firstResult).setMaxResults(
								maxResults);
						return query.list();
					}

				});
		pageList.setRows(rows);
		return pageList;
	}

	public List findByHql(String hql, Object... objects) {
		return getHibernateTemplate().find(hql, objects);
	}

	public List findByHql(String hql) {
		System.out.println("asdfds"+hql);
		return getHibernateTemplate().find(hql);
	}

	// 没有条件参数的可以使用此方法
	public List findCacheByHql(final String hql) {
		return getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback<List>() {
					@Override
					public List doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						// 查询缓存生效
						query.setCacheable(true);
						return query.list();
					}
				});
	}

}
