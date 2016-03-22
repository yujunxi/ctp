package com.cts.web.user.dao.impl;

import org.springframework.stereotype.Repository;

import com.cts.common.dao.impl.GenericDaoImpl;
import com.cts.web.user.dao.UserDao;
import com.cts.web.user.model.User;

@Repository("userDao")
public class UserDaoImpl extends GenericDaoImpl<User,String> implements UserDao {

    public UserDaoImpl() {
        super();
        
        setClazz(User.class);
    }

    /**
     * 验证会员账号
     */
	public Boolean validate(String account, String password) {
		User user = this.get(account);
		if(user!=null){
			if(user.getPassword().equals(password)){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
}
