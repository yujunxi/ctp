package com.cts.web.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
    
    @RequestMapping(value = "/getUserList" , method = {RequestMethod.GET})
    public @ResponseBody List<User> getUserList(){
    	List<User> userList = new ArrayList<User>();
    	userList = userService.findAll();
    	return userList;
    }
    
    @RequestMapping(value = "toEditUser" , method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView toEditUser(){
    	ModelAndView mav = new ModelAndView();
    	return mav;
    }
    
    @RequestMapping(value = "editUser" , method = {RequestMethod.POST})
    public ModelAndView editUser(){
    	ModelAndView mav = new ModelAndView();
    	return mav;
    }
    
    @RequestMapping(value = "deleteUser" , method = {RequestMethod.POST})
    public @ResponseBody Map<String,String> deleteUser(User user){
    	Map<String ,String> result = new HashMap<String, String>();
    	try{
    		userService.delete(user);
    		result.put("msg","É¾³ý³É¹¦");
    		result.put("count","¹²É¾³ý10Ìõ");
    	}catch(Exception e){
    		result.put("msg", "É¾³ýÊ§°Ü");
    	}
    	return result;
    }
    

}
