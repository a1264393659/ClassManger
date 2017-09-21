package com.zxc.service;

import java.io.Serializable;
import java.util.List;

import com.zxc.query.BaseQuery;
import com.zxc.query.PageList;

public interface IBaseService<T> {
	void save(T t);

	void update(T t);

	void delete(Serializable id);

	T get(Serializable id);

	List<T> getAll();

	PageList findByQuery(BaseQuery baseQuery);
}
