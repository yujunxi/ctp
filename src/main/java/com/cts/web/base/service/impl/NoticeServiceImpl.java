package com.cts.web.base.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cts.common.dao.GenericDao;
import com.cts.common.service.impl.GenericServiceImpl;
import com.cts.web.base.dao.NoticeDao;
import com.cts.web.base.model.Notice;
import com.cts.web.base.service.NoticeService;

@Service(value="noticeService")
public class NoticeServiceImpl extends GenericServiceImpl<Notice, String> implements NoticeService{
	
	@Resource(name="noticeDao")
	private NoticeDao dao;
	
	@Override
	protected GenericDao<Notice, String> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
