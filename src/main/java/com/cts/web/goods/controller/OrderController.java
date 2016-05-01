package com.cts.web.goods.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cts.web.base.model.MsgBox;
import com.cts.web.base.service.MsgBoxService;
import com.cts.web.goods.model.Goods;
import com.cts.web.goods.model.Orders;
import com.cts.web.goods.service.GoodsService;
import com.cts.web.goods.service.OrderService;
import com.cts.web.goods.service.ShopCarService;

@Controller
@RequestMapping(value= "/ctp/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ShopCarService shopCarService;
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private MsgBoxService msgBoxService;
	
	/**
	 * ��ת������ҳ
	 * @return
	 */
	@RequestMapping("/main")
	public ModelAndView main(){
		ModelAndView mav = new ModelAndView("/goods/order");
		return mav;
	}
	
	/**
	 * ��ת����������
	 * @return
	 */
	@RequestMapping("/orderMain")
	public ModelAndView orderMain(){
		ModelAndView mav = new ModelAndView("/order/orderMain");
		return mav;
	}
	
	/**
	 * ��ȡ������Ϣ
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getOrder",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Orders get(@RequestParam("id") String id){
		Orders order = orderService.findOne(id);
		return order;
	}
	
	/**
	 * ��ȡ�����б�
	 * @param session
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<Orders> list(HttpSession session){
		String account = session.getAttribute("account").toString();
		List<Orders> orderList = orderService.findByAccount(account);
		return orderList;
	}
	
	@RequestMapping("/orderList")
	@ResponseBody
	public List<Orders> orderList(){
		List<Orders> orderList = orderService.findAll();
		return orderList;
	}
	
	/**
	 * ��ת������ҳ
	 * @return
	 */
	@RequestMapping("/record")
	public ModelAndView recordMain(){
		ModelAndView mav = new ModelAndView("/order/recordMain");
		return mav;
	}
	
	/**
	 * ��ȡ���׼�¼
	 * @param session
	 * @return
	 */
	@RequestMapping("/getCompleteOrder")
	@ResponseBody
	public List<Orders> listCompeleteOrder(HttpSession session){
		String account = session.getAttribute("account").toString();
		List<Orders> orderList = orderService.findByOwnSell(account);
		return orderList;
	}
	
	/**
	 * ��Ӷ���
	 * @param order
	 * @param req
	 * @param rep
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Map<String,Object> add(Orders order,HttpServletRequest req
			,HttpServletResponse rep){
		Map<String,Object> result = new HashMap<String, Object>();
		try{
			Goods good = goodsService.findOne(order.getGoodsCode());
			good.setStatus(1);
			
			order.setCreateTime(new Date());
			order.setStatus(0);
			
			MsgBox msgBox = new MsgBox();
			msgBox.setCreateTime(new Date());
			msgBox.setObj(order.getOwner());
			msgBox.setSender("system");
			msgBox.setStatus(0);
			msgBox.setContent("�µĶ���������");
			msgBoxService.create(msgBox);
			
			MsgBox msgBox2 = new MsgBox();
			msgBox2.setCreateTime(new Date());
			msgBox2.setObj(order.getSeller());
			msgBox2.setSender("system");
			msgBox2.setStatus(0);
			msgBox2.setContent("����һ���µĶ���");
			msgBoxService.create(msgBox2);
			
			goodsService.update(good);
			orderService.create(order);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * ɾ������
	 * @param id
	 * @return
	 */
	@RequestMapping("/del")
	@ResponseBody
	public Map<String,Object> del(@RequestParam("id") String id){
		Map<String,Object> result = new HashMap<String, Object>();
		try{
			Orders order = orderService.findOne(id);
			orderService.delete(order);
			result.put("msg", "ɾ���ɹ�");
		}catch(Exception e){
			result.put("msg", "ɾ��ʧ��");
		}
		return result;
	}
	
	/**
	 * ����ɾ��
	 * @param accountList
	 * @return
	 */
	@RequestMapping(value = "/delSelect" , method = {RequestMethod.POST,RequestMethod.GET} )
    public @ResponseBody Map<String,Object> delSelect(@RequestParam("accountList[]") List<String> accountList){
    	Map<String , Object> result = new HashMap<String , Object>();
    	try{
    		for(String id : accountList){
        		Orders order = orderService.findOne(id);
        		orderService.delete(order);
        		result.put("msg", "ɾ���ɹ�");
        	}
    	}catch(Exception e){
    		result.put("msg", "ɾ��ʧ��");
    	}
    	return result;
    }
	
	/**
	 * ȷ�϶���
	 * @param id
	 * @return
	 */
	@RequestMapping("/confirm")
	@ResponseBody
	public Map<String,Object> confirm(@RequestParam("id") String id){
		Map<String,Object> result = new HashMap<String, Object>();
		try{
			Orders order = orderService.findOne(id);
			order.setStatus(2);
			order.setFinishTime(new Date());
			orderService.delete(order);
			result.put("msg", "ɾ���ɹ�");
		}catch(Exception e){
			result.put("msg", "ɾ��ʧ��");
		}
		return result;
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public Map<String,Object> edit(){
		return null;
	}
	
	/**
	 * ��ת������Ʒҳ��
	 * @return
	 */
	@RequestMapping(value = "/sale" ,method = {RequestMethod.GET})
	public ModelAndView sale(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/order/saleMain");
		return mav;
	}

}
