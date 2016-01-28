package com.cts.web.user.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cts.common.dao.GenericDao;
import com.cts.common.dao.impl.GenericDaoImpl;
import com.cts.common.service.impl.GenericServiceImpl;
import com.cts.web.user.dao.UserDao;
import com.cts.web.user.model.User;
import com.cts.web.user.service.UserService;

@Service("userService")
public class UserServiceImpl extends GenericServiceImpl<User,Long> implements UserService {

    @Resource(name="userDao")
    private UserDao dao;
    
    public UserServiceImpl() {
        super();
    }

    @Override
    protected GenericDao<User,Long> getDao() {
        return this.dao;
    }
}
