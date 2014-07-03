package com.tinycat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tinycat.pojo.User;
import com.tinycat.service.UserService;
import com.tinycat.util.DateUtil;
import com.tinycat.util.JsonUtil;
import com.tinycat.util.StringUtil;
import com.tinycat.util.WebUtil;

/**
 * 用户相关处理类
 * 
 * @author Kalor
 * @time 2012-12-14
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	UserService userService;
	
	/**
	 * 登陆
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login")
	private void login(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String email = (String) req.getParameter("email");
		String password = (String) req.getParameter("password");
		// 1. 接受提交的当事人和证书：
		AuthenticationToken token = new UsernamePasswordToken(email, StringUtil.passEncrypt(password));
		// 2. 获取当前Subject：
		Subject currentUser = SecurityUtils.getSubject();
		// 3. 登录：
		try {
			currentUser.login(token);
		} catch (IncorrectCredentialsException ice) {
			JsonUtil.outputDTOToJSON("密码错误", false, resp);
			return;
		} catch (LockedAccountException lae) {
			JsonUtil.outputDTOToJSON("账户被锁定", false, resp);
			return;
		} catch (AuthenticationException ae) {
			JsonUtil.outputDTOToJSON("授权失败", false, resp);
			return;
		}
		//更新用户登录时间
		userService.updateUserLoginTime(email);
		
		JsonUtil.outputDTOToJSON(null, true, resp);
	}

	/**
	 * 注册
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/regist")
	private void regist(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String randCode = (String) req.getParameter("randCode");
		if(!randCode.equals(WebUtil.getRandCode(req))) {
			JsonUtil.outputDTOToJSON("验证码错误", false, resp);
			return;
		}
		boolean result = userService.userRegist(req);
		if(result)
			JsonUtil.outputDTOToJSON("注册成功", result, resp);
		else
			JsonUtil.outputDTOToJSON("注册失败", result, resp);
	}

}
