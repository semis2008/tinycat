package com.tinycat.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.tinycat.dao.UserDao;
import com.tinycat.pojo.User;
import com.tinycat.service.UserService;
import com.tinycat.util.StringUtil;
import com.tinycat.util.WebUtil;


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

	@Override
	public boolean userRegist(HttpServletRequest req) {
		String email = (String) req.getParameter("email");
		String name = (String) req.getParameter("name");
		String password = (String) req.getParameter("password");
		
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(StringUtil.passEncrypt(password));
		Integer randomPhoto = (int) (Math.random()*User.PHOTO_SCOPE+1);
		user.setPhoto(randomPhoto+"");
		user.setReg_time(new Date());
		user.setGroup_id(2);
		user.setStatus(User.STATUS_NORMAL);
		
		return userDao.insertUser(user)>0?true:false;
	}

	@Override
	public boolean updateUserLoginTime(String email) {
		return userDao.updateUserLoginTime(email)>0?true:false;
	}

	@Override
	public List<User> getActiveUsers(int n) {
		return userDao.queryUserOrderByCreateTime(0, n);
	}

	@Override
	public boolean hasNameAlready(String name) {
		User u = userDao.queryUserByName(name);
		if(u!=null) 
			return true;
		return false;
	}
}
