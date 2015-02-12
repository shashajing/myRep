package com.shashajing.benison.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.shashajing.benison.dao.UserDao;
import com.shashajing.benison.entity.User;

@Component
@Transactional
public class AccountService {
	
	@Autowired
	private UserDao userDao;
	
	public List<User> searchUser(Map<String, Object> parameters) {
		return userDao.searchUser(parameters);
	}
	
	public int countUser(Map<String, Object> parameters) {
		return userDao.countUser(parameters);
	}
	
	public User findUserByLoginName(String loginName) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("loginName", loginName);
		List<User> list = userDao.searchUser(parameters);
		return list.isEmpty()?null:list.get(0);
	}
	
	public User findLoginUser(String loginName, String password) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("loginName", loginName);
		parameters.put("password", password);
		List<User> list = userDao.searchUser(parameters);
		return list.isEmpty()?null:list.get(0);
	}
	
	public List<User> searchUserNotBelongRole(Map<String, Object> parameters) {
		return userDao.searchUser(parameters);
	}
	
	public User searchUserById(String id) {
		Map<String, Object> parameters = Maps.newHashMap();
		parameters.put("userId", id);
		List<User> list = userDao.searchUser(parameters);
		return list.isEmpty()?null:list.get(0);
	}

	public int addUser(User user) {
		return userDao.addUser(user);
	}
	
	public int updateUser(User user) {
		return userDao.updateUser(user);
	}
	
	public int deleteUser(List<Integer> ids) {
		return userDao.deleteUser(ids);
	}
}
