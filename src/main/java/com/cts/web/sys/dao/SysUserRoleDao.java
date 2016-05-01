package com.cts.web.sys.dao;

import java.util.List;

import com.cts.common.dao.GenericDao;
import com.cts.web.sys.model.SysUserRole;

public interface SysUserRoleDao extends GenericDao<SysUserRole, String>{

	List<SysUserRole> findById(String id);

}
