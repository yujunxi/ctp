package com.cts.web.user.dao.impl;

import org.springframework.stereotype.Repository;

import com.cts.common.dao.impl.GenericDaoImpl;
import com.cts.web.user.dao.UserInfoDao;
import com.cts.web.user.model.UserInfo;

@Repository("userInfoDao")
public class UserInfoDaoImpl extends GenericDaoImpl<UserInfo,Integer> implements UserInfoDao {

    public UserInfoDaoImpl() {
        super();
        
        setClazz(UserInfo.class);
    }
}
