package com.cts.web.user.service;

import java.util.List;

import com.cts.common.service.GenericService;
import com.cts.web.user.model.User;

public interface UserService  extends GenericService<User,String> {
	
	public Boolean validate(String account ,String password);
	
	public List<User> findByAccount(String account); 
	
}
