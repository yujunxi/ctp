package com.cts.web.sys.dao;

import java.util.List;

import com.cts.common.dao.GenericDao;
import com.cts.web.sys.model.SysUser;

public interface SysUserDao extends GenericDao<SysUser, String>{

	List<SysUser> validate(String account, String password);

}
