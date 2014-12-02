package com.shashajing.benison.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.shashajing.benison.dao.UserDao;
import com.shashajing.benison.entity.User;

@Component
@Transactional
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public List<User> searchUser(Map<String, Object> parameters) {
		return userDao.searchUser(parameters);
	}

}
