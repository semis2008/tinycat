package com.tinycat.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tinycat.dto.QuestionDTO;
import com.tinycat.service.ChatService;
import com.tinycat.util.JsonUtil;
import com.tinycat.util.ParamUtils;

/**
 * 
 * 问题 相关处理
 * 
 * @author wn
 * @version 1.0.0 QuestionController.java 2014-7-8 下午4:43:15
 */
@Controller
@RequestMapping("/question")
public class QuestionController {
	@Resource
	ChatService chatService;
 
	@RequestMapping(value = "/noanswer")
	private void noAnswer(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Long qId = ParamUtils.getLongParameter(req, "qId", -1);
		QuestionDTO ques = chatService.getRandQuestionNoAnsExcept(qId);
		JsonUtil.outputDTOToJSON(ques, true, resp);
	}
	@RequestMapping(value = "/hasanswer")
	private void hasAnswer(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Long qId = ParamUtils.getLongParameter(req, "qId", -1);
		QuestionDTO ques = chatService.getRandAnswersWithAnsExcept(qId);
		JsonUtil.outputDTOToJSON(ques, true, resp);
	}
	
}
