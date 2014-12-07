package com.shashajing.benison.web;

import java.util.List;
import java.util.Map;

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
	
	@Autowired
	private UserService userService;
	
	@Override
	public String execute() throws Exception {
		
		Map<String, Object> parameters = Maps.newHashMap();
		userList = userService.searchUser(parameters);
		System.out.println(userList.size());
		
		return "success";
	}




	
	
	
	
	public List<User> getUserList() {
		return userList;
	}
}
