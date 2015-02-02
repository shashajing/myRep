package com.shashajing.benison.common;

import com.shashajing.benison.utils.JsonUtils;

/**
 * 
 * @author yanghanjing
 */
public class AjaxObject {
	private boolean success;
	private String message;
	private String data;
	public boolean isSuccess() {
		return success;
	}
	public void setSucess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public String toJson() {
		return new JsonUtils().toJson(this);
	}
}