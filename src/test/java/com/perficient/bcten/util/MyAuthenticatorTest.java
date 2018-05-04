package com.perficient.bcten.util;

import static org.junit.Assert.assertEquals;

import javax.mail.PasswordAuthentication;

import org.junit.Test;

public class MyAuthenticatorTest {

	@Test
	public void test() {
		MyAuthenticator authenticator = new MyAuthenticator("vorce.shen", "halo");
		PasswordAuthentication p = new PasswordAuthentication("vorce.shen", "halo");
		assertEquals(p.getUserName(), authenticator.getPasswordAuthentication().getUserName());
		assertEquals(p.getPassword(), authenticator.getPasswordAuthentication().getPassword());
	}

}
