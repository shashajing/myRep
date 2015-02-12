package com.shashajing.benison.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shashajing.benison.common.CommonAction;
import com.shashajing.benison.service.AccountService;

@Component("manageLoginAction")
public class ManageLoginAction extends CommonAction {

	private static final long serialVersionUID = -5386843526630329110L;
	
	private String loginName;
	private String password;
	private String rememberMe;
	private String error;
	
	@Autowired
	private  AccountService accountService;
	
	@Override
	public String execute() throws Exception {
		error = null;
		UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
		try {
			SecurityUtils.getSubject().login(token);
		} catch (UnknownAccountException uae) {
			error = "未知账号！";
		} catch (IncorrectCredentialsException ice) {
			error = "密码错误！";
		} catch (LockedAccountException lae) {
			error = "账号被锁定！";
		} catch (DisabledAccountException da) {
			error = "账号被禁用！";
		} catch (ExpiredCredentialsException ea) {
			error = "过期的凭证！";
		} catch (ExcessiveAttemptsException eae) {
			error = "登录失败次数过多！";
		} catch (AuthenticationException ae) {
			error = "账号不可用！";
		} catch (Exception e) {
			error = e.getMessage();
		}
		if (null != error) {
			return SUCCESS;
		} else {
			return "toFrame";
		}
	}
	
	public String gotoMain() {
		
		return "main";
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(String rememberMe) {
		this.rememberMe = rememberMe;
	}

	public String getError() {
		return error;
	}
}
