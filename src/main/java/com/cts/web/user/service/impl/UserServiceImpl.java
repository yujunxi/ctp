package com.cts.web.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cts.common.dao.GenericDao;
import com.cts.common.dao.impl.GenericDaoImpl;
import com.cts.common.service.impl.GenericServiceImpl;
import com.cts.web.user.dao.UserDao;
import com.cts.web.user.model.User;
import com.cts.web.user.service.UserService;

@Service("userService")
public class UserServiceImpl extends GenericServiceImpl<User,String> implements UserService {

    @Resource(name="userDao")
    private UserDao dao;
    
    public UserServiceImpl() {
        super();
    }

    @Override
    protected GenericDao<User,String> getDao() {
        return this.dao;
    }

	public Boolean validate(String account, String password) {
		return dao.validate(account, password);
	}

	public List<User> findByAccount(String account) {
		// TODO Auto-generated method stub
		return dao.findByAccount(account);
	}
}
