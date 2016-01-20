package com.cts.web.user.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cts.common.dao.GenericDao;
import com.cts.common.dao.impl.GenericDaoImpl;
import com.cts.common.service.impl.GenericServiceImpl;
import com.cts.web.user.dao.IUserDao;
import com.cts.web.user.model.User;
import com.cts.web.user.service.IUserService;

@Service("userService")
public class UserService extends GenericServiceImpl<User,Long> implements IUserService {

    @Resource(name="usersDao")
    private IUserDao dao;
    
    public UserService() {
        super();
    }

    @Override
    protected GenericDao<User,Long> getDao() {
        return this.dao;
    }
}
