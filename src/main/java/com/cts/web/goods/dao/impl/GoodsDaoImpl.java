package com.cts.web.goods.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cts.common.dao.impl.GenericDaoImpl;
import com.cts.web.goods.dao.GoodsDao;
import com.cts.web.goods.model.Goods;

@Repository("goodsDao")
public class GoodsDaoImpl extends GenericDaoImpl<Goods, Integer> 
	implements GoodsDao{
	
	  public GoodsDaoImpl() {
	        super();
	        
	        setClazz(Goods.class);
	    }

	//type name price num
	public List<Goods> searchGoods(String... params) {
		Criteria criteria = getCurrentSession().createCriteria(Goods.class);
		System.err.println("1231231"+params[0]);
		System.err.println("1231232"+params[4]);
		if(params[0]!=null&&!"".equals(params[0])){
			criteria.add(Restrictions.like("goodsName", "%"+params[0]+"%"));
		}else{
			criteria.add(Restrictions.like("goodsName", "%"+""+"%"));
		}
		
		if(params[1]!=null&&!"".equals(params[1])){
			criteria.add(Restrictions.like("goodsType", "%"+params[1]+"%"));
		}else{
			criteria.add(Restrictions.like("goodsType", "%"+""+"%"));
		}
		//价格范围
		if(params[2].equals("dprice")){
			criteria.add(Restrictions.between("price",0.0,100.0));
		}else if(params[2].equals("zprice")){
			criteria.add(Restrictions.between("price",100.0 ,1000.0));
		}else if(params[2].equals("gprice")){
			criteria.add(Restrictions.ge("price", 1000.0));
		}else{
		}
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		//时间范围
		if(params[3].equals("today")){
			criteria.add(Restrictions.between("createTime",new Date(),new Date()));
		}else if(params[3].equals("three")){
			calendar.add(Calendar.DATE, -1);
			date = calendar.getTime();
			criteria.add(Restrictions.between("createTime",date ,new Date()));
		}else if(params[3].equals("week")){
			calendar.add(Calendar.DATE, -7);
			date = calendar.getTime();
			criteria.add(Restrictions.between("createTime",date ,new Date()));
		}else if(params[3].equals("month")){
			calendar.add(Calendar.DATE, -30);
			date = calendar.getTime();
			criteria.add(Restrictions.between("createTime",date ,new Date()));
		}else if(params[3].equals("year")){
			calendar.add(Calendar.DATE, -365);
			date = calendar.getTime();
			criteria.add(Restrictions.between("createTime",date ,new Date()));
		}else{
		}
		System.err.println(criteria.toString());
		List<Goods> goodsList = criteria.list();
		return goodsList;
	}

	public List<Goods> findBySeller(String account) {
		// TODO Auto-generated method stub
		String hql = "from Goods where seller=?";
		Query query = getCurrentSession().createQuery(hql);
		query.setString(0, account);
		return query.list();
	}

	public List<Goods> findByStatus() {
		Disjunction dis=Restrictions.disjunction();  
		dis.add(Restrictions.eq("status", 0));
		List<Goods> goodsList = getCurrentSession()
				.createCriteria(Goods.class)
				.add(dis)
				.list();
		return goodsList;
	}

	public List<Goods> findByGoods(String goodsType, String goodsName) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("from Goods where goodsType=? or goodsName=?");
		Query query = getCurrentSession().createQuery(sbf.toString());
		query.setString(0, goodsType);
		query.setString(1, goodsName);
		return query.list();
	}

}
