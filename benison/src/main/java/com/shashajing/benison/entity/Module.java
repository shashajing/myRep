package com.shashajing.benison.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * 功能菜单
 * @author yanghanjing
 */
public class Module {
	private Long moduleId;
	private Long parentId;
	private Integer type;//菜单类型1：菜单，2：按钮
	private String moduleName;
	private String moduleUrl;
	private String description;
	private String editor;
	private Date editTime;
	private String parentModule;
	private List<Module> sonModules;
	private List<Role> roles;
	
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public Long getModuleId() {
		return moduleId;
	}
	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getModuleUrl() {
		return moduleUrl;
	}
	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public Date getEditTime() {
		return editTime;
	}
	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}
	public String getParentModule() {
		return parentModule;
	}
	public void setParentModule(String parentModule) {
		this.parentModule = parentModule;
	}
	public List<Module> getSonModules() {
		if (null == sonModules) {
			sonModules = new ArrayList<Module>();
		}
		return sonModules;
	}
	public void setSonModules(List<Module> sonModules) {
		this.sonModules = sonModules;
	}
}