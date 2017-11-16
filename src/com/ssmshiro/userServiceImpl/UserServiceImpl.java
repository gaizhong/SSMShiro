package com.ssmshiro.userServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssmshiro.dao.UserDao;
import com.ssmshiro.userService.UserService;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	@Override
	public List queryUser(Map map) {
		return userDao.queryUser(map);
	}

}
