package com.cts.web.net.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Client
 * @author yujunxi
 * @date 2016/1/28
 */
@Controller
@RequestMapping("/ctp")
public class ClientController {
	
	/**
	 * ��̨����ϵͳ��¼
	 * @return
	 */
	@RequestMapping(value = "/sys", method = RequestMethod.GET)
	public String sysClient(){
		return "sys";
	}
	
	/**
	 * �ͻ���
	 * @return
	 */
	@RequestMapping(value = "/home" , method = RequestMethod.GET)
	public String homeClient(){
		return "index";
	}
	
}
