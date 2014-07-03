package com.tinycat.shiro;

import java.util.ArrayList;
import java.util.List;

public class CroupManager {
	
	public static List<Group> getGroupByUserLevel(String level) {
		List<Group> groups = new ArrayList<Group>();
		
		Group group = new Group();
		List<String> permissions = new ArrayList<String>();
		permissions.add("view:members");
		permissions.add("edit:self");
		group.setPermissionList(permissions);
		
		groups.add(group);
		return groups;
		
	}
}
