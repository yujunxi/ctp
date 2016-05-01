package com.cts.web.sys.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cts.common.dao.impl.GenericDaoImpl;
import com.cts.web.sys.dao.RoleFunctionDao;
import com.cts.web.sys.model.RoleFunction;

@Repository(value = "roleFunctionDao")
public class RoleFunctionDaoImpl extends GenericDaoImpl<RoleFunction, String> 
	implements RoleFunctionDao{

	public RoleFunctionDaoImpl(){
		super();
		setClazz(RoleFunction.class);
	}

	public List<RoleFunction> getRoleFunctionListById(String id) {
		// TODO Auto-generated method stub
		String hql = "from com.cts.web.sys.model.RoleFunction where id.roleId=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setString(0, id);
		return query.list();
	}

	public List<RoleFunction> findByRoleCode(String rolecode) {
		// TODO Auto-generated method stub
		String hql = "from com.cts.web.sys.model.RoleFunction where id.roleId=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setString(0, rolecode);
		return query.list();
	}
}
