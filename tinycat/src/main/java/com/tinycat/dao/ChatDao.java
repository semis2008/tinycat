package com.tinycat.dao;

import java.util.List;

import com.tinycat.pojo.Answer;
import com.tinycat.pojo.Question;
 
public interface ChatDao {

	 Question queryQuestionByName(String name);
	
	 int insertQuestion(Question ques);
	 
	 int updateWeight(Long qId);
	 
	 List<Answer> queryAnswerList();
}
