package com.shashajing.benison.entity;

import java.util.ArrayList;
import java.util.List;



/**
 * 角色.
 * @author yanghanjing
 */
public class Role {
	
	private Long roleId;
	private String roleName;
	private String description;
	
	private List<Module> modules;
	
	private List<String> moduleNames = new ArrayList<String>();
	
	public List<String> getModuleNames() {
		if (null != modules && !modules.isEmpty()) {
			for (Module module : modules) {
				moduleNames.add(module.getModuleName());
			}
		}
		return moduleNames;
	}
	public void setModuleNames(List<String> moduleNames) {
		this.moduleNames = moduleNames;
	}
	public List<Module> getModules() {
		return modules;
	}
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}