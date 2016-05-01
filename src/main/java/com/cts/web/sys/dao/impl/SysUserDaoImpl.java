package com.cts.web.sys.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cts.common.dao.impl.GenericDaoImpl;
import com.cts.web.sys.dao.SysUserDao;
import com.cts.web.sys.model.SysUser;

@Repository(value = "sysUserDao")
public class SysUserDaoImpl extends GenericDaoImpl<SysUser, String>
	implements SysUserDao{
	
	
	public SysUserDaoImpl(){
		super();
		
		setClazz(SysUser.class);
	}

	public List<SysUser> validate(String account, String password) {
		// TODO Auto-generated method stub
		String hql = "from com.cts.web.sys.model.SysUser where account=? and password=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setString(0, account);
		query.setString(1, password);
		return query.list();
	}
}
