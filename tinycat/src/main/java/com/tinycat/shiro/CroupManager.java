package com.tinycat.shiro;

import java.util.ArrayList;
import java.util.List;

public class CroupManager {
	
	public static Group getGroupAuthById(int groupId) {
		Group group = new Group();
		List<String> permissions = new ArrayList<String>();
		permissions.add("teach:allow");
		if(groupId==1) {
			permissions.add("admin:yes");
		}
		group.setPermissionList(permissions);
		return group;
	}
}
