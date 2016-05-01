package com.cts.web.sys.controller;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cts.common.model.TreeNode;
import com.cts.common.util.MD5;
import com.cts.web.base.model.Image;
import com.cts.web.base.model.Notice;
import com.cts.web.base.service.ImageService;
import com.cts.web.sys.model.Function;
import com.cts.web.sys.model.Role;
import com.cts.web.sys.model.RoleFunction;
import com.cts.web.sys.model.RoleFunctionId;
import com.cts.web.sys.model.SysUser;
import com.cts.web.sys.service.FunctionService;
import com.cts.web.sys.service.RoleFunctionService;
import com.cts.web.sys.service.RoleService;
import com.cts.web.sys.service.SysUserRoleService;
import com.cts.web.sys.service.SysUserService;
import com.cts.web.user.model.User;

@Controller
@RequestMapping("/ctp/system")
public class SysController {
	
	@Resource(name="imageService")
	private ImageService imageService;
	
	@Resource(name="functionService")
	private FunctionService functionService;
	
	@Resource(name="roleService")
	private RoleService roleService;
	
	@Resource(name="roleFunctionService")
	private RoleFunctionService roleFunctionService;
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	
	//***************************系统登录********************************************//
	
	/**
	 * 登录页面
	 * @return
	 */
	@RequestMapping(value = "login", method = {RequestMethod.GET})
	public ModelAndView login(){
		ModelAndView mav = new ModelAndView("sys/SysLogin");
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
		session.removeAttribute("admin");
		mav.setViewName("redirect:/ctp/system/login");
		return mav;
	}
	
	/**
	 * 登录校验
	 * @param account
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "submit", method = {RequestMethod.POST})
	public ModelAndView submit(User user,HttpServletRequest req 
			, HttpServletResponse rep ,HttpSession session){
		ModelAndView mav = new ModelAndView();
		MD5 md5 = new MD5();
		
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		
		List<SysUser> userList = sysUserService.validate(account, password);
		if(userList.size()>0){
			session.setAttribute("admin", userList);
			mav.setViewName("redirect:/ctp/sys");
		}else{
			mav.setViewName("sys/SysLogin");
			mav.addObject("msg", "账号或密码错误");
		}
		return mav; 
	}
	
	
	//***************************系统广告公告设置************************************//
	
	/**
	 * 欢迎页
	 * @return
	 */
	@RequestMapping(value = "welcome", method = {RequestMethod.GET})
	public ModelAndView welcome(){
		ModelAndView mav = new ModelAndView("sys/welcome");
		return mav;
	}
	
	/**
	 * 系统后台主页
	 * @return
	 */
	@RequestMapping("/sysMain")
	public ModelAndView sysMain(){
		ModelAndView mav = new ModelAndView("sys/sysMain");
		return mav;
	}
	
	/**
	 * 轮播图片地址管理
	 * @param request
	 * @param imgFile
	 * @param content
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value="/upload", produces = "application/json; charset=utf-8")
	public @ResponseBody Map<String,String> uploadImg(HttpServletRequest request,
			HttpServletResponse response ,
			@RequestParam("imgFile") MultipartFile imgFile){
		Map<String,String> result = new HashMap<String, String>();
		String title = request.getParameter("title");
		String url = request.getParameter("url");
		String fileName = imgFile.getOriginalFilename();
		String message = "";
		//保存在服务器
		ServletContext ctx = request.getSession().getServletContext();
		String savePath = ctx.getRealPath("/WEB-INF/upload/carousel");
		String separator = File.separator;
		File file = new File(savePath);
		if(!file.exists()&&!file.isDirectory()){
			file.mkdir();
		}
		try{
			File f = new File(savePath + separator + fileName);
			imgFile.transferTo(f);
			Image image = new Image();
			image.setFilePath(fileName);
			image.setCreateTime(new Date());
			
			image.setContent(new String(title.getBytes("iso-8859-1"),"utf-8"));
			imageService.create(image);
			result.put("msg", "发布成功");
        }catch (Exception e) {
        	result.put("msg", "发布失败");
            e.printStackTrace();
        }
		return result;
	}
	
	/**
	 * 获取轮播图片地址
	 * @return
	 */
	@RequestMapping(value = "/getCarousel")
	public @ResponseBody List<Image> getImage(){
		List<Image> image = imageService.findAll();
		Collections.sort(image,new newComparator());
		return image;
	}
	
