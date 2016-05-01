package com.cts.web.msg.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cts.common.dao.GenericDao;
import com.cts.common.service.impl.GenericServiceImpl;
import com.cts.web.msg.dao.MessageDao;
import com.cts.web.msg.model.Message;
import com.cts.web.msg.service.MessageService;

@Service("messageService")
public class MessageServiceImpl extends GenericServiceImpl<Message,Integer> implements MessageService {

    @Resource(name="messageDao")
    private MessageDao dao;
    
    public MessageServiceImpl() {
        super();
    }

    @Override
    protected GenericDao<Message,Integer> getDao() {
        return this.dao;
    }

	public List<Message> getMsgByCode(Integer id) {
		// TODO Auto-generated method stub
		return dao.getMsgByCode(id);
	}
}