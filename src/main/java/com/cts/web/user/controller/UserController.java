package com.cts.web.user.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
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

import com.cts.common.util.MD5;
import com.cts.web.goods.model.Goods;
import com.cts.web.goods.service.GoodsService;
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
    
    @Autowired
    private GoodsService goodsService;
    
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
    public @ResponseBody User getUser(@RequestParam("id") String id){
    	return userService.findOne(id);
    }
    
    /**
     * 跳转修改页面
     * @return
     */
    @RequestMapping(value = "/toEditUser" , method = {RequestMethod.POST})
    public ModelAndView toEditUser(@RequestParam("id") String id){
    	ModelAndView mav = new ModelAndView();
    	User user = userService.findOne(id);
    	mav.addObject("user", user);
    	mav.setViewName("/user/userEdit");
    	return mav;
    }
    
    /**
     * 跳转个人中心
     * @return
     */
    @RequestMapping(value = "/space" , method = {RequestMethod.GET})
    public ModelAndView userSpace(HttpSession session){
    	ModelAndView mav = new ModelAndView();
    	try{
    		String account = session.getAttribute("account").toString();
    		List<Goods> goodsList = goodsService.findBySeller(account);
    		if(account!=null&&!account.equals("")){
    			mav.addObject("num",goodsList.size());
    			mav.addObject("time", new Date());
    			mav.setViewName("/user/userSpace");
    		}
    	}catch(Exception e){
    		mav.setViewName("redirect:/ctp/account/login");
    	}
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
    	List<User> userList = userService.findByAccount(account);
    	User user = userList.get(0);
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
    @ResponseBody
    public Map<String,Object> editUserDetail(HttpServletRequest req 
			, HttpServletResponse rep){
    	String userInfoId = req.getParameter("userInfoCode");
    	int id = Integer.parseInt(userInfoId);
    	String address = req.getParameter("address");
    	String tel = req.getParameter("tel");
    	String username = req.getParameter("username");
    	String realname = req.getParameter("realname");
    	String email = req.getParameter("email");
    	Map<String,Object> result = new HashMap<String, Object>();
    	try{
    		UserInfo userInfo = userInfoService.findOne(id);
        	userInfo.setAddress(address);
        	userInfo.setTel(tel);
        	userInfo.setUsername(username);
        	userInfo.setRealname(realname);
        	userInfo.setEmail(email);
        	userInfoService.update(userInfo);
        	result.put("msg", "修改成功");
    	}catch(Exception e){
    		result.put("msg", "修改失败");
    	}
    	return result;
    }
    
    /**
     * 修改用户数据
     * @return
     */
    @RequestMapping(value = "/editUser" , method = {RequestMethod.POST})
    public @ResponseBody Map<String,Object> editUser(HttpServletRequest req 
			, HttpServletResponse rep){
    	Map<String , Object> result = new HashMap<String , Object>();
    	String id = req.getParameter("id");
    	String account = req.getParameter("account");
    	String password = req.getParameter("password");
    	String mail = req.getParameter("mail");
    	try{
    		User user = userService.findOne(id);
        	user.setAccount(account);
        	user.setEmail(mail);
        	
        	if(password!=null&&!"".equals(password)){
        		user.setPassword(MD5.getMD5(password));
        	}
        	userService.update(user);
        	result.put("msg", "修改成功");
    	}catch(Exception e){
    		e.printStackTrace();
    		result.put("msg", "修改失败");
    	}
    	return result;
    }
    
    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> add(User user,HttpServletRequest req 
			, HttpServletResponse rep ,HttpSession session){
    	Map<String,Object> msg = new HashMap<String, Object>();
		MD5 md5 = new MD5();
		
		String account = user.getAccount();
		
		try{
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
				msg.put("msg", "添加成功");
			}else{
				msg.put("msg", "系统存在用户");
			}
		}catch(Exception e){
			e.printStackTrace();
			msg.put("msg", "添加失败");
		}
		
		return null;
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
    
    @RequestMapping(value = "/changeStatus" , method = { RequestMethod.POST})
    public @ResponseBody Map<String , Object> change(@RequestParam("id") String id){
    	Map<String ,Object> result = new HashMap<String, Object>();
    	try{
    		User user = userService.findOne(id);
        	if(user.getStatus()==0){
        		user.setStatus(1);
        	}else{
        		user.setStatus(0);
        	}
        	userService.update(user);
        	result.put("msg", "操作成功");
    	}catch(Exception e){
    		result.put("msg", "操作失败");
    	}
    	return result;
    }
    
    /**
     * 修改密码
     * @param password
     * @return
     */
    @RequestMapping(value = "/resetPwd" , method = {RequestMethod.GET})
    @ResponseBody 
    public Map<String , Object> resetPwd(){
    	Map<String,Object> result = new HashMap<String, Object>();
    	try{
    		result.put("pwd", getRandomPwd());
    	}catch(Exception e){
    		e.printStackTrace();
    		result.put("pwd", "");
    	}
    	return result;
    }
    
	public String getRandomPwd() {
		Random rd = new Random();
		String n = "";
		int getNum;
		do {
			getNum = Math.abs(rd.nextInt()) % 10 + 48;// 产生数字0-9的随机数
			// getNum = Math.abs(rd.nextInt())%26 + 97;//产生字母a-z的随机数
			char num1 = (char) getNum;
			String dn = Character.toString(num1);
			n += dn;
		} while (n.length() < 6);
		return n;
	}
    
    
    /**
     * 删除用户数据
     * @param userList
     * @return
     */
    @RequestMapping(value = "/delUser" , method = {RequestMethod.POST,RequestMethod.GET} )
    public @ResponseBody Map<String,Object> delUser(@RequestParam("id") String id){
    	Map<String , Object> result = new HashMap<String , Object>();
    	userService.deleteById(id);
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
