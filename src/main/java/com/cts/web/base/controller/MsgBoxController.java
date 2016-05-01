package com.cts.web.base.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cts.web.base.model.MsgBox;
import com.cts.web.base.service.MsgBoxService;

@Controller
@RequestMapping("/ctp/box")
public class MsgBoxController {
	
	@Autowired
	private MsgBoxService msgBoxService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<MsgBox> getMsgBox(HttpSession session){
		List<MsgBox> list = msgBoxService.findByObj(session.getAttribute("account").toString());
		return list;
	}
	
	@RequestMapping("/list2")
	@ResponseBody
	public List<MsgBox> getMsgBox2(HttpSession session){
		List<MsgBox> list = msgBoxService.findByObj2(session.getAttribute("account").toString());
		return list;
	}
	
	@RequestMapping("/main")
	public ModelAndView main(HttpSession session){
		ModelAndView mav = new ModelAndView("/box/msgBox");
		List<MsgBox> list = msgBoxService.findByObj(session.getAttribute("account").toString());
		for(MsgBox box:list){
			box.setStatus(1);
			msgBoxService.update(box);
		}
		return mav;
	}
		
}
