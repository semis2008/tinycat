package com.tinycat.shiro;

import java.util.List;

public class Group {
	private List<String> permissionList;
	private String userLevel;

	 
	public List<String> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<String> permissionList) {
		this.permissionList = permissionList;
	}

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}
}
