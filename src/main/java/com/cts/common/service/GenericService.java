package com.cts.common.service;

import java.io.Serializable;
import java.util.List;

public interface GenericService<T, PK extends Serializable> {

	public T findOne(PK id);

	public List<T> findAll();

	public void create(T entity);

	public T update(T entity);

	public void delete(T entity);

	public void deleteById(PK entityId);
	
	public boolean isExist(String args);
}
