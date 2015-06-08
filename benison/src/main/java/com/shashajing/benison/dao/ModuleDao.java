package com.shashajing.benison.dao;

import java.util.List;
import java.util.Map;

import com.shashajing.benison.entity.Module;
import com.shashajing.benison.entity.User;

/**
 * 功能菜单dao
 * @author yanghanjing
 *
 */
@MyBatisDao
public interface ModuleDao {
	
	List<Module> searchModule(Map<String, Object> parameters);
	
	int countModule(Map<String, Object> parameters);
	
	List<Module> findModuleAndRole(Map<String, Object> parameters);
	
	List<Module> searchModuleNotBelongRole(Map<String, Object> parameters);
	
	int countModuleNotBelongRole(Map<String, Object> parameters);
	
	int addModule(Module module);
	
	int updateModule(Module module);
	
	int deleteModule(List<Long> ids);
}
