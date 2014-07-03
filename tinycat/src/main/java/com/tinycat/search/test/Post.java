package com.tinycat.search.test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.tinycat.search.Searchable;


/**
 * 测试索引的对象
 * @author Winter Lau
 */
public class Post implements Searchable {

	private long id;
	private String title;
	private String body;
		
	public Post(){}
	public Post(long id, String t, String b){
		this.id = id;
		this.title = t;
		this.body = b;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public long getId() {
		return id;
	}

	public int compareTo(Searchable o) {
		return 0;
	}
	public long id() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void setId(long id) {
		// TODO Auto-generated method stub
		
	}
	public List<String> storeFields() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<String> indexFields() {
		// TODO Auto-generated method stub
		return null;
	}
	public float boost() {
		// TODO Auto-generated method stub
		return 0;
	}
	public Map<String, String> extendStoreDatas() {
		// TODO Auto-generated method stub
		return null;
	}
	public Map<String, String> extendIndexDatas() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<? extends Searchable> ListAfter(long id, int count) {
		// TODO Auto-generated method stub
		return null;
	}

}
