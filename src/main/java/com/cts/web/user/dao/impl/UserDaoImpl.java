package com.cts.web.user.dao.impl;

import java.util.List;

import org.hibernate.Query;
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
		String hql = "from User where account=? and password=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setString(0, account);
		query.setString(1, password);
		List<User> userList = query.list();
		return userList.size()>0?true:false;
	}

	/**
	 * 获取用户信息
	 */
	public List<User> findByAccount(String account) {
		// TODO Auto-generated method stub
		String hql = "from User where account=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setString(0, account);
		List<User> userList = query.list();
		return userList;
	}
}
