package com.cts.web.sys.service;

import java.util.List;

import com.cts.common.service.GenericService;
import com.cts.web.sys.model.RoleFunction;

public interface RoleFunctionService extends GenericService<RoleFunction, String>{

	public List<RoleFunction> getRoleFunctionListById(String id);

	public List<RoleFunction> findByRoleCode(String rolecode);

}
