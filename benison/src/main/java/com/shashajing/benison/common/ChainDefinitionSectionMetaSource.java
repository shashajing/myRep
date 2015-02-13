package com.shashajing.benison.common;


import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.CollectionUtils;

public class ChainDefinitionSectionMetaSource implements FactoryBean<Ini.Section> {

	//shiro默认的链接定义
    private String filterChainDefinitions;
    
    /**
     * 通过filterChainDefinitions对默认的链接过滤定义
     * 
     * @param filterChainDefinitions 默认的接过滤定义
     */
    public void setFilterChainDefinitions(String filterChainDefinitions) {
        this.filterChainDefinitions = filterChainDefinitions;
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

        //循环数据库资源的url
        /*for (Resource resource : resourceDao.getAll()) {
            if(StringUtils.isNotEmpty(resource.getValue()) && StringUtils.isNotEmpty(resource.getPermission())) {
                section.put(resource.getValue(), resource.getPermission());
            }
        }*/
        section.put("/admin/login.action", "anon");
        section.put("/admin/role**", "authc,roles[fhhhhh]");
        section.put("/admin/**", "authc");

        return section;
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
