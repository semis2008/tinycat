package com.tinycat.dao;

import java.util.List;

import com.tinycat.pojo.Answer;
import com.tinycat.pojo.Question;
 
public interface ChatDao {

	 Question queryQuestionByName(String name);

	 List<Answer> queryAnswerByQuestionId(Long id);
		
	 
	 int insertQuestion(Question ques);
	 
	 int updateQuestionWeight(Long qId);
	 
	 List<Answer> queryAnswerList();
	 
	 List<Answer> queryAnswerByQuestionName(String name);
	 
	 List<Answer> queryAnswerOrderByWeight(int start,int num);
	 
	 List<Question> queryQuestionByHasAnswer(int start,int num,boolean hasAnswer);
	 
	 int queryQuestionCount(boolean hasAnswer);
	 
	 List<Question> queryQuestionByHasAnswerExcept(Long qId,boolean hasAnswer);
	 
}
