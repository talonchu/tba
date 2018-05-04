package com.perficient.bcten.util;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import com.perficient.bcten.util.log4j.LogProvider;

public class LDAPconnect {

	public boolean getAuthentication(String username, String password) {

		DirContext ctx = null;

		Hashtable<String, String> env = new Hashtable<String, String>();

		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");

		env.put(Context.PROVIDER_URL, "ldap://10.2.1.22:389/");

		env.put(Context.SECURITY_AUTHENTICATION, "simple");

		env.put(Context.SECURITY_PRINCIPAL, "cn=" + username + ",ou=Employees,DC=perficient,DC=com");

		env.put(Context.SECURITY_CREDENTIALS, password);

		try {
			ctx = new InitialDirContext(env);
			closeDirContext(ctx);
			return true;
		} catch (Exception e) {
			LogProvider.error(LDAPconnect.class.getName(), e);
			closeDirContext(ctx);
			return false;
		}
	}

	private void closeDirContext(DirContext ctx) {
		if (ctx != null) {
			try {
				ctx.close();
			} catch (NamingException e) {
				LogProvider.error(LDAPconnect.class.getName(), e);
			}
		}
	}

}
