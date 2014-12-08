package com.shashajing.benison.entity;

import java.util.Date;


/**
 * 用户.
 * 
 * @author yanghanjing
 */
public class User {
	
	private int uId;
	private String loginName;
	private String loginPassword;
	private int type;	
	private String userName;
	private String email;
	private String tel;
	private int status;
	private Date editTime;
	private String editUserName;
	
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getEditTime() {
		return editTime;
	}
	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}
	public String getEditUserName() {
		return editUserName;
	}
	public void setEditUserName(String editUserName) {
		this.editUserName = editUserName;
	}
}