package com.tinycat.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.tinycat.dao.ChatDao;
import com.tinycat.dao.template.DbUtilsTemplate;
import com.tinycat.pojo.Answer;
import com.tinycat.pojo.Question;
 
@Repository("chatDao")
public class ChatDaoImpl implements ChatDao {
	@Resource
	private DbUtilsTemplate dbUtilsTemplate;

	@Override
	public Question queryQuestionByName(String name) {
		String sql = "select * from question where name=?";
		return dbUtilsTemplate.findFirst(Question.class,sql,name);
	}

	@Override
	public int insertQuestion(Question ques) {
		String sql = "insert into question(name,user_id,creat_time,has_answer,weight) values(?,?,now(),?,1)";
		Object[] param = { ques.getName(),ques.getUser_id(),false};
		return dbUtilsTemplate.update(sql, param);
	}

	@Override
	public int updateWeight(Long qId) {
		String sql = "update question set weight=weight+1 where id=?";
		return dbUtilsTemplate.update(sql, qId);
	}

	@Override
	public List<Answer> queryAnswerList() {
		String sql = "select * from answer where 1=1";
		return dbUtilsTemplate.find(Answer.class,sql);
	}
	
	
	
	 
	
}
