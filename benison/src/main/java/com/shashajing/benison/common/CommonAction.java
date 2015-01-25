package com.shashajing.benison.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class CommonAction extends ActionSupport{

	private static final long serialVersionUID = -7586585407729436524L;

	private String id;
	private String operateType;//search,add,update,edit,delete,view
	private Page page;
	private HttpServletRequest httpServletRequest;
	private HttpServletResponse httpServletResponse;
	
	public String getId() {
		return id;
	}
	public HttpServletRequest getHttpServletRequest() {
		return ServletActionContext.getRequest();
	}
	public HttpServletResponse getHttpServletResponse() {
		return ServletActionContext.getResponse();
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
