package com.cts.web.base.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cts.web.base.model.Serv;
import com.cts.web.base.service.ServService;
import com.cts.web.sys.model.SysUser;

@Controller
@RequestMapping("/ctp/serv")
public class ServController {
	
	@Autowired
	private ServService servService;
	
	@RequestMapping("/main")
	public ModelAndView main(){
		ModelAndView mav = new ModelAndView("/box/servMain");
		return mav;
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public List<Serv> list(){
		return servService.findAll();
	}
	
	@RequestMapping(value = "/add",method = {RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> add(Serv notice,HttpSession session){
		Map<String,Object> msg = new HashMap<String, Object>();
		try{
			servService.create(notice);
			msg.put("msg", "发布成功");
		}catch(Exception e){
			e.printStackTrace();
			msg.put("msg", "发布失败");
		}
		return msg;
	}
	
	@RequestMapping(value = "/edit",method = {RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> edit(Serv notice){
		Map<String,Object> msg = new HashMap<String, Object>();
		try{
			Serv n = servService.findOne(notice.getId());
			n.setContent(notice.getContent());
			servService.update(n);
			msg.put("msg", "修改成功");
		}catch(Exception e){
			e.printStackTrace();
			msg.put("msg", "修改失败");
		}
		return msg;
	}
	
    @RequestMapping(value = "/del" , method = {RequestMethod.POST,RequestMethod.GET} )
    public @ResponseBody Map<String,Object> del(@RequestParam("id") String id){
    	Map<String , Object> result = new HashMap<String , Object>();
    	servService.deleteById(id);
    	result.put("msg", "删除成功");
    	return result;
    }

    @RequestMapping(value = "/delete" , method = {RequestMethod.POST,RequestMethod.GET} )
    public @ResponseBody Map<String,Object> delete(@RequestParam("accountList[]")
    			List<String> accountList){
    	Map<String , Object> result = new HashMap<String , Object>();
    	for(String str : accountList){
    		Serv notice = servService.findOne(str);
    		servService.delete(notice);
    		result.put("msg", "删除成功");
    	}
    	return result;
    }
}
