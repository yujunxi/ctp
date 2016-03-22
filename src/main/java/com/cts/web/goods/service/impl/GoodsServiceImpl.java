package com.cts.web.goods.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cts.common.dao.GenericDao;
import com.cts.common.service.impl.GenericServiceImpl;
import com.cts.web.goods.dao.GoodsDao;
import com.cts.web.goods.model.Goods;
import com.cts.web.goods.service.GoodsService;

@Service("goodsService")
public class GoodsServiceImpl extends GenericServiceImpl<Goods, String>
	implements GoodsService{
	
	@Resource(name="goodsDao")
    private GoodsDao dao;
    
    public GoodsServiceImpl() {
        super();
    }

    @Override
    protected GenericDao<Goods,String> getDao() {
        return this.dao;
    }
}
