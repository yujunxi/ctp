package com.cts.web.goods.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cts.common.dao.GenericDao;
import com.cts.common.service.impl.GenericServiceImpl;
import com.cts.web.goods.dao.GoodsDao;
import com.cts.web.goods.model.Goods;
import com.cts.web.goods.service.GoodsService;

@Service("goodsService")
public class GoodsServiceImpl extends GenericServiceImpl<Goods, Integer>
	implements GoodsService{
	
	@Resource(name="goodsDao")
    private GoodsDao dao;
    
    public GoodsServiceImpl() {
        super();
    }

    @Override
    protected GenericDao<Goods,Integer> getDao() {
        return this.dao;
    }

	public List<Goods> searchGoods(String... params) {
		// TODO Auto-generated method stub
		return dao.searchGoods(params);
	}

	public List<Goods> findBySeller(String account) {
		// TODO Auto-generated method stub
		return dao.findBySeller(account);
	}

	public List<Goods> findByStatus() {
		// TODO Auto-generated method stub
		return dao.findByStatus();
	}

	public List<Goods> findByGoods(String goodsType, String goodsName) {
		// TODO Auto-generated method stub
		return dao.findByGoods(goodsType,goodsName);
	}
}
