package com.shashajing.benison.common;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.shashajing.benison.entity.Role;
import com.shashajing.benison.service.RoleService;
import com.shashajing.benison.utils.SpringBeanUtils;



/**
 * 角色缓存
 * @author yanghanjing
 */
@Component
public class RoleCache {
	private static Long lastUpdateTime;
	private final static int UPDATE_MINUTE = 30;
	private static List<Role> roles;
	
	public synchronized static List<Role> getCacheRoles() {
		if (null == roles || roles.isEmpty()) {
			loadRoles();
		} else if (null != lastUpdateTime) {
			Long currentTime = new Date().getTime();
			if ((currentTime - lastUpdateTime) > UPDATE_MINUTE * 60 * 1000) {
				loadRoles();
			}
		}
		return roles;
	}

	private static void loadRoles() {
		RoleService roleService = (RoleService)SpringBeanUtils.getBean("roleService");
		roles = roleService.searchRole(null);
	}

	public static Role getCacheRole(String roleName) {
		List<Role> roles = getCacheRoles();
		if (null != roles && !roles.isEmpty()) {
			for (Role role : roles) {
				if (role.getRoleName().equals(roleName)) {
					return role;
				}
			}
		}
		return null;
	}
	
}