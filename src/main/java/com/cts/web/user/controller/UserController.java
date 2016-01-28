package com.cts.web.user.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cts.web.user.model.User;
import com.cts.web.user.model.UserInfo;
import com.cts.web.user.service.UserInfoService;
import com.cts.web.user.service.UserService;

@Controller
@RequestMapping("/ctp/user")
public class UserController {

    @Resource(name="userService")
    private UserService userService;
    
    @Resource(name="userInfoService")
    private UserInfoService userInfoService;
    
    @RequestMapping(value="/count",method=RequestMethod.GET)
    public ModelAndView userCount() {
        
        int count = userService.findAll().size();
        
        ModelAndView mv = new ModelAndView();
        mv.addObject("userCount", count);
        mv.setViewName("user/userCount");
        return mv;
    }
    
    @RequestMapping(value="/list",method=RequestMethod.GET)
    public ModelAndView getUserlist(Model model){
        
        ModelAndView mv = new ModelAndView();
        List<User> userList = userService.findAll();
        System.out.println("log======table 'user' all records:"+userList.size());
        mv.addObject("userList", userList);
        mv.setViewName("user/list");
        return mv;
    }
    
    @RequestMapping(value="/add",method=RequestMethod.GET)
    public ModelAndView getAdd(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/add");
        return mv;
    }
    
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public String add(@ModelAttribute("user") User user){
    	UserInfo userInfo = new UserInfo();
    	user.setUserInfo(userInfo);
        userService.create(user);
        System.err.println(user.getUserInfo().getTel());
        return "redirect:/user/list";
    }
    
    @RequestMapping(value="/show/{userid}",method=RequestMethod.GET)
    public ModelAndView show(@PathVariable Long userid){
        User user = userService.findOne(userid);
        System.err.println(user.getUserInfo().getTel());
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", user);
        mv.setViewName("user/detail");
        return mv;
    }
    
    @RequestMapping(value="/del/{userid}",method=RequestMethod.GET)
    public String del(@PathVariable Long userid){
        userService.deleteById(userid);
        
        return "redirect:/user/list";
    }
    
    @RequestMapping(value="/edit/{userid}",method=RequestMethod.GET)
    public ModelAndView getEdit(@PathVariable Long userid,Model model){
        User user = userService.findOne(userid);
        model.addAttribute("userAttribute", user);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/edit");
        return mv;
    }
    
    @RequestMapping(value="/save/{userid}",method=RequestMethod.POST)
    public String saveEdit(@ModelAttribute("userAttribute") User user,@PathVariable Long userid){
        userService.update(user);
        return "redirect:/user/list";
    }
}
