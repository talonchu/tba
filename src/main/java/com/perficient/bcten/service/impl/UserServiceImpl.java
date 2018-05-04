package com.perficient.bcten.service.impl;

import com.perficient.bcten.dao.PermissionDao;
import com.perficient.bcten.service.UserService;
import com.perficient.bcten.util.LDAPconnect;

public class UserServiceImpl implements UserService {
	private LDAPconnect ldap;
	private String version;
	private PermissionDao permissionDao;

	public void setLdap(LDAPconnect ldap) {
		this.ldap = ldap;
	}

	public PermissionDao getPermissionDao() {
		return permissionDao;
	}

	public void setPermissionDao(PermissionDao permissionDao) {
		this.permissionDao = permissionDao;
	}
	
	public String getVersion() {
		return this.version;
	}
	
	public void setVersion(String version){
		this.version = version;
	}

	public LDAPconnect getLdap() {
		return ldap;
	}

	public boolean login(String username, String password) {
		if (null == ldap) {
			ldap = new LDAPconnect();
		}
		if (ldap.getAuthentication(username, password)) {
			return true;
		}
		return false;
	}

	public int getRole(String username) {
		return permissionDao.validatePermission(username);
	}
	
	
}
