package com.cts.web.goods.dao.impl;

import org.springframework.stereotype.Repository;

import com.cts.common.dao.impl.GenericDaoImpl;
import com.cts.web.goods.dao.GoodsDao;
import com.cts.web.goods.model.Goods;

@Repository("goodsDao")
public class GoodsDaoImpl extends GenericDaoImpl<Goods, String> 
	implements GoodsDao{
	
	  public GoodsDaoImpl() {
	        super();
	        
	        setClazz(Goods.class);
	    }

}
