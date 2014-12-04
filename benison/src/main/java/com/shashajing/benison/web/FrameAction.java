package com.shashajing.benison.web;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.opensymphony.xwork2.ActionSupport;
import com.shashajing.benison.entity.User;
import com.shashajing.benison.service.UserService;

@Component("frameAction")
public class FrameAction extends ActionSupport{

	private List<User> userList;
	
	@Autowired
	private UserService userService;
	
	@PostConstruct
	public void searchUser() {
		
		Map<String, Object> parameters = Maps.newHashMap();
		userList = userService.searchUser(parameters);
		for (User user : userList) {
			System.out.println(user.getLoginName());
		}
	}
	
	
	public String gotoMain() {
		
		return "main";
	}
	
	public List<User> getUserList() {
		return userList;
	}
}
