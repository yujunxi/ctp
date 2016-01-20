package com.cts.web.user.dao.impl;

import org.springframework.stereotype.Repository;

import com.cts.common.dao.impl.GenericDaoImpl;
import com.cts.web.user.dao.IUserInfoDao;
import com.cts.web.user.model.UserInfo;

@Repository("userInfoDao")
public class UserInfoDao extends GenericDaoImpl<UserInfo,Integer> implements IUserInfoDao {

    public UserInfoDao() {
        super();
        
        setClazz(UserInfo.class);
    }
}
