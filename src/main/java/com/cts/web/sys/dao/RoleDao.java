package com.cts.web.sys.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import com.cts.common.dao.GenericDao;
import com.cts.common.model.TreeNode;
import com.cts.web.sys.model.Role;

public interface RoleDao extends GenericDao<Role, Integer>{
	
	public List<TreeNode> getRole();
}
