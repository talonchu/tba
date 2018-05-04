package com.perficient.bcten.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EmailInfoTest {
	EmailInfo emailInfo = new EmailInfo();

	@Test
	public void setGetUsername() {
		emailInfo.setUsername("88");
		assertEquals("88", emailInfo.getUsername());
	}

	@Test
	public void setGetPassword() {
		emailInfo.setPassword("88");
		assertEquals("88", emailInfo.getPassword());
	}

	@Test
	public void setGetDefaultToAddress() {
		emailInfo.setDefaultToAddress("88");
		assertEquals("88", emailInfo.getDefaultToAddress());
	}

	@Test
	public void setGetDefaultSubject() {
		emailInfo.setDefaultSubject("88");
		assertEquals("88", emailInfo.getDefaultSubject());
	}

	@Test
	public void setGetDefaultContent() {
		emailInfo.setDefaultContent("88");
		assertEquals("88", emailInfo.getDefaultContent());
	}

	@Test
	public void setGetFromAddress() {
		emailInfo.setFromAddress("88");
		assertEquals("88", emailInfo.getFromAddress());
	}

	@Test
	public void setGetToAddress() {
		emailInfo.setToAddress("88");
		assertEquals("88", emailInfo.getToAddress());
	}

	@Test
	public void setGetSubject() {
		emailInfo.setSubject("88");
		assertEquals("88", emailInfo.getSubject());
	}

	@Test
	public void setGetContent() {
		emailInfo.setContent("88");
		assertEquals("88", emailInfo.getContent());
	}
}
