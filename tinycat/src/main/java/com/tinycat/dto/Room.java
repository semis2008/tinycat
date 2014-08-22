package com.tinycat.dto;

import java.util.Date;
import java.util.List;

import com.tinycat.util.RoomType;


public class Room {
	private RoomType type;
	private String name;
	private String password;
	private UserDTO createUser;
	private boolean hasPassword;
	private Date createTime;
	private List<UserDTO> users;
	
	
	 
	public RoomType getType() {
		return type;
	}
	public void setType(RoomType type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserDTO getCreateUser() {
		return createUser;
	}
	public void setCreateUser(UserDTO createUser) {
		this.createUser = createUser;
	}
	public boolean isHasPassword() {
		return hasPassword;
	}
	public void setHasPassword(boolean hasPassword) {
		this.hasPassword = hasPassword;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public List<UserDTO> getUsers() {
		return users;
	}
	public void setUsers(List<UserDTO> users) {
		this.users = users;
	}
	
	
}