	//按时间大小进行排序
	private static class newComparator implements Comparator {  
		public int compare(Object object1, Object object2) { 
			Image p1 = (Image) object1; 
			Image p2 = (Image) object2;  
		    return p2.getCreateTime().compareTo(p1.getCreateTime());  
		}  
	} 
	/**
	 * 删除
	 * @param id
	 * @return
	 */
    @RequestMapping(value = "/del" , method = {RequestMethod.POST,RequestMethod.GET} )
    public @ResponseBody Map<String,Object> del(@RequestParam("id") String id){
    	Map<String , Object> result = new HashMap<String , Object>();
    	imageService.deleteById(id);
    	result.put("msg", "删除成功");
    	return result;
    }
	
	//***************************系统角色配置************************************//
	
	/**
	 * 权限设置
	 * @return
	 */
	@RequestMapping(value = "/roleMain")
	public ModelAndView roleMain(){
		ModelAndView mav = new ModelAndView("/sys/roleMain");
		return mav;
	}
	
	/**
	 * 获取角色列表
	 * @return
	 */
	@RequestMapping(value = "/getRoleList")
	@ResponseBody
	public List<Role> getRoleList(){
		List<Role> roleList = roleService.findAll();
		return roleList;
	}
	
	/**
	 * 删除角色
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delRole")
	@ResponseBody
	public Map<String,Object> delRole(@RequestParam("id") Integer id){
		Map<String,Object> result = new HashMap<String, Object>();
		try{
			roleService.deleteById(id);
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "删除失败");
		}
		result.put("msg", "删除成功");
		return result;
	}
	
	/**
	 * 跳转到添加角色页面
	 * @return
	 */
	@RequestMapping(value = "/toAddRole")
	public ModelAndView toAddRole(){
		ModelAndView mav = new ModelAndView("/sys/roleAdd");
		return mav;
	}
	
	/**
	 * 添加角色
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "/addRole")
	@ResponseBody
	public Map<String,Object> addRole(Role role){
		Map<String,Object> result = new HashMap<String, Object>();
		try{
			roleService.create(role);
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "添加失败");
		}
		result.put("msg", "添加成功");
		return result;
	}
	
	/**
	 * 添加角色
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "/delFunc")
	@ResponseBody
	public Map<String,Object> delFunc(@RequestParam("id") String id){
		Map<String,Object> result = new HashMap<String, Object>();
		try{
			functionService.deleteById(id);
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "删除失败");
		}
		result.put("msg", "删除成功");
		return result;
	}
	/**
	 * 跳转到分配功能页面
	 * @return
	 */
	@RequestMapping(value = "/toSetFunc")
	public ModelAndView toSetFunc(@RequestParam("ids[]") List<String> ids){
		ModelAndView mav = new ModelAndView("/sys/roleFuncMain");
		mav.addObject("ids",ids);
		return mav;
	}
	
	/**
	 * 分配功能
	 * @return
	 */
	@RequestMapping(value = "/setFunc")
	@ResponseBody
	public Map<String,Object> setFunc(@RequestParam("ids[]") List<String> ids,
			@RequestParam("func[]") List<String> func){
		Map<String,Object> result = new HashMap<String, Object>();
		
		try{
			//写入用户权限
			for(String id:ids){
				
				clearRoleFunction(id);
				
				RoleFunction rf = new RoleFunction();
				RoleFunctionId rfId= new RoleFunctionId();
				//权限ID
				rfId.setRoleId(id);
				//功能ID
				for(String fun : func){
					rfId.setFuncId(fun);
					rf.setId(rfId);
					roleFunctionService.create(rf);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "添加失败");
		}
		result.put("msg", "添加成功");
		return result;
	}
	
	/**
	 * 清空权限的功能配置
	 * @param id
	 */
	public void clearRoleFunction(String id){
		List<RoleFunction> roleFunctionList = roleFunctionService.getRoleFunctionListById(id);
		try{
			for(RoleFunction rf : roleFunctionList){
				roleFunctionService.delete(rf);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//***************************系统功能配置************************************//
	
	
	/**
	 * 功能配置
	 * @return
	 */
	@RequestMapping(value = "/funcMain")
	public ModelAndView funcMain(){
		ModelAndView mav = new ModelAndView("/sys/funcMain");
		return mav;
	}
	
	/**
	 * 获取功能列表
	 * @return
	 */
	@RequestMapping(value = "/getFuncList")
	@ResponseBody
	public List<Function> getFuncList(){
		List<Function> function = functionService.findAll();
		return function;
	}
	
	/**
	 * 获取功能树
	 * @return
	 */
	@RequestMapping(value = "/getFunction")
	@ResponseBody
	public List<TreeNode> getFunction(){
		List<TreeNode> treeNodes = functionService.getFunction();
		return treeNodes;
	}
	
	/**
	 * 获取功能树
	 * @return
	 */
	@RequestMapping(value = "/getRole")
	@ResponseBody
	public List<TreeNode> getRole(){
		List<TreeNode> treeNodes = roleService.getRole();
		return treeNodes;
	}
}
