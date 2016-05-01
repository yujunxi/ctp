package com.cts.web.goods.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cts.common.dao.GenericDao;
import com.cts.common.service.impl.GenericServiceImpl;
import com.cts.web.goods.dao.ShopCarDao;
import com.cts.web.goods.model.ShopCar;
import com.cts.web.goods.service.ShopCarService;

@Service("shopCarService")
public class ShopCarServiceImpl extends GenericServiceImpl<ShopCar, String> implements ShopCarService{

	@Resource(name="shopCarDao")
    private ShopCarDao dao;
    
    public ShopCarServiceImpl() {
        super();
    }

    @Override
    protected GenericDao<ShopCar,String> getDao() {
        return this.dao;
    }

	public List<ShopCar> findByName(String name) {
		// TODO Auto-generated method stub
		return dao.findByName(name);
	}

}
