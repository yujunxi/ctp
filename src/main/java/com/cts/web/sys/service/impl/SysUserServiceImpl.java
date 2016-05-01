package com.cts.web.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cts.common.dao.GenericDao;
import com.cts.common.service.impl.GenericServiceImpl;
import com.cts.web.sys.dao.SysUserDao;
import com.cts.web.sys.model.SysUser;
import com.cts.web.sys.service.SysUserService;

@Service(value = "sysUserService")
public class SysUserServiceImpl extends GenericServiceImpl<SysUser, String>
	implements SysUserService{

	@Resource(name = "sysUserDao")
	private SysUserDao dao;
	
	@Override
	protected GenericDao<SysUser, String> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	public List<SysUser> validate(String account, String password) {
		// TODO Auto-generated method stub
		return dao.validate(account,password);
	}

}
