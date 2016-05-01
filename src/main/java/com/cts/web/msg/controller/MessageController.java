package com.cts.web.msg.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cts.web.msg.model.Message;
import com.cts.web.msg.service.MessageService;

@Controller
@RequestMapping("/ctp/message")
public class MessageController {
	
	@Resource(name="messageService")
	private MessageService messageService;
	
	/**
	 * ����
	 * @return
	 */
	@RequestMapping(value = "/addMsg",method = {RequestMethod.POST})
	@ResponseBody
	public Map<String,String> addMsg(HttpServletRequest req,HttpServletResponse rep){
		Map<String,String> msg = new HashMap<String, String>();
		String author = req.getParameter("author");
		String content = req.getParameter("content");
		String goodsCode = req.getParameter("goodsCode");
		String seller = req.getParameter("seller");
		try{
			Message message = new Message();
			message.setCreateTime(new Date());
			message.setAuthor(author);
			message.setContent(content.trim());
			message.setGoodsCode(Integer.parseInt(goodsCode));
			message.setUid(seller);
			messageService.create(message);
			msg.put("message", "���Գɹ�");
		}catch(Exception e){
			msg.put("message", "����ʧ��");
			e.printStackTrace();
		}
		return msg;
	}
	
	/**
	 * �鿴����
	 * @param goodsCode
	 * @return
	 */
	@RequestMapping(value = "/getMsg",method = {RequestMethod.GET})
	@ResponseBody
	public List<Message> getMsg(@RequestParam("goodsCode") Integer goodsCode){
		List<Message> msgList = messageService.getMsgByCode(goodsCode);
		return msgList;
	}
	
	/**
	 * ��ת�����Թ���
	 */
	@RequestMapping(value="/msgMain", method = {RequestMethod.GET})
	public ModelAndView msgMain(){
		ModelAndView mav = new ModelAndView("/msg/msgMain");
		return mav;
	}
	/**
	 * ��ȡ��Ʒ�б�
	 */
	@RequestMapping(value = "/getMsgList" , method = {RequestMethod.GET})
	@ResponseBody
	public List<Message> getMsgList(){
		List<Message> msgList = messageService.findAll();
		return msgList;
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value="/msg", method = {RequestMethod.GET})
	public ModelAndView msg(){
		ModelAndView mav = new ModelAndView("/msg/msg");
		return mav;
	}
	
	/**
	 * �޸�
	 * @param id
	 * @param req
	 * @param rep
	 * @return
	 */
	@RequestMapping(value = "/edit",method = {RequestMethod.POST})
	@ResponseBody
	public Map<String,String> edit(@RequestParam("id") Integer id
			,HttpServletRequest req,HttpServletResponse rep){
		Map<String,String> msg = new HashMap<String, String>();
		try{
			Message m = messageService.findOne(id);
			m.setContent(req.getParameter("content"));
			messageService.update(m);
			msg.put("msg", "�޸�ʧ��");
		}catch(Exception e){
			e.printStackTrace();
			msg.put("msg", "�޸�ʧ��");
		}
		return msg;
	} 
	
	/**
	 * ɾ������
	 * @return
	 */
	@RequestMapping(value = "/delMsg",method = {RequestMethod.POST})
	@ResponseBody
	public Map<String,String> delMsg(@RequestParam("id") Integer id){
		Map<String,String> msg = new HashMap<String, String>();
		Message message = messageService.findOne(id);
		messageService.delete(message);
		return msg;
	}

	/**
	 * ����ɾ��
	 * @param msgList
	 * @return
	 */
	@RequestMapping(value = "/delSelect", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody Map<String, Object> delSelect(
			@RequestParam("msgList[]") List<Integer> msgList) {
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			for (Integer str : msgList) {
				Message msg = messageService.findOne(str);
				messageService.delete(msg);
			}
			result.put("msg", "ɾ���ɹ�");
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "ɾ��ʧ��");
		}
		
		return result;
	}
}
