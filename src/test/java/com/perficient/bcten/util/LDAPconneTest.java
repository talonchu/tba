package com.perficient.bcten.util;

import static org.junit.Assert.assertEquals;

import javax.naming.NamingException;

import org.junit.Test;
import com.perficient.bcten.util.LDAPconnect;

public class LDAPconneTest {

	@Test
	public void connectWithRightAccount() throws NamingException {
		LDAPconnect ldap = new LDAPconnect();
		assertEquals(false, ldap.getAuthentication("", "dsa"));
	}

	@Test
	public void connectWithRightAccount2() throws NamingException {
		LDAPconnect ldap = new LDAPconnect();
		assertEquals(true, ldap.getAuthentication("terrence.zhang", "Zy108020171"));
	}
}
