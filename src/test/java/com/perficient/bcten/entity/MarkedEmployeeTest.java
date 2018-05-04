package com.perficient.bcten.entity;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import javassist.expr.NewArray;

import org.junit.Test;

import com.perficient.bcten.model.MarkedEmployee;

public class MarkedEmployeeTest {
	MarkedEmployee markedEmployee = new MarkedEmployee();
	Employee employee = new Employee();

	@Test
	public void setGetEmployee() {
		markedEmployee.setEmployee(employee);
		assertEquals(employee, markedEmployee.getEmployee());
	}

	@Test
	public void setIsEligible() {
		markedEmployee.setEligible(true);
		assertEquals(true, markedEmployee.getIsEligible());
	}
}
