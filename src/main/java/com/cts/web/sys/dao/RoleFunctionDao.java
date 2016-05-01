package com.cts.web.sys.dao;

import java.util.List;

import com.cts.common.dao.GenericDao;
import com.cts.web.sys.model.RoleFunction;

public interface RoleFunctionDao extends GenericDao<RoleFunction, String>{

	public List<RoleFunction> getRoleFunctionListById(String id);

	public List<RoleFunction> findByRoleCode(String rolecode);

}
 