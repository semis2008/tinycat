package com.tinycat.service;

import java.util.List;

import com.tinycat.pojo.Answer;

/**
 * 
 * Chat
 * 
 * @author wn
 * @version 1.0.0 ChatService.java 2014-7-4 下午4:03:47
 */
public interface ChatService {
	 
	/**
	 * 
	  * 获取答案
	  *
	  * @autor: wn  2014-7-4 下午4:04:48
	  * @param question
	  * @param userId
	  * @return
	 */
	String getAnswer(String question,Long userId);
	
	List<Answer> getAnswerList();
}
