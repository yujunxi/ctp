package com.cts.web.sys.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cts.common.dao.impl.GenericDaoImpl;
import com.cts.web.sys.dao.SysUserRoleDao;
import com.cts.web.sys.model.SysUserRole;

@Repository(value = "sysUserRoleDao")
public class SysUserRoleDaoImpl extends GenericDaoImpl<SysUserRole, String> implements SysUserRoleDao{
	
	public SysUserRoleDaoImpl(){
		super();
		
		setClazz(SysUserRole.class);
	}

	public List<SysUserRole> findById(String id) {
		// TODO Auto-generated method stub
		String hql = "from com.cts.web.sys.model.SysUserRole where id.userId=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setString(0, id);
		return query.list();
	}
}
