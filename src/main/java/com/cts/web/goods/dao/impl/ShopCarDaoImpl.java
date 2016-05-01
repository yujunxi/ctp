package com.cts.web.goods.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cts.common.dao.impl.GenericDaoImpl;
import com.cts.web.goods.dao.ShopCarDao;
import com.cts.web.goods.model.ShopCar;

@Repository("shopCarDao")
public class ShopCarDaoImpl extends GenericDaoImpl<ShopCar, String> implements ShopCarDao{
	
	  public ShopCarDaoImpl() {
	        super();
	        setClazz(ShopCar.class);
	  }

	public List<ShopCar> findByName(String name) {
		// TODO Auto-generated method stub
		String hql = "from ShopCar where owner=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setString(0, name);
		return query.list();
	}
}
