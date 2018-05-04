package com.perficient.bcten.service.impl;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.Test;

import com.perficient.bcten.dao.EmployeeDao;
import com.perficient.bcten.dao.NotEligibleEmployeeDao;
import com.perficient.bcten.entity.Employee;
import com.perficient.bcten.model.MarkedEmployee;
import com.perficient.bcten.service.impl.EmployeeServiceImpl;

public class EmployeeServiceImplTest extends EasyMockSupport {
	private List<Employee> mockEmployees;
	private EmployeeServiceImpl employeeServiceImpl;
	private EmployeeDao employeeDao = createMock(EmployeeDao.class);
	private NotEligibleEmployeeDao notEligibleEmployeeDao = createMock(NotEligibleEmployeeDao.class);

	private static List<Employee> createEmployeeList() {
		Employee employee = new Employee();
		employee.setEmployeeId("HI194");
		employee.setId(1);
		employee.setName("h");
		List<Employee> mockEmployeesList = new ArrayList<Employee>();
		mockEmployeesList.add(employee);
		return mockEmployeesList;
	}

	private static List<Employee> createEmployeeListwithOnbordDate() {
		Employee employee = new Employee();
		employee.setEmployeeId("HI194");
		employee.setId(1);
		employee.setName("h");
		employee.setOnboardDate(Date.valueOf("2011-01-31"));
		Employee employee2 = new Employee();
		employee2.setEmployeeId("HI194");
		employee2.setId(2);
		employee2.setName("h");
		employee2.setOnboardDate(new Date(System.currentTimeMillis()));
		List<Employee> mockEmployeesList = new ArrayList<Employee>();
		mockEmployeesList.add(employee);
		mockEmployeesList.add(employee2);
		return mockEmployeesList;
	}

	private static List<Employee> createEmployeeListwithOnbordDateAndWorkStartDate() {
		Employee employee = new Employee();
		employee.setEmployeeId("HI194");
		employee.setId(1);
		employee.setName("h");
		employee.setOnboardDate(Date.valueOf("2011-02-02"));
		employee.setWorkStartDate(Date.valueOf("2012-02-02"));

		Employee employee2 = new Employee();
		employee2.setEmployeeId("HI194");
		employee2.setId(2);
		employee2.setName("h");
		employee2.setOnboardDate(new Date(System.currentTimeMillis()));
		employee.setWorkStartDate(Date.valueOf("2012-02-02"));
		List<Employee> mockEmployeesList = new ArrayList<Employee>();
		mockEmployeesList.add(employee);
		mockEmployeesList.add(employee2);
		return mockEmployeesList;
	}

	@Test
	public void getAllEmployeeWithDaoDataBackTest() {
		employeeServiceImpl = new EmployeeServiceImpl();
		mockEmployees = createEmployeeList();

		EasyMock.expect(employeeDao.findEmlpoyees()).andReturn(mockEmployees);
		EasyMock.replay(employeeDao);
		employeeServiceImpl.setEmployeeDao(employeeDao);

		assertEquals(mockEmployees, employeeServiceImpl.getAllEmployees());
	}

	@Test
	public void getMarkedEmployeesWithNotNullTest() {
		Date teamBuildingTime = Date.valueOf("2012-04-04");
		employeeServiceImpl = new EmployeeServiceImpl();
		mockEmployees = createEmployeeList();
		EasyMock.expect(employeeDao.findEmlpoyees()).andReturn(mockEmployees);
		EasyMock.replay(employeeDao);
		employeeServiceImpl.setEmployeeDao(employeeDao);
		employeeServiceImpl.getEmployeeDao();

		EasyMock.expect(notEligibleEmployeeDao.findNotEligibleEmployees(teamBuildingTime)).andReturn(mockEmployees);
		EasyMock.replay(notEligibleEmployeeDao);
		employeeServiceImpl.setNotEligibleEmployeeDao(notEligibleEmployeeDao);
		employeeServiceImpl.getNotEligibleEmployeeDao();

		List<MarkedEmployee> markedEmployees = new ArrayList<MarkedEmployee>();
		MarkedEmployee mEmployee = new MarkedEmployee();
		mEmployee.setEligible(false);
		Employee employee = new Employee();
		employee.setEmployeeId("HI194");
		employee.setId(1);
		employee.setName("h");
		mEmployee.setEmployee(employee);

		markedEmployees.add(mEmployee);
		assertEquals(markedEmployees.size(), employeeServiceImpl.getMarkedEmployees(teamBuildingTime).size());
	}

