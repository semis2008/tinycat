package com.tinycat.dao.impl;

import java.util.List;
import java.util.Map;

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
		String sql = "insert into question(name,user_id,create_time,has_answer,weight) values(?,?,now(),?,1)";
		Object[] param = { ques.getName(),ques.getUser_id(),false};
		return dbUtilsTemplate.update(sql, param);
	}

	@Override
	public int updateQuestionWeight(Long qId) {
		String sql = "update question set weight=weight+1 where id=?";
		return dbUtilsTemplate.update(sql, qId);
	}

	@Override
	public List<Answer> queryAnswerList() {
		String sql = "select * from answer where 1=1";
		return dbUtilsTemplate.find(Answer.class,sql);
	}

	@Override
	public List<Answer> queryAnswerByQuestionName(String name) {
		String sql = "select * from answer where question = ?";
		return dbUtilsTemplate.find(Answer.class,sql,name);
	}

	@Override
	public List<Answer> queryAnswerOrderByWeight(int start, int num) {
		String sql = "select * from answer order by weight desc limit ?,?";
		Object[] params = { start,num};
		return dbUtilsTemplate.find(Answer.class, sql, params);
	}

	@Override
	public List<Question> queryQuestionByHasAnswer(int start, int num,boolean hasAnswer) {
		String sql = "select * from question where has_answer=? order by weight desc limit ?,?";
		Object[] params = { hasAnswer,start,num};
		return dbUtilsTemplate.find(Question.class, sql, params);
	}

	@Override
	public int queryQuestionCount(boolean hasAnswer) {
		String sql = "select count(*) totalNum from question where has_answer = ?";
		Map<String,Object> result = dbUtilsTemplate.findFirst(sql, hasAnswer);
		return Integer.parseInt((Long)result.get("totalNum")+"");
	}

	@Override
	public List<Question> queryQuestionByHasAnswerExcept(Long qId, boolean hasAnswer) {
		String sql = "select * from question where has_answer=? and id<>? order by weight desc";
		Object[] params = { hasAnswer,qId};
		return dbUtilsTemplate.find(Question.class, sql, params);
	}

	@Override
	public List<Answer> queryAnswerByQuestionId(Long id) {
		String sql = "select * from answer where id=?";
		return dbUtilsTemplate.find(Answer.class,sql,id);
	}
	
	
	
	 
	
}
