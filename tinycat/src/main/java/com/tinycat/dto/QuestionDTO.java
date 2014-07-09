package com.tinycat.dto;

import java.util.Date;
import java.util.List;

import com.tinycat.pojo.Answer;

public class QuestionDTO {
	
	private Long id;
	private String name;
	private Long user_id;
	private Date create_time;
	private boolean has_answer;
	private int weight;
	private List<Answer> answers;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public boolean isHas_answer() {
		return has_answer;
	}
	public void setHas_answer(boolean has_answer) {
		this.has_answer = has_answer;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	
	
}
