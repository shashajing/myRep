package com.shashajing.benison.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.shashajing.benison.dao.ModuleDao;
import com.shashajing.benison.entity.Module;

@Component
@Transactional
public class ModuleService {
	
	@Autowired
	private ModuleDao moduleDao;
	
	public List<Module> searchModule(Map<String, Object> parameters) {
		return moduleDao.searchModule(parameters);
	}
	
	public int countModule(Map<String, Object> parameters) {
		return moduleDao.countModule(parameters);
	}
	
	public Module searchMoMuleById(String id) {
		Map<String, Object> parameters = Maps.newHashMap();
		parameters.put("moduleId", id);
		List<Module> list = moduleDao.searchModule(parameters);
		return list.isEmpty()?null:list.get(0);
	}

	public List<Module> findModuleAndRole(Map<String, Object> parameters) {
		return moduleDao.findModuleAndRole(parameters);
	}
	
	public int addModule(Module Module) {
		return moduleDao.addModule(Module);
	}
	
	
	public int updateModule(Module Module) {
		return moduleDao.updateModule(Module);
	}
	
	public int deleteModule(List<Long> ids) {
		return moduleDao.deleteModule(ids);
	}
}
