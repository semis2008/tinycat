package com.tinycat.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.lucene.index.IndexNotFoundException;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.springframework.stereotype.Service;

import com.tinycat.dao.ChatDao;
import com.tinycat.pojo.Answer;
import com.tinycat.pojo.Question;
import com.tinycat.search.IndexHolder;
import com.tinycat.search.SearchHelper;
import com.tinycat.search.Searchable;
import com.tinycat.service.ChatService;

@Service("ChatService")
public class ChatServiceImpl implements ChatService {
	
	@Resource
	ChatDao chatDao;
	
	@Override
	public String getAnswer(String question, Long userId) {
		List<Answer> answers = new ArrayList<Answer>();
		//首先精确匹配，查看答案，没有的话，用lucence模糊按关键字查询
		answers = chatDao.queryAnswerByQuestionName(question);
		if (answers.size() > 0) {
			return answers.get((int) Math.random() * answers.size()).getContent();
		}
		// lucence查询问题以及问题的答案
		try {
			IndexHolder holder = IndexHolder.getInstance();
			Query q = SearchHelper.makeQuery("questionContent", question);
			List<Searchable> hits = holder.find(Answer.class, q, null, new Sort(), 1, 100);
			for (Searchable searchable : hits) {
				Answer v = (Answer) searchable;
				answers.add(v);
			}
			if (answers.size() > 0) {
				return answers.get((int) Math.random() * answers.size()).getContent();
			} else if(userId!=-1){//未找到答案。如果是登陆用户提问的
				//先看 是否有这个问题，没有的话新建，有的话计数+1
				Question ques = chatDao.queryQuestionByName(question);
				if(ques!=null) {
					chatDao.updateWeight(ques.getId());
				}else{
					ques = new Question();
					ques.setName(question);
					ques.setUser_id(userId);
					chatDao.insertQuestion(ques);
				}
			}
		}catch (IOException e) {
			e.printStackTrace();
			return Answer.getRandomNoAnswerMsg();
		}
		return Answer.getRandomNoAnswerMsg();
	}

	public static void main(String args[]) {
		ChatServiceImpl impl = new ChatServiceImpl();
		String ans = impl.getAnswer("asdasd", null);
		System.out.print("tinyCat说：" + ans);
	}

	@Override
	public List<Answer> getAnswerList() {
		return chatDao.queryAnswerList();
	}
}
