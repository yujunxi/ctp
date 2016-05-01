package com.cts.web.msg.dao;

import java.util.List;

import com.cts.common.dao.GenericDao;
import com.cts.web.msg.model.Message;

public interface MessageDao extends GenericDao<Message,Integer>{

	public List<Message> getMsgByCode(Integer id);

}
