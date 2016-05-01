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
	
	
	//***************************ϵͳ��¼********************************************//
	
	/**
	 * ��¼ҳ��
	 * @return
	 */
	@RequestMapping(value = "login", method = {RequestMethod.GET})
	public ModelAndView login(){
		ModelAndView mav = new ModelAndView("sys/SysLogin");
		return mav;
	}
	
	/**
	 * �û��˳�
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
	 * ��¼У��
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
			mav.addObject("msg", "�˺Ż��������");
		}
		return mav; 
	}
	
	
	//***************************ϵͳ��湫������************************************//
	
	/**
	 * ��ӭҳ
	 * @return
	 */
	@RequestMapping(value = "welcome", method = {RequestMethod.GET})
	public ModelAndView welcome(){
		ModelAndView mav = new ModelAndView("sys/welcome");
		return mav;
	}
	
	/**
	 * ϵͳ��̨��ҳ
	 * @return
	 */
	@RequestMapping("/sysMain")
	public ModelAndView sysMain(){
		ModelAndView mav = new ModelAndView("sys/sysMain");
		return mav;
	}
	
	/**
	 * �ֲ�ͼƬ��ַ����
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
		//�����ڷ�����
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
			result.put("msg", "�����ɹ�");
        }catch (Exception e) {
        	result.put("msg", "����ʧ��");
            e.printStackTrace();
        }
		return result;
	}
	
	/**
	 * ��ȡ�ֲ�ͼƬ��ַ
	 * @return
	 */
	@RequestMapping(value = "/getCarousel")
	public @ResponseBody List<Image> getImage(){
		List<Image> image = imageService.findAll();
		Collections.sort(image,new newComparator());
		return image;
	}
	
	//��ʱ���С��������
	private static class newComparator implements Comparator {  
		public int compare(Object object1, Object object2) { 
			Image p1 = (Image) object1; 
			Image p2 = (Image) object2;  
		    return p2.getCreateTime().compareTo(p1.getCreateTime());  
		}  
	} 
	/**
	 * ɾ��
	 * @param id
	 * @return
	 */
    @RequestMapping(value = "/del" , method = {RequestMethod.POST,RequestMethod.GET} )
    public @ResponseBody Map<String,Object> del(@RequestParam("id") String id){
    	Map<String , Object> result = new HashMap<String , Object>();
    	imageService.deleteById(id);
    	result.put("msg", "ɾ���ɹ�");
    	return result;
    }
	
	//***************************ϵͳ��ɫ����************************************//
	
	/**
	 * Ȩ������
	 * @return
	 */
	@RequestMapping(value = "/roleMain")
	public ModelAndView roleMain(){
		ModelAndView mav = new ModelAndView("/sys/roleMain");
		return mav;
	}
	
	/**
	 * ��ȡ��ɫ�б�
	 * @return
	 */
	@RequestMapping(value = "/getRoleList")
	@ResponseBody
	public List<Role> getRoleList(){
		List<Role> roleList = roleService.findAll();
		return roleList;
	}
	
	/**
	 * ɾ����ɫ
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
			result.put("msg", "ɾ��ʧ��");
		}
		result.put("msg", "ɾ���ɹ�");
		return result;
	}
	
	/**
	 * ��ת����ӽ�ɫҳ��
	 * @return
	 */
	@RequestMapping(value = "/toAddRole")
	public ModelAndView toAddRole(){
		ModelAndView mav = new ModelAndView("/sys/roleAdd");
		return mav;
	}
	
	/**
	 * ��ӽ�ɫ
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
			result.put("msg", "���ʧ��");
		}
		result.put("msg", "��ӳɹ�");
		return result;
	}
	
	/**
	 * ��ӽ�ɫ
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
			result.put("msg", "ɾ��ʧ��");
		}
		result.put("msg", "ɾ���ɹ�");
		return result;
	}
	/**
	 * ��ת�����书��ҳ��
	 * @return
	 */
	@RequestMapping(value = "/toSetFunc")
	public ModelAndView toSetFunc(@RequestParam("ids[]") List<String> ids){
		ModelAndView mav = new ModelAndView("/sys/roleFuncMain");
		mav.addObject("ids",ids);
		return mav;
	}
	
	/**
	 * ���书��
	 * @return
	 */
	@RequestMapping(value = "/setFunc")
	@ResponseBody
	public Map<String,Object> setFunc(@RequestParam("ids[]") List<String> ids,
			@RequestParam("func[]") List<String> func){
		Map<String,Object> result = new HashMap<String, Object>();
		
		try{
			//д���û�Ȩ��
			for(String id:ids){
				
				clearRoleFunction(id);
				
				RoleFunction rf = new RoleFunction();
				RoleFunctionId rfId= new RoleFunctionId();
				//Ȩ��ID
				rfId.setRoleId(id);
				//����ID
				for(String fun : func){
					rfId.setFuncId(fun);
					rf.setId(rfId);
					roleFunctionService.create(rf);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			result.put("msg", "���ʧ��");
		}
		result.put("msg", "��ӳɹ�");
		return result;
	}
	
	/**
	 * ���Ȩ�޵Ĺ�������
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
	
	//***************************ϵͳ��������************************************//
	
	
	/**
	 * ��������
	 * @return
	 */
	@RequestMapping(value = "/funcMain")
	public ModelAndView funcMain(){
		ModelAndView mav = new ModelAndView("/sys/funcMain");
		return mav;
	}
	
	/**
	 * ��ȡ�����б�
	 * @return
	 */
	@RequestMapping(value = "/getFuncList")
	@ResponseBody
	public List<Function> getFuncList(){
		List<Function> function = functionService.findAll();
		return function;
	}
	
	/**
	 * ��ȡ������
	 * @return
	 */
	@RequestMapping(value = "/getFunction")
	@ResponseBody
	public List<TreeNode> getFunction(){
		List<TreeNode> treeNodes = functionService.getFunction();
		return treeNodes;
	}
	
	/**
	 * ��ȡ������
	 * @return
	 */
	@RequestMapping(value = "/getRole")
	@ResponseBody
	public List<TreeNode> getRole(){
		List<TreeNode> treeNodes = roleService.getRole();
		return treeNodes;
	}
}
