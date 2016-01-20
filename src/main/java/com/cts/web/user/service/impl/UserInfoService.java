package com.cts.web.user.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cts.common.dao.GenericDao;
import com.cts.common.service.impl.GenericServiceImpl;
import com.cts.web.user.dao.IUserInfoDao;
import com.cts.web.user.model.UserInfo;
import com.cts.web.user.service.IUserInfoService;

@Service("userInfoService")
public class UserInfoService extends GenericServiceImpl<UserInfo,Integer> implements IUserInfoService {

    @Resource(name="userInfoDao")
    private IUserInfoDao dao;
    
    public UserInfoService() {
        super();
    }

    @Override
    protected GenericDao<UserInfo,Integer> getDao() {
        return this.dao;
    }
}