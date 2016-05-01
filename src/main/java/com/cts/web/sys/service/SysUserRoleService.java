package com.cts.web.sys.service;

import java.util.List;

import com.cts.common.service.GenericService;
import com.cts.web.sys.model.SysUserRole;

public interface SysUserRoleService extends GenericService<SysUserRole, String>{

	List<SysUserRole> findById(String id);

}
