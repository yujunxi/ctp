package com.cts.web.base.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cts.common.dao.impl.GenericDaoImpl;
import com.cts.web.base.dao.MsgBoxDao;
import com.cts.web.base.model.MsgBox;

@Repository(value = "msgBoxDao")
public class MsgBoxDaoImpl extends GenericDaoImpl<MsgBox, String> implements MsgBoxDao{
	public MsgBoxDaoImpl(){
		super();
		setClazz(MsgBox.class);
	}

	@Override
	public List<MsgBox> findByObj(String string) {
		// TODO Auto-generated method stub
		String hql = "from MsgBox a where a.obj=? and a.status=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setString(0, string);
		query.setInteger(1, 0);
		return query.list();
	}

	@Override
	public List<MsgBox> findByObj2(String string) {
		// TODO Auto-generated method stub
		String hql = "from MsgBox a where a.obj=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setString(0, string);
		return query.list();
	}
}
