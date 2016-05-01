package com.cts.web.user.dao;

import java.util.List;

import com.cts.common.dao.GenericDao;
import com.cts.web.user.model.User; 

public interface UserDao extends GenericDao<User,String> {
	
	public Boolean validate(String account ,String password);

	public List<User> findByAccount(String account);
	
}
