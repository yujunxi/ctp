package com.cts.web.user.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cts.common.dao.GenericDao;
import com.cts.common.service.impl.GenericServiceImpl;
import com.cts.web.user.dao.UserInfoDao;
import com.cts.web.user.model.UserInfo;
import com.cts.web.user.service.UserInfoService;

@Service("userInfoService")
public class UserInfoServiceImpl extends GenericServiceImpl<UserInfo,Integer> implements UserInfoService {

    @Resource(name="userInfoDao")
    private UserInfoDao dao;
    
    public UserInfoServiceImpl() {
        super();
    }

    @Override
    protected GenericDao<UserInfo,Integer> getDao() {
        return this.dao;
    }
}