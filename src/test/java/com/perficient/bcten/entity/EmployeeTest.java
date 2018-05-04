package com.perficient.bcten.entity;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import org.junit.Test;

public class EmployeeTest {
	Employee employee = new Employee();

	@Test
	public void setGetEmployeeId() {
		employee.setEmployeeId("88");
		assertEquals("88", employee.getEmployeeId());
	}

	@Test
	public void setGetName() {
		employee.setName("root");
		assertEquals("root", employee.getName());
	}

	@Test
	public void setGetLevel() {
		employee.setLevel("level-5");
		assertEquals("level-5", employee.getLevel());
	}

	@Test
	public void setGetOnboardDate() {
		Date date = new Date(2012, 4, 1);
		employee.setOnboardDate(date);
		assertEquals(date, employee.getOnboardDate());
	}

	@Test
	public void setGeWorkStarttDate() {
		Date date = new Date(2012, 4, 1);
		employee.setWorkStartDate(date);
		assertEquals(date, employee.getWorkStartDate());
	}
}
