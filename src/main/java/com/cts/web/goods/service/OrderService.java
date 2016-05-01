package com.cts.web.goods.service;

import java.util.List;

import com.cts.common.service.GenericService;
import com.cts.web.goods.model.Orders;

public interface OrderService extends GenericService<Orders, String>{

	List<Orders> findByAccount(String account);

	List<Orders> findByOwnSell(String account);

	Orders findByCode(Integer goodsCode);


}
