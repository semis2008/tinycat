package com.tinycat.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.tinycat.dao.UserDao;
import com.tinycat.pojo.User;
import com.tinycat.service.UserService;


/**
 * 用户业务处理接口实现类
 * 
 * @author Kalor
 * @time 2012-12-17
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;

	public User getUserByName(String name) {
		return userDao.queryUserByEmail(name);
	}
}
