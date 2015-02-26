package com.shashajing.benison.common;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.shashajing.benison.entity.Module;
import com.shashajing.benison.service.ModuleService;
import com.shashajing.benison.utils.SpringBeanUtils;



/**
 * 功能菜单缓存
 * @author yanghanjing
 */
@Component
public class ModuleCache {
	private static Long lastUpdateTime;
	private final static int UPDATE_MINUTE = 30;
	private static List<Module> modules;
	
	public synchronized static List<Module> getCacheModules() {
		if (null == modules || modules.isEmpty()) {
			loadModules();
		} else if (null != lastUpdateTime) {
			Long currentTime = new Date().getTime();
			if ((currentTime - lastUpdateTime) > UPDATE_MINUTE * 60 * 1000) {
				loadModules();
			}
		}
		return modules;
	}

	private static void loadModules() {
		ModuleService moduleService = (ModuleService)SpringBeanUtils.getBean("moduleService");
		modules = moduleService.findModuleAndRole(null);
	}

	public static Module getCacheModule(String moduleName) {
		List<Module> modules = getCacheModules();
		if (null != modules && !modules.isEmpty()) {
			for (Module module : modules) {
				if (module.getModuleName().equals(moduleName)) {
					return module;
				}
			}
		}
		return null;
	}
	
}