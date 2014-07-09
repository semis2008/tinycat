package com.tinycat.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tinycat.pojo.User;

/**
 * 用户业务处理接口
 * @author Kalor
 * @time 2012-12-17
 */
public interface UserService {
	 
	
	/**
	 * 依据用户id查询用户信息
	 * @param id 用户id
	 * @return 用户实体类
	 */
	User getUserByName(String name);
	
	Boolean userRegist(HttpServletRequest req);

	boolean updateUserLoginTime(String email);
	
	/**
	 * 
	  * 获取积分最高的前n个用户
	  *
	  * @autor: wn  2014-7-8 上午10:39:11
	  * @param n
	  * @return
	 */
	List<User> getActiveUsers(int n);
}
