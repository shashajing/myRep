package com.shashajing.benison.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.shashajing.benison.common.CommonAction;
import com.shashajing.benison.entity.Module;
import com.shashajing.benison.service.ModuleService;

@Component("moduleAction")
@Scope("prototype")
public class ModuleAction extends CommonAction{

	private static final long serialVersionUID = 7515358606387010997L;

	private List<Module> moduleList;
	private List<Module> searchModuleList;
	private Module searchModule;
	private Module editModule;
	
	@Autowired
	private ModuleService moduleService;
	
	@Override
	public String execute() throws Exception {
		editModule = null;
		setOperateType("search");
		Map<String, Object> parameters = Maps.newHashMap();
		if (null != searchModule) {
			parameters.put("moduleId", searchModule.getModuleId());
			parameters.put("parentId", searchModule.getParentId());
			parameters.put("moduleName", StringUtils.trimToNull(searchModule.getModuleName()));
			parameters.put("type", searchModule.getType());
		}
		getPage().setTotal(Long.valueOf(moduleService.countModule(parameters)));
		parameters.put("start", getPage().getStart());//起始下标
		parameters.put("pageNum", getPage().getPageNum());//每页显示数量
		moduleList = moduleService.searchModule(parameters);
		parameters.clear();
		searchModuleList = moduleService.searchModule(parameters);
		return "success";
	}
	
	public String initInput() {
		if (StringUtils.isNotBlank(getId())) {
			editModule = moduleService.searchMoMuleById(getId());
			searchModuleList = moduleService.searchModule(null);
		}
		return "success";
	}
	
	public String editModule() {
		if (editModule != null) {
			if (editModule.getModuleId() != null) {
				moduleService.updateModule(editModule);
			} else {
				moduleService.addModule(editModule);
			}
		}
		return "toModuleList";
	}
	
	public String deleteModule() {
		if (StringUtils.isNotBlank(getId())) {
			String[] ids = getId().split(",");
			List<Long> idList = new ArrayList<Long>(ids.length);
			for (String uId : ids) {
				idList.add(Long.valueOf(uId));
			}
			if (!idList.isEmpty()) {
				moduleService.deleteModule(idList);
			}
		}
		return "toModuleList";
	}

	public Module getSearchModule() {
		return searchModule;
	}

	public void setSearchModule(Module searchModule) {
		this.searchModule = searchModule;
	}

	public Module getEditModule() {
		return editModule;
	}

	public void setEditModule(Module editModule) {
		this.editModule = editModule;
	}

	public List<Module> getModuleList() {
		return moduleList;
	}

	public List<Module> getSearchModuleList() {
		return searchModuleList;
	}
}
