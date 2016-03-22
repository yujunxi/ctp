package com.cts.web.user.dao;

import com.cts.common.dao.GenericDao;
import com.cts.web.user.model.User; 

public interface UserDao extends GenericDao<User,String> {
	
	public Boolean validate(String account ,String password);
	
}
