package com.cts.web.sys.service;

import java.util.List;

import com.cts.common.model.TreeNode;
import com.cts.common.service.GenericService;
import com.cts.web.sys.model.Role;

public interface RoleService extends GenericService<Role, Integer>{
	
	public List<TreeNode> getRole();
}
