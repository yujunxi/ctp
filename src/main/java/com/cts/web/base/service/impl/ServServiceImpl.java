package com.cts.web.base.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cts.common.dao.GenericDao;
import com.cts.common.service.impl.GenericServiceImpl;
import com.cts.web.base.dao.ServDao;
import com.cts.web.base.model.Serv;
import com.cts.web.base.service.ServService;

@Service(value = "servService")
public class ServServiceImpl extends GenericServiceImpl<Serv, String> implements ServService{
	
	@Resource(name = "servDao")
	private ServDao dao;

	@Override
	protected GenericDao<Serv, String> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	
	
}
