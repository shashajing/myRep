package com.shashajing.benison.web;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.opensymphony.xwork2.ActionSupport;
import com.shashajing.benison.entity.User;
import com.shashajing.benison.service.UserService;

@Component("userAction")
public class UserAction extends ActionSupport{
	private static final long serialVersionUID = 3384495531777315088L;

	private List<User> userList;
	
	private User user;
	private String id;
	
	@Autowired
	private UserService userService;
	
	@Override
	public String execute() throws Exception {
		
		Map<String, Object> parameters = Maps.newHashMap();
		if (null != user) {
			parameters.put("loginName", user.getLoginName());
			parameters.put("userName", user.getUserName());
			parameters.put("type", user.getType());
			parameters.put("status", user.getStatus());
		}
		userList = userService.searchUser(parameters);
		return "success";
	}
	
	public String initInput() {
		if (StringUtils.isNotBlank(id)) {
			user = userService.searchUserById(id);
		}
		return "success";
	}
	
	public String addUser() {
		
		userService.addUser(user);
		
		return "success";
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUserList() {
		return userList;
	}
}
