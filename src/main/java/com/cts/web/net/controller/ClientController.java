package com.cts.web.net.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cts.web.sys.model.Function;
import com.cts.web.sys.model.RoleFunction;
import com.cts.web.sys.model.SysUser;
import com.cts.web.sys.model.SysUserRole;
import com.cts.web.sys.service.FunctionService;
import com.cts.web.sys.service.RoleFunctionService;
import com.cts.web.sys.service.SysUserRoleService;

/**
 * Client
 * 
 * @author yujunxi
 * @date 2016/1/28
 */
@Controller
@RequestMapping("/ctp")
public class ClientController {

	@Autowired
	private SysUserRoleService sysUserRoleService;

	@Autowired
	private RoleFunctionService roleFunctionService;

	@Autowired
	private FunctionService funcService;

	/**
	 * 后台管理系统登录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sys", method = RequestMethod.GET)
	public ModelAndView sysClient(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		HashSet array = new HashSet();
		List<String> funcList = new ArrayList<String>();
		try {
			List<SysUser> userList = (List<SysUser>) session
					.getAttribute("admin");
			mav.setViewName("sys");
			SysUser user = userList.get(0);
			List<SysUserRole> sur = sysUserRoleService.findById(user.getId());
			for (SysUserRole s : sur) {
				List<RoleFunction> func = roleFunctionService.findByRoleCode(s
						.getId().getRolecode());
				// [1,2,3] func.toarray()
				for (RoleFunction fun : func) {
					array.add(fun.getId().getFuncId());
				}
			}
			Iterator it = array.iterator();
			while (it.hasNext()) {
				String o = (String) it.next();
				funcList.add(o);
			}
			mav.addObject("funcList", funcList);
		} catch (Exception e) {
			// e.printStackTrace();
			mav.setViewName("redirect:/ctp/system/login");
		}

		return mav;
	}

	/**
	 * 客户端
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String homeClient() {
		return "index";
	}

}
