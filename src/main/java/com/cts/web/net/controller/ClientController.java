package com.cts.web.net.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Client
 * @author yujunxi
 * @date 2016/1/28
 */
@Controller
@RequestMapping("/ctp")
public class ClientController {
	
	/**
	 * http:localhost:8080/ctp
	 * Ê×Ò³
	 * @return
	 */
	@RequestMapping
	public String index(){
		return "index";
	}
	
}
