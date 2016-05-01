package com.cts.web.sys.service;

import java.util.List;

import com.cts.common.service.GenericService;
import com.cts.web.sys.model.SysUser;

public interface SysUserService extends GenericService<SysUser, String>{
	
	public List<SysUser> validate(String account,String password);
	
}
