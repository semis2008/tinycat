package com.tinycat.pojo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tinycat.search.Searchable;

public class Question implements Searchable{
	private Long id;
	private String name;
	private Long user_id;
	private Date create_time;
	private boolean has_answer;
	private int weight;
	private String all_have;
	
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

	 

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getAll_have() {
		return all_have;
	}

	public void setAll_have(String all_have) {
		this.all_have = all_have;
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

	@Override
	public int compareTo(Searchable o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long id() {
		return id;
	}

	@Override
	public void setId(long id) {

		this.id = id;
	}

	@Override
	public List<String> storeFields() {
		return Arrays.asList("id","all_have","name","user_id","create_time","has_answer");
	}

	@Override
	public List<String> indexFields() {
		return Arrays.asList("name","has_answer");
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
