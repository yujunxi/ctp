package com.cts.web.goods.dao;

import java.util.List;

import com.cts.common.dao.GenericDao;
import com.cts.web.goods.model.Goods;

public interface GoodsDao extends GenericDao<Goods, Integer>{

	List<Goods> searchGoods(String... params);

	List<Goods> findBySeller(String account);

	List<Goods> findByStatus();

	List<Goods> findByGoods(String goodsType, String goodsName);

}
