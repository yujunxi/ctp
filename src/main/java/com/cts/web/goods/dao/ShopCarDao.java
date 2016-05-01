package com.cts.web.goods.dao;

import java.util.List;

import com.cts.common.dao.GenericDao;
import com.cts.web.goods.model.ShopCar;

public interface ShopCarDao extends GenericDao<ShopCar, String>{
	
	public List<ShopCar> findByName(String name);

}
