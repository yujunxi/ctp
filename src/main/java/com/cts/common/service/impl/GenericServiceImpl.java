package com.cts.common.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.cts.common.dao.GenericDao;
import com.cts.common.service.GenericService;

@Transactional
public abstract class GenericServiceImpl <T , PK extends Serializable> implements GenericService<T ,PK> {
    
    protected abstract GenericDao<T , PK> getDao();

    public T findOne(PK id) {
        return getDao().get(id);
    }

    public List<T> findAll() {
        return getDao().findAll();
    }

    public void create(T entity) {
    	getDao().create(entity);
    }

    public T update(T entity) {
        return getDao().update(entity);
    }

    public void delete(T entity) {
    	getDao().delete(entity);
    }

    public void deleteById(PK entityId) {
    	getDao().deleteById(entityId);
    }
    
    public boolean isExist(String args){
		return getDao().isExist(args);
    }

}
