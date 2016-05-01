package com.cts.web.base.dao.impl;

import org.springframework.stereotype.Repository;

import com.cts.common.dao.impl.GenericDaoImpl;
import com.cts.web.base.dao.NoticeDao;
import com.cts.web.base.model.Notice;

@Repository(value = "noticeDao")
public class NoticeDaoImpl extends GenericDaoImpl<Notice, String> implements NoticeDao{
	
	public NoticeDaoImpl(){
		super();
		setClazz(Notice.class);
	}
}
