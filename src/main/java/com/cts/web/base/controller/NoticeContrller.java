package com.cts.web.base.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cts.web.base.model.Notice;
import com.cts.web.base.service.NoticeService;
import com.cts.web.sys.model.SysUser;

@Controller
@RequestMapping("/ctp/notice")
public class NoticeContrller {
	
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<Notice> list(){
		return noticeService.findAll();
	}
	
	@RequestMapping(value = "/list2",method={RequestMethod.GET})
	@ResponseBody
	public List<Notice> list2(){
		List<Notice> noticeList = noticeService.findAll();
		Collections.sort(noticeList,new newComparator());
		return noticeList;
	}
	
	//按时间大小进行排序
	private static class newComparator implements Comparator {  
	      public int compare(Object object1, Object object2) { 
	    	  Notice p1 = (Notice) object1; 
	    	  Notice p2 = (Notice) object2;  
	          return p2.getCreateTime().compareTo(p1.getCreateTime());  
	      }  
	}  
	
	@RequestMapping("/get")
	@ResponseBody
	public Notice get(@RequestParam("id") String id){
		return noticeService.findOne(id);
	}
	
	@RequestMapping(value = "/add",method = {RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> add(Notice notice,HttpSession session){
		Map<String,Object> msg = new HashMap<String, Object>();
		try{
			notice.setCreateTime(new Date());
			List<SysUser> userList = (List<SysUser>) session.getAttribute("admin");
			SysUser user = userList.get(0);
			notice.setAuthor(user.getAdmin());
			noticeService.create(notice);
			msg.put("msg", "发布成功");
		}catch(Exception e){
			e.printStackTrace();
			msg.put("msg", "发布失败");
		}
		return msg;
	}
	
	@RequestMapping(value = "/edit",method = {RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> edit(Notice notice){
		Map<String,Object> msg = new HashMap<String, Object>();
		try{
			Notice n = noticeService.findOne(notice.getId());
			System.err.println(notice.getContent());
			n.setContent(notice.getContent());
			noticeService.update(n);
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
    	noticeService.deleteById(id);
    	result.put("msg", "删除成功");
    	return result;
    }

    @RequestMapping(value = "/delete" , method = {RequestMethod.POST,RequestMethod.GET} )
    public @ResponseBody Map<String,Object> delete(@RequestParam("accountList[]")
    			List<String> accountList){
    	Map<String , Object> result = new HashMap<String , Object>();
    	for(String str : accountList){
    		Notice notice = noticeService.findOne(str);
    		noticeService.delete(notice);
    		result.put("msg", "删除成功");
    	}
    	return result;
    }

}
