package com.cts.common.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.cts.common.dao.GenericDao;

public class GenericDaoImpl<T ,PK extends Serializable> implements GenericDao<T , PK> {
    
    private Class<T> clazz;
    
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    protected void setClazz(Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }
    
    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    
    public T findOne(PK id) {
        return (T)getCurrentSession().get(clazz, id);
    }

    
    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + clazz.getName()).list();
    }

    
    public void create(T entity) {
         getCurrentSession().saveOrUpdate(entity);
    }

    
    public T update(T entity) {
        getCurrentSession().update(entity);
        return entity;
    }

    
    public void delete(T entity) {
         getCurrentSession().delete(entity);
    }

    
    public void deleteById(PK entityId) {
        T entity = findOne(entityId);
        delete(entity);
    }
    
}
