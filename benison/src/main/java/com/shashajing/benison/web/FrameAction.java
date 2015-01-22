package com.shashajing.benison.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.shashajing.benison.entity.Module;
import com.shashajing.benison.service.ModuleService;

@Component("frameAction")
public class FrameAction extends ActionSupport{

	private List<Module> moduleList;
	
	@Autowired
	private ModuleService moduleService;
	
	
	
	
	@Override
	public String execute() throws Exception {
		
		moduleList = moduleService.searchModule(null);
		
		return SUCCESS;
	}





	public void searchUser() {
		
		/*Map<String, Object> parameters = Maps.newHashMap();
		userList = userService.searchUser(parameters);
		for (User user : userList) {
			System.out.println(user.getLoginName());
		}*/
	}
	
	
	
	
	
	public String gotoMain() {
		
		return "main";
	}
	
	
}
