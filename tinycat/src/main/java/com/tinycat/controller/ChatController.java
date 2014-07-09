package com.tinycat.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tinycat.pojo.Answer;
import com.tinycat.search.IndexHolder;
import com.tinycat.service.ChatService;
import com.tinycat.util.JsonUtil;
import com.tinycat.util.ParamUtils;
import com.tinycat.util.StringUtil;

/**
 * chat相关处理
 * 
 * @author wn
 * @version 1.0.0 ChatController.java 2014-7-4 下午3:47:53
 */
@Controller
@RequestMapping("/chat")
public class ChatController {
	@Resource
	ChatService chatService;
 
	@RequestMapping(value = "/answer")
	private void answer(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String msg = ParamUtils.getParameter(req, "msg");
		Long userId = ParamUtils.getLongParameter(req, "userId", -1);
		msg = StringUtil.escapeHtmlTags(msg.trim());
		
		String answer = chatService.getAnswer(msg, userId);
		JsonUtil.outputDTOToJSON(answer, true, resp);
	}

	@RequestMapping(value = "/test")
	private void test(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		JsonUtil.outputDTOToJSON(chatService.getAnswerList(), true, resp);
	}
	
	/**
	 * 
	  * 建索引
	  *
	  * @autor: wn  2014-7-4 下午6:10:08
	  * @param req
	  * @param resp
	  * @throws Exception
	 */
	@RequestMapping(value = "/buildindex")
	private void buildIndex(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		IndexHolder holder = IndexHolder.getInstance();
		//获取所有的answer
		List<Answer> answerList = chatService.getAnswerList();
		holder.optimize(Answer.class);
		holder.add(answerList);
//		
		JsonUtil.outputDTOToJSON("success", true, resp);
	}
}
