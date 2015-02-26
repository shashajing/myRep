package com.shashajing.benison.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.shashajing.benison.common.AjaxDto;
import com.shashajing.benison.common.CommonAction;
import com.shashajing.benison.entity.Module;
import com.shashajing.benison.entity.ModuleRole;
import com.shashajing.benison.entity.Role;
import com.shashajing.benison.entity.User;
import com.shashajing.benison.entity.UserRole;
import com.shashajing.benison.service.ModuleService;
import com.shashajing.benison.service.RoleService;
import com.shashajing.benison.service.UserService;
import com.shashajing.benison.utils.JsonUtils;

@Component("roleAction")
@Scope("prototype")
public class RoleAction extends CommonAction {

	private static final long serialVersionUID = 7515358606387010997L;
	
	private List<Role> roleList;
	private Role searchRole;
	private Role editRole;
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	@Autowired
	private ModuleService moduleService;
	
	@Override
	public String execute() throws Exception {
		editRole = null;
		setOperateType("search");
		Map<String, Object> parameters = Maps.newHashMap();
		if (null != searchRole) {
			parameters.put("roleName", StringUtils.trimToNull(searchRole.getRoleName()));
		}
		roleList = roleService.searchRole(parameters);
		return SUCCESS;
	}
	
	public String initInput() {
		
		if (StringUtils.isNotBlank(getId())) {
			editRole = roleService.searchRoleById(getId());
		}
		return SUCCESS;
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
	
	public String userRoleSearch() {
		if (StringUtils.isNotBlank(getId())) {
			Map<String, Object> parameters = Maps.newHashMap();
			parameters.put("roleId", getId());
			List<UserRole> userRoles = roleService.userRoleSearch(parameters);
			AjaxDto<UserRole> dto = new AjaxDto<UserRole>();
			dto.setDraw(1);
			dto.setRecordsTotal(userRoles.size());
			dto.setRecordsFiltered(userRoles.size());
			dto.setData(userRoles);
			ajaxDataList(new JsonUtils().toJson(dto));
		}
		return null;
	}
	
	public String moduleRoleSearch() {
		if (StringUtils.isNotBlank(getId())) {
			Map<String, Object> parameters = Maps.newHashMap();
			parameters.put("roleId", getId());
			List<ModuleRole> moduleRoles = roleService.moduleRoleSearch(parameters);
			AjaxDto<ModuleRole> dto = new AjaxDto<ModuleRole>();
			dto.setDraw(1);
			dto.setRecordsTotal(moduleRoles.size());
			dto.setRecordsFiltered(moduleRoles.size());
			dto.setData(moduleRoles);
			ajaxDataList(new JsonUtils().toJson(dto));
		}
		return null;
	}

	public String deleteUserRole(){
		if (StringUtils.isNotBlank(getId())) {
			Map<String, Object> parameters = Maps.newHashMap();
			parameters.put("id", getId());
			try {
				roleService.deleteUserRole(parameters);
				ajaxSuccess("删除成功");
			} catch (Exception e) {
				ajaxError("删除失败："+e.getMessage());
			}
		}
		return null;
	}
	
	public String deleteModuleRole(){
		if (StringUtils.isNotBlank(getId())) {
			Map<String, Object> parameters = Maps.newHashMap();
			parameters.put("id", getId());
			try {
				roleService.deleteModuleRole(parameters);
				ajaxSuccess("删除成功");
			} catch (Exception e) {
				ajaxError("删除失败："+e.getMessage());
			}
		}
		return null;
	}
	
	public String userSearch() {
		if (StringUtils.isNotBlank(getId())) {
			Map<String, Object> parameters = Maps.newHashMap();
			parameters.put("roleId", getId());
			String pageFrom = getHttpServletRequest().getParameter("iDisplayStart");
			String pageShowNum = getHttpServletRequest().getParameter("iDisplayLength");
			String sEcho = getHttpServletRequest().getParameter("sEcho");
			if (StringUtils.isNotBlank(pageFrom) && StringUtils.isNotBlank(pageShowNum) && StringUtils.isNotBlank(sEcho)) {
				parameters.put("start", Integer.valueOf(pageFrom));
				parameters.put("pageNum", Integer.valueOf(pageShowNum));
			}
			List<User> users = userService.searchUserNotBelongRole(parameters);
			int count = userService.countUserNotBelongRole(parameters);
			AjaxDto<User> dto = new AjaxDto<User>();
			dto.setsEcho(Integer.valueOf(sEcho));//按照原样返回给页面
			dto.setRecordsTotal(count);
			dto.setRecordsFiltered(count);
			dto.setData(users);
			ajaxDataList(new JsonUtils().toJson(dto));
		}
		return null;
	}
	
	public String moduleSearch() {
		if (StringUtils.isNotBlank(getId())) {
			Map<String, Object> parameters = Maps.newHashMap();
			parameters.put("roleId", getId());
			String pageFrom = getHttpServletRequest().getParameter("iDisplayStart");
			String pageShowNum = getHttpServletRequest().getParameter("iDisplayLength");
			String sEcho = getHttpServletRequest().getParameter("sEcho");
			if (StringUtils.isNotBlank(pageFrom) && StringUtils.isNotBlank(pageShowNum) && StringUtils.isNotBlank(sEcho)) {
				parameters.put("start", Integer.valueOf(pageFrom));
				parameters.put("pageNum", Integer.valueOf(pageShowNum));
			}
			List<Module> modules = moduleService.searchModuleNotBelongRole(parameters);
			int count = moduleService.countModuleNotBelongRole(parameters);
			AjaxDto<Module> dto = new AjaxDto<Module>();
			dto.setsEcho(Integer.valueOf(sEcho));//按照原样返回给页面
			dto.setRecordsTotal(count);
			dto.setRecordsFiltered(count);
			dto.setData(modules);
			ajaxDataList(new JsonUtils().toJson(dto));
		}
		return null;
	}
	
	public String addUserRole() {
		if (StringUtils.isNotBlank(getId())) {
			
			String[] userIds = getId().split(",");
			String roleId = getHttpServletRequest().getParameter("roleId");
			if (userIds.length > 0 && StringUtils.isNotBlank(roleId)) {
				try {
					List<UserRole> userRoles = new ArrayList<UserRole>(userIds.length);
					for (String userId : userIds) {
						UserRole userRole = new UserRole();
						userRole.setUserId(Long.valueOf(userId));
						userRole.setRoleId(Long.valueOf(roleId));
						userRoles.add(userRole);
					}
					Map<String, Object> parameters = new HashMap<String, Object>();
					parameters.put("list", userRoles);
					roleService.addUserRole(parameters);
					ajaxSuccess("添加成功！");
				} catch (Exception e) {
					ajaxError("添加失败:"+e.getMessage());
				}
			} else {
				ajaxError("提交的参数缺失！");
			}
		} else {
			ajaxError("提交的参数缺失！");
		}
		return null;
	}
	
	public String addModuleRole() {
		if (StringUtils.isNotBlank(getId())) {
			
			String[] moduleIds = getId().split(",");
			String roleId = getHttpServletRequest().getParameter("roleId");
			if (moduleIds.length > 0 && StringUtils.isNotBlank(roleId)) {
				try {
					List<ModuleRole> moduleRoles = new ArrayList<ModuleRole>(moduleIds.length);
					for (String moduleId : moduleIds) {
						ModuleRole moduleRole = new ModuleRole();
						moduleRole.setModuleId(Long.valueOf(moduleId));
						moduleRole.setRoleId(Long.valueOf(roleId));
						moduleRoles.add(moduleRole);
					}
					Map<String, Object> parameters = new HashMap<String, Object>();
					parameters.put("list", moduleRoles);
					roleService.addModuleRole(parameters);
					ajaxSuccess("添加成功！");
				} catch (Exception e) {
					ajaxError("添加失败:"+e.getMessage());
				}
			} else {
				ajaxError("提交的参数缺失！");
			}
		} else {
			ajaxError("提交的参数缺失！");
		}
		return null;
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
