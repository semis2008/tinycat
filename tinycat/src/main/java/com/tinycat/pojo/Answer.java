package com.tinycat.pojo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tinycat.search.Searchable;

public class Answer implements Searchable {
	public static final List<String> NO_ANSWER_MSG = Arrays.asList("我不清楚哦...", "不知道你在说什么啊...", "额，对不起，我在走神..", "虽然不知道你在说什么，但是好像很厉害的样子...", "或许你可以教我怎么回答这个问题...", "对不起，我还不知道这个呢...", "我在吃饭，没空理你！", "额...咳咳...今天天气真不错");
	
	private Long id;
	private Long question_id;
	private String question;
	private Long user_id;
	private String content;
	private Date create_time;
	private int weight;
	
	private String all_have;

	public static String getRandomNoAnswerMsg() {
		int randIndex = 0;
		randIndex = (int) (Math.random()*NO_ANSWER_MSG.size());
		return NO_ANSWER_MSG.get(randIndex);
	} 
	
	public Long getId() {
		return id;
	}

	public Long getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(Long question_id) {
		this.question_id = question_id;
	}

	 
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getAll_have() {
		return all_have;
	}

	public void setAll_have(String all_have) {
		this.all_have = all_have;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public int compareTo(Searchable o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Long id() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public List<String> storeFields() {
		return Arrays.asList("id","all_have", "question_id", "user_id", "question_content", "content", "create_time", "weight");
	}

	@Override
	public List<String> indexFields() {
		return Arrays.asList("content", "question_content");
	}

	@Override
	public float boost() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Map<String, String> extendStoreDatas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> extendIndexDatas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends Searchable> ListAfter(long id, int count) {
		// TODO Auto-generated method stub
		return null;
	}
}
