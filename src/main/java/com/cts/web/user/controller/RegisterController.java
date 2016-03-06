package com.cts.web.user.controller;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
@RequestMapping("/ctp/admin")
public class RegisterController {
	
	@Resource(name="userService")
    private UserService userService;
	
	@RequestMapping(value = "/reg" , method = RequestMethod.GET)
	public ModelAndView toRegisterPage(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/user/register");
		return mav;
	}
	
	@RequestMapping(value = "/register" , method = RequestMethod.POST)
	public ModelAndView Register(HttpServletRequest req 
			, HttpServletResponse rep ,HttpSession session){
		ModelAndView mav = new ModelAndView();
		
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		
		User user = new User();
		user.setAccount(account);
		user.setPassword(password);
		user.setCreateTime(new Date());
				
		UserInfo userInfo = new UserInfo();
		user.setUserInfo(userInfo);
				
		userService.create(user);
		
		session.setAttribute("account", account);
		
		mav.setViewName("index");
		return mav;
		
	}
}
