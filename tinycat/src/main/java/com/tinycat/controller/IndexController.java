package com.tinycat.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tinycat.dto.QuestionDTO;
import com.tinycat.dto.UserDTO;
import com.tinycat.pojo.Answer;
import com.tinycat.pojo.Question;
import com.tinycat.pojo.User;
import com.tinycat.service.ChatService;
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
	@Resource
	ChatService chatService;

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
		setLoginUserDTO(req);
		// 获取活跃用户，按积分
//		List<User> activeUsers = userService.getActiveUsers(10);
//		// 获取热度最大的问答
//		List<Answer> hotAnswers = chatService.getAnswerOrderByWeight(5);
//		req.setAttribute("activeUsers", activeUsers);
//		req.setAttribute("hotAnswers", hotAnswers);
		return new ModelAndView("game");
	}
	
	/**
	 * 显示主页
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/game")
	private ModelAndView showGame(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		setLoginUserDTO(req);
		 
		
		return new ModelAndView("game");
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
		setLoginUserDTO(req);
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
		setLoginUserDTO(req);
		// 获取活跃用户，按积分
		List<User> activeUsers = userService.getActiveUsers(8);
		req.setAttribute("activeUsers", activeUsers);
		return new ModelAndView("regist");
	}
	
	/**
	 * teach页面（显示没有人回答的问题，以及）
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/teach")
	private ModelAndView showTeach(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		setLoginUserDTO(req);
		//随机获取一个没有人回答的问题
		QuestionDTO quesNoAns = chatService.getRandQuestionNoAnsExcept(-1l);
		//随机获取有人回答的问题及答案
		QuestionDTO quesHasAns = chatService.getRandAnswersWithAnsExcept(-1l);
		
		req.setAttribute("quesNoAns", quesNoAns);
		req.setAttribute("quesHasAns", quesHasAns);
		return new ModelAndView("teach");
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
		setLoginUserDTO(req);
		return new ModelAndView("unauthorized");
	}

	private void setLoginUserDTO(HttpServletRequest req) {
		UserDTO user = WebUtil.getLoginUser(userService);
		req.setAttribute("loginUser", user);
	}
}
