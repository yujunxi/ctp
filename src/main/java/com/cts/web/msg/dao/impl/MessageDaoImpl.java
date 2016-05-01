package com.cts.web.msg.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cts.common.dao.impl.GenericDaoImpl;
import com.cts.web.goods.model.Goods;
import com.cts.web.msg.dao.MessageDao;
import com.cts.web.msg.model.Message;

@Repository("messageDao")
public class MessageDaoImpl extends GenericDaoImpl<Message, Integer>
		implements MessageDao{
	
	 public MessageDaoImpl() {
	        super();
	        setClazz(Message.class);
	 }

	public List<Message> getMsgByCode(Integer id) {
		// TODO Auto-generated method stub
		List<Message> msgList = getCurrentSession()
				.createCriteria(Message.class)
				.add(Restrictions.eq("goodsCode", id))
				.list();
		return msgList;
	}

}
