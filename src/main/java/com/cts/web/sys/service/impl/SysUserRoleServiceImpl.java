package com.cts.web.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cts.common.dao.GenericDao;
import com.cts.common.service.impl.GenericServiceImpl;
import com.cts.web.sys.dao.SysUserRoleDao;
import com.cts.web.sys.model.SysUserRole;
import com.cts.web.sys.service.SysUserRoleService;
import com.cts.web.sys.service.SysUserService;


@Service(value = "sysUserRoleService")
public class SysUserRoleServiceImpl extends GenericServiceImpl<SysUserRole, String> implements SysUserRoleService{

	@Resource(name = "sysUserRoleDao")
	private SysUserRoleDao dao;
	
	@Override
	protected GenericDao<SysUserRole, String> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	public List<SysUserRole> findById(String id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

}
