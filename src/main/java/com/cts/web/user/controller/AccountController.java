package com.cts.web.user.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;

import com.cts.common.util.MD5;
import com.cts.web.user.model.User;
import com.cts.web.user.model.UserInfo;
import com.cts.web.user.service.UserService;

@Controller
@RequestMapping("/ctp/account")
public class AccountController {
	
	@Resource(name="userService")
    private UserService userService;
	
	/**
	 * 登录页面
	 * @return
	 */
	@RequestMapping(value = "/login" , method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/user/login");
		return mav;
	}
	
	/**
	 * 系统登录验证
	 * @param user
	 * @param req
	 * @param rep
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/login/submit" , method={RequestMethod.POST})
	public ModelAndView submit(User user,HttpServletRequest req 
			, HttpServletResponse rep ,HttpSession session){
		ModelAndView mav = new ModelAndView();
		MD5 md5 = new MD5();
		
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		
		Boolean flag = userService.validate(account, md5.getMD5ofStr(password));
		if(flag){
			session.setAttribute("account", account);
			mav.setViewName("redirect:/ctp");
		}else{
			mav.addObject("msg", "账号或密码不准确");
			mav.setViewName("/user/login");
		}
		return mav;
		
	} 
	
	/**
	 * 用户退出
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/logout" , method = {RequestMethod.GET})
	public ModelAndView logout(HttpSession session){
		ModelAndView mav = new ModelAndView();
		session.removeAttribute("account");
		mav.setViewName("redirect:/ctp");
		return mav;
	}
	
	/**
	 * 注册页面
	 * @return
	 */
	@RequestMapping(value = "/register" , method = {RequestMethod.GET})
	public ModelAndView toRegisterPage(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/user/register");
		return mav;
	}
	
	/**
	 * 注册
	 * @param req
	 * @param rep
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/register/submit" , method = {RequestMethod.POST})
	public ModelAndView Register(User user,HttpServletRequest req 
			, HttpServletResponse rep ,HttpSession session){
		ModelAndView mav = new ModelAndView();
		MD5 md5 = new MD5();
		
		String account = user.getAccount();
		
		boolean flag = userService.isExist(account);
		if(!flag){
			
			String password = md5.getMD5ofStr(user.getPassword());
			
			user.setAccount(account);
			user.setPassword(password);
			user.setCreateTime(new Date());
			user.setStatus(0);
			UserInfo userInfo = new UserInfo();
			user.setUserInfo(userInfo);
					
			userService.create(user);
			
			session.setAttribute("account", account);
			mav.setViewName("redirect:/ctp");
		}else{
			mav.addObject("msg", "该账号已存在");
			mav.setViewName("/user/register");
		}
		return mav;
	}

}
