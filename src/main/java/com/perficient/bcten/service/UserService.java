package com.perficient.bcten.service;

public interface UserService {
	
	boolean login(String username, String password);

	int getRole(String username);
	
	String getVersion();
		
}
