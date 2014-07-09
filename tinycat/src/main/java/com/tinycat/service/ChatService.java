package com.tinycat.service;

import java.util.List;

import com.tinycat.dto.QuestionDTO;
import com.tinycat.pojo.Answer;
import com.tinycat.pojo.Question;

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
	
	/**
	 * 
	  * 获取热度最大的问答
	  *
	  * @autor: wn  2014-7-8 上午10:46:06
	  * @param n
	  * @return
	 */
	List<Answer> getAnswerOrderByWeight(int n);
	
	List<Question> getQuestionByHasAnswer(int start,int num,boolean hasAnswer);
	
	/**
	 * 
	  * 获取随机一个问题
	  *
	  * @autor: wn  2014-7-8 下午3:44:22
	  * @param qId 除了这个问题之外
	  * @return
	 */
	QuestionDTO getRandQuestionNoAnsExcept(Long qId);
	
	/**
	 * 
	  * 获取除了指定问题外的随机问题及答案
	  *
	  * @autor: wn  2014-7-8 下午4:52:03
	  * @param qId
	  * @return
	 */
	QuestionDTO getRandAnswersWithAnsExcept(Long qId);
}
