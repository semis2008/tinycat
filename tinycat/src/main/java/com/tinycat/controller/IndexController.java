package com.tinycat.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tinycat.pojo.User;
import com.tinycat.service.UserService;
import com.tinycat.util.WebUtil;
 

/**
 * 系统响应处理类
 * 
 * @author Kalor
 * @time 2012-12-14
 */
@Controller
public class IndexController {

	@Resource
	UserService userService;
	
	/**
	 * 显示主页
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/index")
	private ModelAndView showIndex(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		 
		User user = WebUtil.getLoginUser(userService);
		req.setAttribute("loginUser", user);
		 
		return new ModelAndView("index");
	}

	/**
	 * 登陆页
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login")
	private ModelAndView showLogin(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		 
		User user = WebUtil.getLoginUser(userService);
		req.setAttribute("loginUser", user);
		return new ModelAndView("login");
	}

	/**
	 * 注册页
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/regist")
	private ModelAndView showRegist(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		 
		User user = WebUtil.getLoginUser(userService);
		req.setAttribute("loginUser", user);
		return new ModelAndView("regist");
	}
	
	/**
	 * 未授权错误页
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/unauthorized")
	private ModelAndView showUnauthorized(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		User user = WebUtil.getLoginUser(userService);
		req.setAttribute("loginUser", user);
		return new ModelAndView("unauthorized");
	}
}
