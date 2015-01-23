package com.shashajing.benison.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.shashajing.benison.common.CommonAction;
import com.shashajing.benison.entity.Role;
import com.shashajing.benison.service.RoleService;

@Component("roleAction")
@Scope("prototype")
public class RoleAction extends CommonAction{

	private static final long serialVersionUID = 7515358606387010997L;

	private List<Role> roleList;
	private Role searchRole;
	private Role editRole;
	
	@Autowired
	private RoleService roleService;
	
	@Override
	public String execute() throws Exception {
		editRole = null;
		setOperateType("search");
		Map<String, Object> parameters = Maps.newHashMap();
		if (null != searchRole) {
			parameters.put("roleName", StringUtils.trimToNull(searchRole.getRoleName()));
		}
		roleList = roleService.searchRole(parameters);
		return "success";
	}
	
	public String initInput() {
		if (StringUtils.isNotBlank(getId())) {
			editRole = roleService.searchRoleById(getId());
		}
		return "success";
	}
	
	public String editRole() {
		if (editRole != null) {
			if (editRole.getRoleId() != null) {
				roleService.updateRole(editRole);
			} else {
				roleService.addRole(editRole);
			}
		}
		return "toRoleList";
	}
	
	public String deleteRole() {
		if (StringUtils.isNotBlank(getId())) {
			String[] ids = getId().split(",");
			List<Long> idList = new ArrayList<Long>(ids.length);
			for (String uId : ids) {
				idList.add(Long.valueOf(uId));
			}
			if (!idList.isEmpty()) {
				roleService.deleteRole(idList);
			}
		}
		return "toRoleList";
	}

	public Role getSearchRole() {
		return searchRole;
	}

	public void setSearchRole(Role searchRole) {
		this.searchRole = searchRole;
	}

	public Role getEditRole() {
		return editRole;
	}

	public void setEditRole(Role editRole) {
		this.editRole = editRole;
	}

	public List<Role> getRoleList() {
		return roleList;
	}
}
