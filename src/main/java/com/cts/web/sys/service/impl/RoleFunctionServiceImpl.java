package com.cts.web.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cts.common.dao.GenericDao;
import com.cts.common.service.impl.GenericServiceImpl;
import com.cts.web.sys.dao.RoleFunctionDao;
import com.cts.web.sys.model.RoleFunction;
import com.cts.web.sys.service.RoleFunctionService;

@Service(value = "roleFunctionService")
public class RoleFunctionServiceImpl extends GenericServiceImpl<RoleFunction, String> 
	implements RoleFunctionService{
	
	@Resource(name = "roleFunctionDao")
	private RoleFunctionDao dao;

	@Override
	protected GenericDao<RoleFunction, String> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	public List<RoleFunction> getRoleFunctionListById(String id) {
		// TODO Auto-generated method stub
		return dao.getRoleFunctionListById(id);
	}

	public List<RoleFunction> findByRoleCode(String rolecode) {
		// TODO Auto-generated method stub
		return dao.findByRoleCode(rolecode);
	}

}
