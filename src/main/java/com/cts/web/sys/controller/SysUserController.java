package com.cts.web.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cts.web.sys.model.SysUser;
import com.cts.web.sys.model.SysUserRole;
import com.cts.web.sys.model.SysUserRoleId;
import com.cts.web.sys.service.SysUserRoleService;
import com.cts.web.sys.service.SysUserService;

@Controller
@RequestMapping("/ctp/sysUser")
public class SysUserController {
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/main")
	public ModelAndView main(){
		ModelAndView mav = new ModelAndView("sys/SysUserMain");
		return mav;
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<SysUser> list(){
		List<SysUser> list = sysUserService.findAll();
		return list;
	}
	
	@RequestMapping("/toAdd")
	public ModelAndView toAdd(){
		ModelAndView mav = new ModelAndView("sys/SysUserAdd");
		return mav;
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add" , method = {RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> add(SysUser user){
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			user.setAdmin(user.getAccount());
			sysUserService.create(user);
			map.put("msg", "添加成功");
		}catch(Exception e){
			e.printStackTrace();
			map.put("msg", "添加失败");
		}
		return map;
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del" , method = {RequestMethod.POST})
	@ResponseBody
	public Map<String,Object> del(@RequestParam("id") String id){
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			SysUser user = sysUserService.findOne(id);
			sysUserService.delete(user);
			map.put("msg", "删除成功");
		}catch(Exception e){
			e.printStackTrace();
			map.put("msg", "删除失败");
		}
		return map;
	}
	
	/**
	 * 跳转到角色分配页面
	 * @return
	 */
	@RequestMapping(value = "/toSetRole")
	public ModelAndView toSetRole(@RequestParam("ids[]") List<String> ids){
		ModelAndView mav = new ModelAndView("/sys/SysUserRoleMain");
		mav.addObject("ids",ids);
		return mav;
	}
	
	/**
	 * 分配功能
	 * @return
	 */
	@RequestMapping(value = "/setRole")
	@ResponseBody
	public Map<String,Object> setRole(@RequestParam("ids[]") List<String> ids,
			@RequestParam("role[]") List<String> role){
		Map<String,Object> result = new HashMap<String, Object>();
		
		try{
			//写入用户权限
			for(String id:ids){
				
				clearRole(id);
				
				SysUserRole sur = new SysUserRole();
				SysUserRoleId surid = new SysUserRoleId();
				
				surid.setUserId(id);
				for(String r : role){
					surid.setRolecode(r);
					sur.setId(surid);
					sysUserRoleService.create(sur);
				}
			}
			result.put("msg", "添加成功");
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "添加失败");
		}
		return result;
	}

	private void clearRole(String id) {
		// TODO Auto-generated method stub
		List<SysUserRole> sur = sysUserRoleService.findById(id);
		if(sur.size()>0){
			for(SysUserRole s:sur){
				sysUserRoleService.delete(s);
			}
		}
	}
} 
