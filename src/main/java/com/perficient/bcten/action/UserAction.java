package com.perficient.bcten.action;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.perficient.bcten.service.UserService;

public class UserAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = -5614660438567804603L;
	private UserService userService;
	private String username;
	private String password;
	private String msg;
	private Map<String, Object> session;

	/*---------setter && getter----------*/
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	/*---------other function----------*/
	public String login() {
		String uname = username;
		String pword = password;
		if (username != null && !"".equals(username)) {
			if (userService.login(uname, pword)) {
				session.put("name", uname);
				if (userService.getRole(uname) == 1) {
					session.put("role", 1);
				} else if (userService.getRole(uname) == 2) {
					session.put("role", 2);

				}
				if (session.get("requestId") != null) {
					return "emailLinkAction";
				}

				return SUCCESS;
			} else {
				msg = "incorrect login or password";
				return ERROR;
			}
		} else {
			msg = "Please input the username and password";
			return ERROR;
		}
	}

	public String logout() {
		ServletActionContext.getRequest().getSession().invalidate();
		return SUCCESS;
	}
}
