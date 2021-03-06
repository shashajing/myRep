package com.shashajing.benison.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.shashajing.benison.common.CommonAction;
import com.shashajing.benison.entity.User;
import com.shashajing.benison.service.UserService;

@Component("userAction")
@Scope("prototype")
public class UserAction extends CommonAction {
	private static final long serialVersionUID = 3384495531777315088L;

	private List<User> userList;
	
	private User searchUser;
	private User editUser;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	CacheManager ehCacheManager;
	
	private static final String EH_CACHE_NAME = "ehCache";
	
	@Override
	public String execute() throws Exception {
		editUser = null;
		setOperateType("search");
		Map<String, Object> parameters = Maps.newHashMap();
		if (null != searchUser) {
			parameters.put("loginName", StringUtils.trimToNull(searchUser.getLoginName()));
			parameters.put("userName", StringUtils.trimToNull(searchUser.getUserName()));
			parameters.put("type", searchUser.getType());
			parameters.put("status", searchUser.getStatus());
		}
		getPage().setTotal(Long.valueOf(userService.countUser(parameters)));
		parameters.put("start", getPage().getStart());//起始下标
		parameters.put("pageNum", getPage().getPageNum());//每页显示数量
		userList = userService.searchUser(parameters);
		return "success";
	}
	
	public String initInput() {
		if (StringUtils.isNotBlank(getId())) {
			editUser = getCacheUser(getId());
			if (null == editUser) {
				editUser = userService.searchUserById(getId());
				setCacheUser(editUser);
			}
			
		}
		return "success";
	}
	
	private void setCacheUser(User user) {
		if (null == user) {
			return;
		}
		if (null == ehCacheManager) {
			return;
		}
		Cache cache = ehCacheManager.getCache(EH_CACHE_NAME);
		if (null == cache || cache.isDisabled()) {
			return;
		}
		cache.put(new Element(user.getUserId(), user));
	}

	private User getCacheUser(String id){
		if (StringUtils.isBlank(id)) {
			return null;
		}
		if (null == ehCacheManager) {
			return null;
		}
		Cache cache = ehCacheManager.getCache(EH_CACHE_NAME);
		if (null == cache || cache.isDisabled()) {
			return null;
		}
		Element element = cache.get(id);
		if (null == element) {
			return null;
		}
		return (User)element.getObjectValue();
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
		if (StringUtils.isNotBlank(getId())) {
			String[] ids = getId().split(",");
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
}
