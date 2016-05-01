package com.cts.web.goods.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cts.common.dao.GenericDao;
import com.cts.common.service.impl.GenericServiceImpl;
import com.cts.web.goods.dao.OrderDao;
import com.cts.web.goods.model.Orders;
import com.cts.web.goods.service.OrderService;

@Service("orderService")
public class OrderServiceImpl extends GenericServiceImpl<Orders, String> implements OrderService{
	
	@Resource(name="orderDao")
	private OrderDao dao;

	@Override
	protected GenericDao<Orders, String> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	public List<Orders> findByAccount(String account) {
		// TODO Auto-generated method stub
		return dao.findByAccount(account);
	}

	public List<Orders> findByOwnSell(String account) {
		// TODO Auto-generated method stub
		return dao.findByOwnSell(account);
	}

	public Orders findByCode(Integer goodsCode) {
		// TODO Auto-generated method stub
		return dao.findByCode(goodsCode);
	}

}