	@Test
	public void getMarkedEmployeesWithNullTest() {
		Date teamBuildingTime = Date.valueOf("2012-04-04");
		employeeServiceImpl = new EmployeeServiceImpl();
		mockEmployees = createEmployeeList();
		EasyMock.expect(employeeDao.findEmlpoyees()).andReturn(mockEmployees);
		EasyMock.replay(employeeDao);
		employeeServiceImpl.setEmployeeDao(employeeDao);
		employeeServiceImpl.getEmployeeDao();

		EasyMock.expect(notEligibleEmployeeDao.findNotEligibleEmployees(teamBuildingTime)).andReturn(null);
		EasyMock.replay(notEligibleEmployeeDao);
		employeeServiceImpl.setNotEligibleEmployeeDao(notEligibleEmployeeDao);
		employeeServiceImpl.getNotEligibleEmployeeDao();

		List<MarkedEmployee> markedEmployees = new ArrayList<MarkedEmployee>();
		MarkedEmployee mEmployee = new MarkedEmployee();
		mEmployee.setEligible(false);
		Employee employee = new Employee();
		employee.setEmployeeId("HI194");
		employee.setId(1);
		employee.setName("h");
		mEmployee.setEmployee(employee);

		markedEmployees.add(mEmployee);
		assertEquals(markedEmployees.size(), employeeServiceImpl.getMarkedEmployees(teamBuildingTime).size());
	}

	@Test
	public void getMarkedEmployeesWithNotNullTest2() {
		Date teamBuildingTime = Date.valueOf("2012-04-04");
		employeeServiceImpl = new EmployeeServiceImpl();
		mockEmployees = createEmployeeListwithOnbordDate();
		EasyMock.expect(employeeDao.findEmlpoyees()).andReturn(mockEmployees);
		EasyMock.replay(employeeDao);
		employeeServiceImpl.setEmployeeDao(employeeDao);
		employeeServiceImpl.getEmployeeDao();

		EasyMock.expect(notEligibleEmployeeDao.findNotEligibleEmployees(teamBuildingTime)).andReturn(mockEmployees);
		EasyMock.replay(notEligibleEmployeeDao);
		employeeServiceImpl.setNotEligibleEmployeeDao(notEligibleEmployeeDao);
		employeeServiceImpl.getNotEligibleEmployeeDao();

		List<MarkedEmployee> markedEmployees = new ArrayList<MarkedEmployee>();
		MarkedEmployee mEmployee = new MarkedEmployee();
		mEmployee.setEligible(false);
		Employee employee = new Employee();
		employee.setEmployeeId("HI194");
		employee.setId(1);
		employee.setName("h");
		mEmployee.setEmployee(employee);

		MarkedEmployee mEmployee2 = new MarkedEmployee();
		mEmployee2.setEligible(false);
		Employee employee2 = new Employee();
		employee2.setEmployeeId("HI194");
		employee2.setId(2);
		employee2.setName("h");
		mEmployee2.setEmployee(employee2);

		markedEmployees.add(mEmployee);
		markedEmployees.add(mEmployee2);
		assertEquals(markedEmployees.size(), employeeServiceImpl.getMarkedEmployees(teamBuildingTime).size());
	}

	@Test
	public void getMarkedEmployeesWithNotNullTest3() {
		Date teamBuildingTime = Date.valueOf("2012-01-31");
		employeeServiceImpl = new EmployeeServiceImpl();
		mockEmployees = createEmployeeListwithOnbordDateAndWorkStartDate();
		EasyMock.expect(employeeDao.findEmlpoyees()).andReturn(mockEmployees);
		EasyMock.replay(employeeDao);
		employeeServiceImpl.setEmployeeDao(employeeDao);
		employeeServiceImpl.getEmployeeDao();

		EasyMock.expect(notEligibleEmployeeDao.findNotEligibleEmployees(teamBuildingTime)).andReturn(mockEmployees);
		EasyMock.replay(notEligibleEmployeeDao);
		employeeServiceImpl.setNotEligibleEmployeeDao(notEligibleEmployeeDao);
		employeeServiceImpl.getNotEligibleEmployeeDao();

		List<MarkedEmployee> markedEmployees = new ArrayList<MarkedEmployee>();
		MarkedEmployee mEmployee = new MarkedEmployee();
		mEmployee.setEligible(false);
		Employee employee = new Employee();
		employee.setEmployeeId("HI194");
		employee.setId(1);
		employee.setName("h");
		mEmployee.setEmployee(employee);

		MarkedEmployee mEmployee2 = new MarkedEmployee();
		mEmployee2.setEligible(false);
		Employee employee2 = new Employee();
		employee2.setEmployeeId("HI194");
		employee2.setId(2);
		employee2.setName("h");
		mEmployee2.setEmployee(employee2);

		markedEmployees.add(mEmployee);
		markedEmployees.add(mEmployee2);
		assertEquals(markedEmployees.size(), employeeServiceImpl.getMarkedEmployees(teamBuildingTime).size());
	}

}
