package com.shashajing.benison.common;


import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.shashajing.benison.dao.ModuleDao;
import com.shashajing.benison.entity.Module;

public class ChainDefinitionSectionMetaSource implements FactoryBean<Ini.Section> {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	//shiro默认的链接定义
    private String filterChainDefinitions;
    
    //全局链接定义，反正最后面加载，以免覆盖局部定义
    private String globalDefinitions;
    
    @Autowired
    private ModuleDao moduleDao;
    
    /**
     * 通过filterChainDefinitions对默认的链接过滤定义
     * 
     * @param filterChainDefinitions 默认的接过滤定义
     */
    public void setFilterChainDefinitions(String filterChainDefinitions) {
        this.filterChainDefinitions = filterChainDefinitions;
    }
    
	public void setGlobalDefinitions(String globalDefinitions) {
		this.globalDefinitions = globalDefinitions;
	}



	@Override
	public Section getObject() throws Exception {
		
		Ini ini = new Ini();
		//加载默认的url
        ini.load(filterChainDefinitions);
        
        Ini.Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
        if (CollectionUtils.isEmpty(section)) {
            section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        }
        
        //先加载按钮级别的，再加载页面级别的
        loadDbDefinitions(section);
        
        //加载全局定义
        loadGlobalDefinitions(section);
        
        return section;
	}
	
	private void loadGlobalDefinitions(Section section) {
		if (StringUtils.isBlank(globalDefinitions)) {
			logger.debug("ChainDefinitionSectionMetaSource globalDefinitions is null");
			return;
		}
		String[] definitions = globalDefinitions.split(",");
		for (String def : definitions) {
			if (StringUtils.isBlank(def)) {
				continue;
			}
			String[] v = def.split("=");
			if (v == null || v.length < 1) {
				continue;
			}
			section.put(v[0].trim(), v[1].trim());
		}
		logger.debug("ChainDefinitionSectionMetaSource, loadGlobalDefinitions globalDefinitions ="+globalDefinitions);
	}

	private void loadDbDefinitions(Section section) {
		
		List<Module> modules = moduleDao.findModuleAndRole(null);
		if (modules.isEmpty()) {
			return;
		}
		String definition;
		for (Module module : modules) {
			definition = "";
			if (StringUtils.isBlank(module.getModuleUrl())) {
				continue;
			}
			if (CollectionUtils.isEmpty(module.getRoles())) {
				definition = "authc";
			} else {
				for (int i=0;i<module.getRoles().size();i++) {
					definition += module.getRoles().get(i).getRoleName();
					if (i != module.getRoles().size() - 1) {
						definition += ",";
					}
				}
				definition = "authc,roles["+definition+"]";
			}
			section.put(module.getModuleUrl(), definition);
		}
		logger.debug("ChainDefinitionSectionMetaSource, loadDbDefinitions module size ="+modules.size());
	}

	@Override
	public Class<?> getObjectType() {
		
		return Section.class;
	}

	@Override
	public boolean isSingleton() {
		
		return true;
	}

}
