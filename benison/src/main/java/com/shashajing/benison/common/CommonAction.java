package com.shashajing.benison.common;

import com.opensymphony.xwork2.ActionSupport;

public class CommonAction extends ActionSupport{

	private static final long serialVersionUID = -7586585407729436524L;

	private String id;
	private String operateType;//search,add,update,edit,delete,view
	private Page page;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	public Page getPage() {
		if (null == page) {
			page = new Page();
		}
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
}
