package com.cts.web.base.dao.impl;

import org.springframework.stereotype.Repository;

import com.cts.common.dao.impl.GenericDaoImpl;
import com.cts.web.base.dao.ServDao;
import com.cts.web.base.model.Serv;

@Repository(value = "servDao")
public class ServDaoImpl extends GenericDaoImpl<Serv, String> implements ServDao{
	
	public ServDaoImpl(){
		super();
		setClazz(Serv.class);
	}
}
