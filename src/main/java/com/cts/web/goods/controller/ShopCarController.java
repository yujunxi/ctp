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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cts.web.base.model.MsgBox;
import com.cts.web.base.service.MsgBoxService;
import com.cts.web.goods.model.Goods;
import com.cts.web.goods.model.ShopCar;
import com.cts.web.goods.service.ShopCarService;

@Controller
@RequestMapping(value = "/ctp/shopCar")
public class ShopCarController {
	
	@Autowired
	private ShopCarService shopCarService;
	
	@Autowired
	private MsgBoxService msgBoxService;
	
	/**
	 * ��ת�����ﳵҳ��
	 * @param session
	 * @return
	 */
	@RequestMapping("/main")
	public ModelAndView main(HttpSession session){
		String account = session.getAttribute("account").toString();
		ModelAndView mav = new ModelAndView("/goods/shopCar");
		return mav;
	}
	
	/**
	 * ��ȡ���ﳵ��Ʒ�б�
	 * @param session
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<ShopCar> list(HttpSession session){
		String account = session.getAttribute("account").toString();
		List<ShopCar> shopCar = shopCarService.findByName(account);
		return shopCar;
	}
	
	/**
	 * �����Ʒ�����ﳵ
	 * @param car
	 * @param req
	 * @param rep
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Map<String,Object> add(ShopCar car,HttpServletRequest req
			,HttpServletResponse rep){
		Map<String,Object> result = new HashMap<String, Object>();
		try{
			car.setCreateTime(new Date());
			car.setStatus(0);
			shopCarService.create(car);
			
			MsgBox msgBox = new MsgBox();
			msgBox.setCreateTime(new Date());
			msgBox.setObj(car.getOwner());
			msgBox.setSender("system");
			msgBox.setStatus(0);
			msgBox.setContent("���Ĺ��ﳵ��������µ���Ʒ");
			msgBoxService.create(msgBox);
			
			result.put("msg", "�ɹ���ӵ����ﳵ");
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "���ʧ��");
		}
		return result;
	}

	/**
	 * ɾ�����ﳵ����
	 * @param id
	 * @return
	 */
	@RequestMapping("/del")
	@ResponseBody
	public Map<String,Object> del(@RequestParam("id") String id){
		Map<String,Object> result = new HashMap<String, Object>();
		try{
			shopCarService.deleteById(id);
			result.put("msg", "ɾ���ɹ�");
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "ɾ��ʧ��");
		}
		return result;
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public Map<String,Object> edit(){
		return null;
	}
	
}
