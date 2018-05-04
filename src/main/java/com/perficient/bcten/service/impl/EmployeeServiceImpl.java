package com.perficient.bcten.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.perficient.bcten.dao.EmployeeDao;
import com.perficient.bcten.dao.NotEligibleEmployeeDao;
import com.perficient.bcten.entity.Employee;
import com.perficient.bcten.model.MarkedEmployee;
import com.perficient.bcten.service.EmployeeService;
import com.perficient.bcten.util.exception.CException;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDao employeeDao;
	private NotEligibleEmployeeDao notEligibleEmployeeDao;

	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public NotEligibleEmployeeDao getNotEligibleEmployeeDao() {
		return notEligibleEmployeeDao;
	}

	public void setNotEligibleEmployeeDao(NotEligibleEmployeeDao notEligibleEmployeeDao) {
		this.notEligibleEmployeeDao = notEligibleEmployeeDao;
	}

	public List<Employee> getAllEmployees() {
		List<Employee> employees = employeeDao.findEmlpoyees();
		if (employees == null) {
			throw new CException();
		}
		return employees;
	}

	public List<MarkedEmployee> getMarkedEmployees(Date teamBuildingTime) {
		List<MarkedEmployee> markedEmployees = new ArrayList<MarkedEmployee>();
		List<Employee> notEligibleEmployees = notEligibleEmployeeDao.findNotEligibleEmployees(teamBuildingTime);
		List<Employee> allEmployees = getAllEmployees();
		if (notEligibleEmployees == null) {
			notEligibleEmployees = new ArrayList<Employee>();
		}
		for (Employee employee : allEmployees) {
			MarkedEmployee markedEmployee = new MarkedEmployee();
			markedEmployee.setEmployee(employee);
			Date onboardDate = employee.getOnboardDate();
			Date workStartDate = employee.getWorkStartDate();
			if (onboardDate == null && workStartDate == null) {
				markedEmployee.setEligible(false);
				markedEmployees.add(markedEmployee);
				continue;
			}
			Date bigDate = getBiggerDate(onboardDate, workStartDate);
			Date teamBuildingAccessDate = getTeamBuildingAccessDate(bigDate);
			if ("Intern".equals(employee.getLevel()) || teamBuildingAccessDate.getTime() > teamBuildingTime.getTime()) {
				markedEmployee.setEligible(false);
				markedEmployees.add(markedEmployee);
				continue;
			}
			markedEmployee.setEligible(true);
			for (Employee notEligibleemployee : notEligibleEmployees) {
				if (employee.getName().equals(notEligibleemployee.getName())) {
					markedEmployee.setEligible(false);
					break;
				}
			}
			markedEmployees.add(markedEmployee);
		}
		return markedEmployees;
	}

	private Date getBiggerDate(Date onboardDate, Date workStartDate) {
		Date date;
		if (onboardDate == null || workStartDate == null) {
			date = onboardDate == null ? workStartDate : onboardDate;
		} else {
			date = onboardDate.getTime() > workStartDate.getTime() ? onboardDate : workStartDate;
		}
		return date;

	}

	private Date getTeamBuildingAccessDate(Date bigDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(bigDate);
		c.add(Calendar.MONTH, Integer.parseInt("3"));
		Date date = new Date(c.getTimeInMillis());
		if (bigDate.getDate() > date.getDate()) {
			c.add(Calendar.DAY_OF_MONTH, 1);
			date = new Date(c.getTimeInMillis());
		}
		return date;

	}
}
