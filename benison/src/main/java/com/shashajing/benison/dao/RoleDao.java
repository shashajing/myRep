package com.shashajing.benison.dao;

import java.util.List;
import java.util.Map;

import com.shashajing.benison.entity.ModuleRole;
import com.shashajing.benison.entity.Role;
import com.shashajing.benison.entity.UserRole;

/**
 * 角色dao
 * @author yanghanjing
 *
 */
@MyBatisDao
public interface RoleDao {
	
	List<Role> searchRole(Map<String, Object> parameters);
	
	List<UserRole> userRoleSearch(Map<String, Object> parameters);
	
	List<ModuleRole> moduleRoleSearch(Map<String, Object> parameters);
	
	int addRole(Role role);
	
	int addUserRole(Map<String, Object> parameters);
	
	int addModuleRole(Map<String, Object> parameters);
	
	int updateRole(Role role);
	
	int deleteRole(List<Long> ids);
	
	int deleteUserRole(Map<String, Object> parameters);
	
	int deleteModuleRole(Map<String, Object> parameters);
}
