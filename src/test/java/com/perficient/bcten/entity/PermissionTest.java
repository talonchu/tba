package com.perficient.bcten.entity;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PermissionTest {
	Permission permission = new Permission();

	@Test
	public void setGetEmployeeName() {
		permission.setEmployeeName("test");
		assertEquals("test", permission.getEmployeeName());
	}

	@Test
	public void setGetRole() {
		Integer role = new Integer(1);
		permission.setRole(1);
		assertEquals(role, permission.getRole());
	}
}
