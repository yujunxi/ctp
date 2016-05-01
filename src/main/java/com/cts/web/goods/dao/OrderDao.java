package com.cts.web.goods.dao;

import java.util.List;

import com.cts.common.dao.GenericDao;
import com.cts.web.goods.model.Orders;

public interface OrderDao extends GenericDao<Orders, String>{

	List<Orders> findByAccount(String account);

	List<Orders> findByOwnSell(String account);

	Orders findByCode(Integer goodsCode);

}
