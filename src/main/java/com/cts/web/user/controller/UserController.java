package com.cts.web.user.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cts.web.user.model.User;
import com.cts.web.user.model.UserInfo;
import com.cts.web.user.service.UserInfoService;
import com.cts.web.user.service.UserService;

/**
 * localhost:8080/ctp/user
 * @author yujunxi
 */
@Controller
@RequestMapping("/ctp/user")
public class UserController {

    @Resource(name="userService")
    private UserService userService;
    
    @Resource(name="userInfoService")
    private UserInfoService userInfoService;
    
    @RequestMapping(value = "userMain" , method = {RequestMethod.GET})
    public ModelAndView userMain(){
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("user/userMain");
    	return mav;
    }
    
    /**
     * 获取用户数据
     * @return
     */
    @RequestMapping(value = "/getUserList" , method = {RequestMethod.GET})
    public @ResponseBody List<User> getUserList(){
    	List<User> userList = new ArrayList<User>();
    	userList = userService.findAll();
    	return userList;
    }
    
    /**
     * 查看用户数据
     * @param userList
     * @return
     */
    @RequestMapping(value = "/getUser" , method = {RequestMethod.POST} )
    public @ResponseBody User getUser(@RequestParam("account") String account){
    	User user = userService.findOne(account);
    	return user;
    }
    
    /**
     * 跳转修改页面
     * @return
     */
    @RequestMapping(value = "/toEditUser" , method = {RequestMethod.POST})
    public ModelAndView toEditUser(@RequestParam("account") String account){
    	ModelAndView mav = new ModelAndView();
    	User user = userService.findOne(account);
    	mav.addObject("user", user);
    	mav.setViewName("/user/userEdit");
    	return mav;
    }
    
    /**
     * 跳转个人中心
     * @return
     */
    @RequestMapping(value = "/space" , method = {RequestMethod.GET})
    public ModelAndView userSpace(){
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/user/userSpace");
    	return mav;
    }
    
    /**
     * 获取个人信息
     * @param session
     * @return
     */
    @RequestMapping(value = "/getUserDetail" , method = {RequestMethod.GET})
    public ModelAndView getUserDetail(HttpSession session){
    	ModelAndView mav = new ModelAndView();
    	String account = session.getAttribute("account").toString();
    	User user = userService.findOne(account);
    	mav.setViewName("/user/userDetail");
    	mav.addObject("userInfo", user.getUserInfo());
    	return mav;
    }
    
    /**
     * 修改个人信息
     * @param req
     * @param rep
     * @return
     */
    @RequestMapping(value = "/editUserDetail" , method = {RequestMethod.POST})
    public Map<String,Object> editUserDetail(HttpServletRequest req 
			, HttpServletResponse rep){
    	String userInfoId = req.getParameter("userInfoCode");
    	int id = Integer.parseInt(userInfoId);
    	System.out.println(id);
    	UserInfo userInfo = userInfoService.findOne(id);
    	userInfo.setAddress("hahaha");
    	userInfoService.update(userInfo);
    	Map<String,String> result = new HashMap<String, String>();
    	result.put("msg", "修改成功");
    	return null;
    }
    
    /**
     * 修改用户数据
     * @return
     */
    @RequestMapping(value = "/editUser" , method = {RequestMethod.POST})
    public @ResponseBody Map<String,Object> editUser(HttpServletRequest req 
			, HttpServletResponse rep){
    	Map<String , Object> result = new HashMap<String , Object>();
    	String account = req.getParameter("account_code");
    	String account2 = req.getParameter("account");
    	String password = req.getParameter("password");
    	
    	User user = userService.findOne(account);
    	user.setAccount(account2);
    	user.setPassword(password);
    	user.setEmail("123@qq.com");
    	user.setCreateTime(new Date());
    	
    	userService.update(user);
    	result.put("msg", "修改成功");
    	return result;
    }
    
    /**
     * 修改个人信息
     * @param username
     * @param info
     * @return
     */
    @RequestMapping(value = "/editUserInfo" , method = { RequestMethod.POST})
    public @ResponseBody Map<String , Object> editUserInfo(@RequestParam("username") String username
    		,UserInfo info){
    	Map<String ,Object> result = new HashMap<String, Object>();
    	User user = userService.findOne(username);
    	user.setUserInfo(info);
    	return result;
    }
    
    
    /**
     * 修改密码
     * @param password
     * @return
     */
    @RequestMapping(value = "/editPassword" , method = {RequestMethod.POST})
    @ResponseBody 
    public Map<String , Object> editPassword(@RequestParam("password") String password){
    	return null;
    }
    
    
    /**
     * 删除用户数据
     * @param userList
     * @return
     */
    @RequestMapping(value = "/delUser" , method = {RequestMethod.POST,RequestMethod.GET} )
    public @ResponseBody Map<String,Object> delUser(@RequestParam("account") String account){
    	Map<String , Object> result = new HashMap<String , Object>();
    	userService.deleteById(account);
    	result.put("msg", "删除成功");
    	return result;
    }

    @RequestMapping(value = "/delSelectUser" , method = {RequestMethod.POST,RequestMethod.GET} )
    public @ResponseBody Map<String,Object> delSelectUser(@RequestParam("accountList[]")
    			List<String> accountList){
    	Map<String , Object> result = new HashMap<String , Object>();
    	for(String str : accountList){
    		User user = userService.findOne(str.toString());
    		userService.delete(user);
    		result.put("msg", "删除成功");
    	}
    	return result;
    }
    
}
