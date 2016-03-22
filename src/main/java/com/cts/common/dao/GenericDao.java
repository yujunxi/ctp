package com.cts.common.dao;

import java.io.Serializable;
import java.util.List;

/*
 * 通用的操作接口
 */
public interface GenericDao<T , PK extends Serializable> {

	public T get(PK id);

	public List<T> findAll();

	public void create(T entity);

	public T update(T entity);

	public void delete(T entity);

	public void deleteById(PK entityId);
	
	public boolean isExist(String args);

}