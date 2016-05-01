package com.cts.web.msg.service;

import java.util.List;

import com.cts.common.service.GenericService;
import com.cts.web.msg.model.Message;

public interface MessageService extends GenericService<Message, Integer>{

	public List<Message> getMsgByCode(Integer id);

}
