package com.cts.web.goods.service;

import java.util.List;

import com.cts.common.service.GenericService;
import com.cts.web.goods.model.Goods;

public interface GoodsService extends GenericService<Goods, Integer>{
	
	public List<Goods> searchGoods(String... params);

	public List<Goods> findBySeller(String account);

	public List<Goods> findByStatus();

	public List<Goods> findByGoods(String goodsType, String goodsName);

}
