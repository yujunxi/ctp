package com.cts.web.base.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cts.common.dao.GenericDao;
import com.cts.common.service.impl.GenericServiceImpl;
import com.cts.web.base.dao.MsgBoxDao;
import com.cts.web.base.model.MsgBox;
import com.cts.web.base.service.MsgBoxService;

@Service(value = "msgBoxService")
public class MsgBoxServiceImpl extends GenericServiceImpl<MsgBox, String> implements MsgBoxService{

	@Resource(name = "msgBoxDao")
	private MsgBoxDao dao;
	
	@Override
	protected GenericDao<MsgBox, String> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public List<MsgBox> findByObj(String string) {
		// TODO Auto-generated method stub
		return dao.findByObj(string);
	}

	@Override
	public List<MsgBox> findByObj2(String string) {
		// TODO Auto-generated method stub
		return dao.findByObj2(string);
	}

}
