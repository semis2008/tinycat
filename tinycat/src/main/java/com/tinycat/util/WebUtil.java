package com.tinycat.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.tinycat.dto.UserDTO;
import com.tinycat.pojo.User;
import com.tinycat.service.UserService;

public class WebUtil {
	
	public static UserDTO getLoginUser(UserService userService) {
		Subject currentUser = SecurityUtils.getSubject();
		User user = userService.getUserByName((String) currentUser.getPrincipal());
		UserDTO dto = UserDTO.init(user);
		return dto;
	}
	
	public static String getLoginUser() {
		Subject currentUser = SecurityUtils.getSubject();
		return (String) currentUser.getPrincipal();
	}
	
	public static String getRandCode(HttpServletRequest req) {
		return (String) req.getSession().getAttribute("randCode");
	}
	
}
