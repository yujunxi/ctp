package com.cts.web.goods.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cts.common.dao.impl.GenericDaoImpl;
import com.cts.web.goods.dao.OrderDao;
import com.cts.web.goods.model.Goods;
import com.cts.web.goods.model.Orders;

@Repository("orderDao")
public class OrderDaoImpl extends GenericDaoImpl<Orders, String> implements OrderDao{
	
	public OrderDaoImpl(){
		super();
		
		setClazz(Orders.class);
	}

	public List<Orders> findByAccount(String account) {
		// TODO Auto-generated method stub
		String hql = "from Orders where owner=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setString(0, account);
		return query.list();
	}

	public List<Orders> findByOwnSell(String account) {
		Disjunction dis=Restrictions.disjunction();  
		dis.add(Restrictions.eq("owner", account));
		dis.add(Restrictions.eq("seller", account));
		dis.add(Restrictions.eq("status", 3));
		List<Orders> list = getCurrentSession()
				.createCriteria(Orders.class)
				.add(dis)
				.list();
		return list;
	}

	public Orders findByCode(Integer goodsCode) {
		String hql = "from Orders where goodsCode=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setInteger(0, goodsCode);
		List<Orders> list = query.list();
		Orders order = list.get(0);
		return order;
	}

}
