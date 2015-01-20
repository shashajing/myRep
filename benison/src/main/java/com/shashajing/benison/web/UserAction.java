package com.shashajing.benison.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.opensymphony.xwork2.ActionSupport;
import com.shashajing.benison.common.Page;
import com.shashajing.benison.entity.User;
import com.shashajing.benison.service.UserService;

@Component("userAction")
public class UserAction extends ActionSupport{
	private static final long serialVersionUID = 3384495531777315088L;

	private List<User> userList;
	
	private User searchUser;
	private User editUser;
	private String id;
	private String operateType;//search,add,update,edit,delete,view
	private Page page = new Page();
	
	@Autowired
	private UserService userService;
	
	@Override
	public String execute() throws Exception {
		editUser = null;
		operateType = "search";
		Map<String, Object> parameters = Maps.newHashMap();
		if (null != searchUser) {
			parameters.put("loginName", StringUtils.trimToNull(searchUser.getLoginName()));
			parameters.put("userName", StringUtils.trimToNull(searchUser.getUserName()));
			parameters.put("type", searchUser.getType());
			parameters.put("status", searchUser.getStatus());
		}
		page.setTotal(Long.valueOf(userService.countUser(parameters)));
		parameters.put("start", page.getStartNum());
		parameters.put("pageNum", page.getPageNum());
		userList = userService.searchUser(parameters);
		return "success";
	}
	
	public String initInput() {
		if (StringUtils.isNotBlank(id)) {
			editUser = userService.searchUserById(id);
		}
		return "success";
	}
	
	public String editUser() {
		if (editUser != null) {
			if (editUser.getUserId() != null) {
				userService.updateUser(editUser);
			} else {
				userService.addUser(editUser);
			}
		}
		return "toUserList";
	}
	
	public String deleteUser() {
		if (StringUtils.isNotBlank(id)) {
			String[] ids = id.split(",");
			List<Integer> idList = new ArrayList<Integer>(ids.length);
			for (String uId : ids) {
				idList.add(Integer.valueOf(uId));
			}
			if (!idList.isEmpty()) {
				userService.deleteUser(idList);
			}
		}
		return "toUserList";
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getSearchUser() {
		return searchUser;
	}

	public void setSearchUser(User searchUser) {
		this.searchUser = searchUser;
	}

	public User getEditUser() {
		return editUser;
	}

	public void setEditUser(User editUser) {
		this.editUser = editUser;
	}

	public List<User> getUserList() {
		return userList;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
}
