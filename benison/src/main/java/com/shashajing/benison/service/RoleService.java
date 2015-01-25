package com.shashajing.benison.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.shashajing.benison.dao.RoleDao;
import com.shashajing.benison.entity.Role;
import com.shashajing.benison.entity.UserRole;

@Component
@Transactional
public class RoleService {
	
	@Autowired
	private RoleDao roleDao;
	
	public List<Role> searchRole(Map<String, Object> parameters) {
		return roleDao.searchRole(parameters);
	}
	
	public Role searchRoleById(String id) {
		Map<String, Object> parameters = Maps.newHashMap();
		parameters.put("roleId", id);
		List<Role> list = roleDao.searchRole(parameters);
		return list.isEmpty()?null:list.get(0);
	}
	
	public List<UserRole> userRoleSearch(Map<String, Object> parameters) {
		return roleDao.userRoleSearch(parameters);
	}

	public int addRole(Role role) {
		return roleDao.addRole(role);
	}
	
	
	public int updateRole(Role role) {
		return roleDao.updateRole(role);
	}
	
	public int deleteRole(List<Long> ids) {
		return roleDao.deleteRole(ids);
	}
}
