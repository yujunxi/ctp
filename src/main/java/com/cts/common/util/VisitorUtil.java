package com.cts.common.util;

import javax.servlet.http.HttpSession;

public class VisitorUtil {
	
	public static final String VISITOR_KEY = "visitor";
	
	public static void setVisitor(HttpSession session){
		session.setAttribute(VISITOR_KEY, "a");
	}
	
}
