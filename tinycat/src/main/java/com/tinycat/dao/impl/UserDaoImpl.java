package com.tinycat.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.tinycat.dao.UserDao;
import com.tinycat.dao.template.DbUtilsTemplate;
import com.tinycat.pojo.User;

/**
 * 用户数据操作接口实现类
 * 
 * @author Kalor
 * @time 2012-12-17
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {
	@Resource
	private DbUtilsTemplate dbUtilsTemplate;
	
	@Override
	public User queryUserByEmail(String email) {
		String sql = "select * from user where email = ?";
		return dbUtilsTemplate.findFirst(User.class, sql, email);
	}
	
	@Override
	public User queryUserByName(String name) {
		String sql = "select * from user where name = ?";
		return dbUtilsTemplate.findFirst(User.class, sql, name);
	}

	@Override
	public int insertUser(User user) {
		String sql = "insert into user (email,name,password,photo,reg_time,group_id,status) values (?,?,?,?,now(),?,?)";
		Object[] param = { user.getEmail(), user.getName(), user.getPassword(), user.getPhoto(),user.getGroup_id(),user.getStatus()};
		return dbUtilsTemplate.update(sql, param);
	}

	@Override
	public int updateUserLoginTime(String email) {
		String sql = "update user set login_time = now() where email = ?";
		return dbUtilsTemplate.update(sql, email);
	}

	@Override
	public List<User> queryUserOrderByCreateTime(int start, int num) {
		String sql = "select * from user order by reg_time desc limit ?,?";
		Object[] params = { start,num};
		return dbUtilsTemplate.find(User.class, sql, params);
	}

	@Override
	public int updateUserPhoto(Long userId, String photo) {
		String sql = "update user set photo = ? where id = ?";
		Object[] params = { photo,userId};
		return dbUtilsTemplate.update(sql, params);
	}

	@Override
	public int updateUserName(Long userId, String name) {
		String sql = "update user set name = ? where id = ?";
		Object[] params = { name,userId};
		return dbUtilsTemplate.update(sql, params);
	}

	@Override
	public User queryUserById(Long userId) {
		String sql = "select * from user where id = ?";
		return dbUtilsTemplate.findFirst(User.class, sql, userId);
	}

}
