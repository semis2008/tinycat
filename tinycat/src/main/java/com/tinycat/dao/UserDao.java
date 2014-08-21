package com.tinycat.dao;

import java.util.List;

import com.tinycat.pojo.User;

/**
 * 用户数据处理接口
 * 
 * @author Kalor
 * @time 2012-12-17
 */
public interface UserDao {

	/**
	 * 依据注册邮箱查询用户
	 * 
	 * @param email
	 *            注册邮箱
	 * @return 用户实体类
	 */
	User queryUserByEmail(String email);
	
	User queryUserByName(String name);
	
	int insertUser(User user);
	
	int updateUserLoginTime(String email);
	
	int updateUserName(Long userId,String name);
	
	int updateUserPhoto(Long userId,String photo);
	
	List<User> queryUserOrderByCreateTime(int start,int num);
	
}
