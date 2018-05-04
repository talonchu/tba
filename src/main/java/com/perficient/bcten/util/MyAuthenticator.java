package com.perficient.bcten.util;

import javax.mail.*;

public class MyAuthenticator extends Authenticator {
	private String userName;
	private String password;

	public MyAuthenticator(String username, String password) {
		this.userName = username;
		this.password = password;
	}

	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}
}