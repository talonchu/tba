package com.perficient.bcten.service.impl;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.Test;

import com.perficient.bcten.dao.NotEligibleEmployeeDao;
import com.perficient.bcten.entity.Employee;

public class NotEligibleEmployeeServiceImplTest extends EasyMockSupport {
	private NotEligibleEmployeeDao notEligibleEmployeeDao = createMock(NotEligibleEmployeeDao.class);
	private List<Employee> mockEmployees;

	private static List<Employee> createEmployeeList() {
		Employee employee = new Employee();
		employee.setEmployeeId("HI194");
		employee.setId(1);
		employee.setName("h");
		List<Employee> mockEmployeesList = new ArrayList<Employee>();
		mockEmployeesList.add(employee);
		return mockEmployeesList;
	}

	@Test
	public void getAllNotEmployeeTest() {
		Date date = Date.valueOf("2012-04-04");
		NotEligibleEmployeeServiceImpl notEligibleEmployeeImpl = new NotEligibleEmployeeServiceImpl();
		mockEmployees = createEmployeeList();

		EasyMock.expect(notEligibleEmployeeDao.findNotEligibleEmployees(date)).andReturn(mockEmployees);
		EasyMock.replay(notEligibleEmployeeDao);
		notEligibleEmployeeImpl.setNotEligibleEmployeeDao(notEligibleEmployeeDao);
		notEligibleEmployeeImpl.getNotEligibleEmployeeDao();

		assertEquals(mockEmployees, notEligibleEmployeeImpl.getAllNotEligibleEmployee(date));
	}

}
