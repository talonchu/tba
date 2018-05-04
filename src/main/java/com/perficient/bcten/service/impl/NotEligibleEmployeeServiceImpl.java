package com.perficient.bcten.service.impl;

import java.sql.Date;
import java.util.List;

import com.perficient.bcten.dao.NotEligibleEmployeeDao;
import com.perficient.bcten.entity.Employee;
import com.perficient.bcten.service.NotEligibleEmployeeService;

public class NotEligibleEmployeeServiceImpl implements NotEligibleEmployeeService {
	private NotEligibleEmployeeDao notEligibleEmployeeDao;

	public NotEligibleEmployeeDao getNotEligibleEmployeeDao() {
		return notEligibleEmployeeDao;
	}

	public void setNotEligibleEmployeeDao(NotEligibleEmployeeDao notEligibleEmployeeDao) {
		this.notEligibleEmployeeDao = notEligibleEmployeeDao;
	}

	@Override
	public List<Employee> getAllNotEligibleEmployee(Date date) {

		return (List<Employee>) notEligibleEmployeeDao.findNotEligibleEmployees(date);
	}

}
