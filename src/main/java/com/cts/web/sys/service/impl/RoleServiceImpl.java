package com.cts.web.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cts.common.dao.GenericDao;
import com.cts.common.model.TreeNode;
import com.cts.common.service.impl.GenericServiceImpl;
import com.cts.web.sys.dao.RoleDao;
import com.cts.web.sys.model.Role;
import com.cts.web.sys.service.RoleService;

@Service(value = "roleService")
public class RoleServiceImpl extends GenericServiceImpl<Role, Integer> implements RoleService{
	
	@Resource(name = "roleDao")
	private RoleDao dao;

	@Override
	protected GenericDao<Role, Integer> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	public List<TreeNode> getRole() {
		// TODO Auto-generated method stub
		return dao.getRole();
	}

}
