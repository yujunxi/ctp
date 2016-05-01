package com.cts.web.goods.service;

import java.util.List;

import com.cts.common.service.GenericService;
import com.cts.web.goods.model.ShopCar;

public interface ShopCarService extends GenericService<ShopCar, String>{
	
	public List<ShopCar> findByName(String name);
 
}
