package com.shashajing.benison.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;
import com.shashajing.benison.utils.JsonUtils;

public class CommonAction extends ActionSupport{

	private static final long serialVersionUID = -7586585407729436524L;

	protected Logger logger = LoggerFactory.getLogger(getClass());
	private String id;
	private String operateType;//search,add,update,edit,delete,view
	private Page page;
	public static final String DEFAULT_ENCODING = "UTF-8";//系统默认编码
	/**
	 * Content Type
	 */
	private static final String TEXT_TYPE = "text/plain";
	private static final String JSON_TYPE = "application/json";
	
	public void ajaxDataList(String jsonContent) {
		responseToPage(jsonContent, JSON_TYPE, DEFAULT_ENCODING);
	}
	
	public void ajaxSuccess(String message) {
		AjaxObject ajaxObject = new AjaxObject();
		ajaxObject.setSuccess(true);
		ajaxObject.setMessage(message);
		String jsonContent = new JsonUtils().toJson(ajaxObject);
		responseToPage(jsonContent, JSON_TYPE, DEFAULT_ENCODING);
	}
	
	public void ajaxSuccess(String message, String data) {
		AjaxObject ajaxObject = new AjaxObject();
		ajaxObject.setSuccess(true);
		ajaxObject.setMessage(message);
		ajaxObject.setData(data);
		String jsonContent = new JsonUtils().toJson(ajaxObject);
		responseToPage(jsonContent, JSON_TYPE, DEFAULT_ENCODING);
	}
	
	public void ajaxError(String message) {
		AjaxObject ajaxObject = new AjaxObject();
		ajaxObject.setSuccess(false);
		ajaxObject.setMessage(message);
		String jsonContent = new JsonUtils().toJson(ajaxObject);
		responseToPage(jsonContent, JSON_TYPE, DEFAULT_ENCODING);
	}
	
	public void ajaxError(String message, String data) {
		AjaxObject ajaxObject = new AjaxObject();
		ajaxObject.setSuccess(false);
		ajaxObject.setMessage(message);
		ajaxObject.setData(data);
		String jsonContent = new JsonUtils().toJson(ajaxObject);
		responseToPage(jsonContent, JSON_TYPE, DEFAULT_ENCODING);
	}
	
	private static void responseToPage(String content, String contentType, String encoding) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType(contentType + ";charset=" + encoding);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		try {
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
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
