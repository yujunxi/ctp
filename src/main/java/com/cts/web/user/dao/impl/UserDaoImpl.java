package com.cts.web.user.dao.impl;

import org.springframework.stereotype.Repository;

import com.cts.common.dao.impl.GenericDaoImpl;
import com.cts.web.user.dao.UserDao;
import com.cts.web.user.model.User;

@Repository("userDao")
public class UserDaoImpl extends GenericDaoImpl<User,Long> implements UserDao {

    public UserDaoImpl() {
        super();
        
        setClazz(User.class);
    }
}
