package com.tinycat.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.tinycat.pojo.User;
import com.tinycat.service.UserService;

public class WebUtil {
	public static User getLoginUser(UserService userService) {
		Subject currentUser = SecurityUtils.getSubject();
		User user = userService.getUserByName((String) currentUser.getPrincipal());
		return user;
		
	}
	
	public static String getRandCode(HttpServletRequest req) {
		return (String) req.getSession().getAttribute("randCode");
	}
}
