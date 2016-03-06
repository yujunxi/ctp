package com.cts.web.user.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cts.web.user.model.User;
import com.cts.web.user.model.UserInfo;
import com.cts.web.user.service.UserService;

@Controller
@RequestMapping("/ctp/login")
public class LoginController {
	
	@Resource(name="userService")
    private UserService userService;
	
	@RequestMapping(value = "" , method = RequestMethod.GET)
	public ModelAndView toLoginPage(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/user/login");
		return mav;
	}
	
	@RequestMapping(value = "/loginIn")
	public ModelAndView RignIn(User user,HttpServletRequest req 
			, HttpServletResponse rep ,HttpSession session){
		ModelAndView mav = new ModelAndView();
		
		String account = req.getParameter("account");
		String password = req.getParameter("password");
			
		session.setAttribute("account", account);
		mav.setViewName("redirect:/ctp/index");
		return mav;
		
	} 
	
}
