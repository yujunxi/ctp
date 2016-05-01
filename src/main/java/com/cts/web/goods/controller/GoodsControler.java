package com.cts.web.goods.controller;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cts.web.base.model.MsgBox;
import com.cts.web.base.service.MsgBoxService;
import com.cts.web.goods.model.Goods;
import com.cts.web.goods.model.Orders;
import com.cts.web.goods.service.GoodsService;
import com.cts.web.goods.service.OrderService;
import com.cts.web.user.model.User;

@Controller
@RequestMapping("/ctp/goods")
public class GoodsControler {

	@Resource(name = "goodsService")
	public GoodsService goodsService;

	@Resource(name = "orderService")
	public OrderService orderService;

	@Autowired
	public MsgBoxService msgBoxService;
	/**
	 * ��ȡ��Ʒ�б�
	 * 
	 * @return
	 */
	@RequestMapping(value = "/goodsList", method = { RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> goodsList() {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Goods> goodList = goodsService.findAll();
		result.put("list", goodList);
		return result;
	}

	/**
	 * ��ȡ�ȶ����5����¼
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getHotGoodsList", method = { RequestMethod.GET })
	@ResponseBody
	public List<Goods> getHotGoodsList() {
		List<Goods> goodsList = goodsService.findByStatus();
		Collections.sort(goodsList, new newComparator());
		return goodsList;
	}

	// ������ȴ�С��������
	private static class newComparator implements Comparator {
		public int compare(Object object1, Object object2) {
			Goods p1 = (Goods) object1;
			Goods p2 = (Goods) object2;
			return new Integer(p2.getReadNum()).compareTo(new Integer(p1
					.getReadNum()));
		}
	}

	/**
	 * ��ȡ����5����¼
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getNewGoodsList", method = { RequestMethod.GET })
	@ResponseBody
	public List<Goods> getNewGoodsList() {
		List<Goods> goodsList = goodsService.findByStatus();
		Collections.sort(goodsList, new newComparator2());
		return goodsList;
	}

	// ��ʱ���С��������
	private static class newComparator2 implements Comparator {
		public int compare(Object object1, Object object2) {
			Goods p1 = (Goods) object1;
			Goods p2 = (Goods) object2;
			return p2.getCreateTime().compareTo(p1.getCreateTime());
		}
	}

	/**
	 * ��ȡ��Ʒ��ϸ��Ϣ
	 * 
	 * @param good
	 * @return
	 */
	@RequestMapping(value = "/getGoods", method = { RequestMethod.GET })
	public ModelAndView getGoods(@RequestParam("goodsCode") String goodsCode) {
		ModelAndView mav = new ModelAndView("goods/goodsDetail");
		Goods good = goodsService.findOne(Integer.parseInt(goodsCode));
		int i = good.getReadNum() + 1;
		good.setReadNum(i);
		goodsService.update(good);
		mav.addObject("good", good);
		return mav;
	}

	/**
	 * ������Ʒ��Ϣ
	 * 
	 * @param goodsName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/search", method = { RequestMethod.GET })
	public ModelAndView searchGoods(HttpServletRequest req,
			HttpServletResponse rep) throws Exception {
		ModelAndView mav = new ModelAndView("/goods/goodsSearch");
		String goodsType = req.getParameter("goodsType");
		String goodsName = req.getParameter("goodsName");
		String price = req.getParameter("price");
		String time = req.getParameter("time");
		if (goodsType != null && !"".equals(goodsType)) {
			mav.addObject("type", goodsType);
		} else {
			mav.addObject("type", "");
		}

		if (goodsName != null && !"".equals(goodsName)) {
			mav.addObject("name", goodsName);
		} else {
			mav.addObject("name", "");
		}

		if (price != null && !"".equals(price)) {
			mav.addObject("price", price);
		} else {
			mav.addObject("price", "");
		}

		if (time != null && !"".equals(time)) {
			mav.addObject("time", time);
		} else {
			mav.addObject("time", "");
		}
		return mav;
	}

	@RequestMapping(value = "/searchGoods", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public List<Goods> getSearch(HttpServletRequest req, HttpServletResponse rep) {
		String goodsType = req.getParameter("goodsType");
		String goodsName = req.getParameter("goodsName");
		String price = req.getParameter("price");
		String time = req.getParameter("time");
		System.err.println(goodsName);
		if (goodsType == null && "".equals(goodsType)) {
			goodsType = "";
		}

		if (goodsName == null && "".equals(goodsName)) {
			goodsName = "";
		}

		if (price == null && "".equals(price)) {
			price = "";
		}

		if (time == null && "".equals(time)) {
			time = "";
		}
		List<Goods> goodsList = goodsService.searchGoods(goodsName, goodsType,
				price, time,goodsName);
		return goodsList;
	}

	/**
	 * ������Ʒ
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param goodsImage
	 * @param goodsName
	 * @param num
	 * @param price
	 * @param introduce
	 * @param goodsType
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/publish2", produces = "application/json; charset=utf-8")
	public @ResponseBody Map<String, Object> uploadImg(
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session,
			@RequestParam("goodsImage") MultipartFile goodsImage,
			@RequestParam("goodsName") String goodsName,
			@RequestParam("num") String num,
			@RequestParam("price") String price,
			@RequestParam("introduce") String introduce,
			@RequestParam("goodsType") String goodsType,
			@RequestParam("tel") String tel,
			@RequestParam("address") String address) {
		String fileName = goodsImage.getOriginalFilename();
		String username = session.getAttribute("account").toString();
		// �����ڷ�����
		ServletContext ctx = request.getSession().getServletContext();
		String savePath = ctx.getRealPath("/WEB-INF/upload/goods");
		String separator = File.separator;
		File file = new File(savePath);
		if (!file.exists() && !file.isDirectory()) {
			file.mkdir();
		}
		try {
			File f = new File(savePath + separator + fileName);
			goodsImage.transferTo(f);
			Goods good = new Goods();
			good.setGoodsName(new String(goodsName.getBytes("ISO-8859-1"),
					"UTF-8"));
			good.setGoodsType(goodsType);
			good.setImgName(fileName);
			good.setNum(Integer.parseInt(num));
			good.setPrice(Double.parseDouble(price));
			good.setIntroduce(new String(introduce.getBytes("ISO-8859-1"),
					"UTF-8"));
			good.setSeller(username);
			good.setChangenType("��������");
			good.setAddress(new String(address.getBytes("ISO-8859-1"), "UTF-8"));
			good.setTel(new String(tel.getBytes("ISO-8859-1"), "UTF-8"));
			good.setCreateTime(new Date());
			goodsService.create(good);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ��ת������Ʒҳ��
	 * 
	 * @return
	 */
	@RequestMapping(value = "/publishMain", method = { RequestMethod.GET })
	public ModelAndView publishMain(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		try {
			String account = session.getAttribute("account").toString();
			if (account != null && !account.equals("")) {
				mav.setViewName("/goods/publishMain");
			}
		} catch (Exception e) {
			mav.setViewName("redirect:/ctp/account/login");
		}
		return mav;
	}

	/**
	 * �޸���Ʒ��Ϣ
	 * 
	 * @param goods
	 * @return
	 */
	@RequestMapping(value = "/editGoods", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> editGoods(Goods goods) {
		goodsService.update(goods);
		return null;
	}

	/**
	 * ɾ����Ʒ
	 * 
	 * @param goods
	 * @return
	 */
	@RequestMapping(value = "/delGoods", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> delGoods(
			@RequestParam("goodsCode") Integer goodsCode) {
		Map<String, Object> msg = new HashMap<String, Object>();
		try {
			Goods goods = goodsService.findOne(goodsCode);
			goodsService.delete(goods);
			msg.put("msg", "ɾ���ɹ�");
		} catch (Exception e) {
			msg.put("msg", "ɾ��ʧ��");
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * ����ɾ��
	 * 
	 * @param accountList
	 * @return
	 */
	@RequestMapping(value = "/delSelectGoods", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody Map<String, Object> delSelectUser(
			@RequestParam("goodsList[]") List<Integer> goodsList) {
		Map<String, Object> result = new HashMap<String, Object>();
		for (Integer id : goodsList) {
			Goods goods = goodsService.findOne(id);
			goodsService.delete(goods);
			result.put("msg", "ɾ���ɹ�");
		}
		return result;
	}

	/**
	 * ��ת����Ʒ����
	 */
	@RequestMapping(value = "/goodsMain", method = { RequestMethod.GET })
	public ModelAndView goodsMain() {
		ModelAndView mav = new ModelAndView("/goods/goodsMain");
		return mav;
	}

	/**
	 * ��ȡ��Ʒ�б�
	 */
	@RequestMapping(value = "/getGoodsList", method = { RequestMethod.GET })
	@ResponseBody
	public List<Goods> getGoodsList() {
		List<Goods> goodsList = goodsService.findAll();
		return goodsList;
	}

	/**
	 * �����Ʒ
	 */
	@RequestMapping(value = "/auditGoods", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> auditGoods(
			@RequestParam("goodsCode") Integer goodsCode,
			@RequestParam("audit") int audit) {
		Map<String, Object> msg = new HashMap<String, Object>();
		try {
			Goods goods = goodsService.findOne(goodsCode);
			switch (audit) {
			case 0:
				goods.setAudit(1);
				break;
			case 1:
				goods.setAudit(2);
				break;
			case 2:
				goods.setAudit(1);
				break;
			default:
				goods.setAudit(1);
				break;
			}
			goodsService.update(goods);
			msg.put("msg", "��˳ɹ�");
		} catch (Exception e) {
			msg.put("msg", "���ʧ��");
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * ��ȡ���ҳ����е�������Ʒ
	 */
	@RequestMapping(value = "/getGoodsBySeller", method = { RequestMethod.GET })
	@ResponseBody
	public List<Goods> getGoodsBySeller(HttpSession session) {
		String account = session.getAttribute("account").toString();
		List<Goods> goodsList = goodsService.findBySeller(account);
		return goodsList;
	}

	/**
	 * ���ҷ���
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/sendGoods", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> sendGoods(@RequestParam("id") Integer id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Goods goods = goodsService.findOne(id);
			goods.setStatus(2);
			goodsService.update(goods);
			Orders order = orderService.findByCode(goods.getGoodsCode());
			order.setStatus(1);
			orderService.update(order);
			
			MsgBox msgBox = new MsgBox();
			msgBox.setCreateTime(new Date());
			msgBox.setObj(order.getOwner());
			msgBox.setSender(order.getSeller());
			msgBox.setStatus(0);
			msgBox.setContent("�����Ѿ��ͻ�,�뱣�ֵ绰��ͨ,������ϵ");
			msgBoxService.create(msgBox);
			
			result.put("msg", "�����ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "����ʧ��");
		}
		return result;
	}

	@RequestMapping(value = "/confirmGoods", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> confirmGoods(@RequestParam("id") String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Orders order = orderService.findOne(id);

			Goods goods = goodsService.findOne(order.getGoodsCode());
			goodsService.delete(goods);
			
			order.setStatus(2);
			orderService.update(order);
			System.err.println("12312312312");
			MsgBox msgBox = new MsgBox();
			msgBox.setCreateTime(new Date());
			msgBox.setObj(order.getOwner());
			msgBox.setSender("system");
			msgBox.setStatus(0);
			msgBox.setContent("�������:"+order.getOrderId()+"����ɽ���");
			msgBoxService.create(msgBox);
			
			MsgBox msgBox2 = new MsgBox();
			msgBox2.setCreateTime(new Date());
			msgBox2.setObj(order.getSeller());
			msgBox2.setSender("system");
			msgBox2.setStatus(0);
			msgBox2.setContent("�������:"+order.getOrderId()+"����ɽ���");
			msgBoxService.create(msgBox2);
			
			result.put("msg", "�����ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "����ʧ��");
		}
		return result;
	}
}
