package com.cts.web.goods.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cts.web.goods.model.Goods;
import com.cts.web.goods.service.GoodsService;
import com.cts.web.user.model.User;

@Controller
@RequestMapping("/ctp/goods")
public class GoodsControler {
	
	@Resource(name="goodsService")
	public GoodsService goodsService;

	/**
	 * 获取商品列表
	 * @return
	 */
	@RequestMapping(value = "/goodsList" , method = {RequestMethod.GET})
	@ResponseBody
	public Map<String,Object> goodsList(){
		Map<String,Object> result = new HashMap<String, Object>();
		List<Goods> goodList = goodsService.findAll();
		result.put("list", goodList);
		return result;
	}
	
	/**
	 * 获取商品详细信息
	 * @param good
	 * @return
	 */
	@RequestMapping(value = "/getGoods" , method = {RequestMethod.GET})
	@ResponseBody
	public List<Goods> getGoods(Goods good){
		return null;
	}
	
	/**
	 * 创建商品
	 * @param goods
	 * @return
	 */
	@RequestMapping(value = "/createGoods" , method = {RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> createGoods(Goods goods){
		goodsService.create(goods);
		return null;
	}
	
	@RequestMapping(value = "/publishMain" ,method = {RequestMethod.GET})
	public ModelAndView publishMain(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/goods/publishMain");
		return mav;
	}
	
	@RequestMapping(value = "/publish" , method = {RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> publish(User user,HttpServletRequest req 
			, HttpServletResponse rep ,HttpSession session){
		String goodsName = req.getParameter("goodsName");
		Goods goods = new Goods();
		goods.setGoodsName(goodsName);
		goodsService.create(goods);
		return null;
	}
	
	/**
	 * 修改商品信息
	 * @param goods
	 * @return
	 */
	@RequestMapping(value = "/editGoods" , method = {RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> editGoods(Goods goods){
		goodsService.update(goods);
		return null;
	}
	
	/**
	 * 删除商品
	 * @param goods
	 * @return
	 */
	@RequestMapping(value = "/delGoods" , method = {RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> delGoods(Goods goods){
		goodsService.delete(goods);
		return null;
	}
	
	/**
	 * 审核商品
	 */
}
