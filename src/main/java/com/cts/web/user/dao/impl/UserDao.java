package com.cts.web.user.dao.impl;

import org.springframework.stereotype.Repository;

import com.cts.common.dao.impl.GenericDaoImpl;
import com.cts.web.user.dao.IUserDao;
import com.cts.web.user.model.User;

@Repository("usersDao")
public class UserDao extends GenericDaoImpl<User,Long> implements IUserDao {

    public UserDao() {
        super();
        
        setClazz(User.class);
    }
}
