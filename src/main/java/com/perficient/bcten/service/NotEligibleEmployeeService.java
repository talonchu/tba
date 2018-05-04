package com.perficient.bcten.service;

import java.sql.Date;
import java.util.List;

import com.perficient.bcten.entity.Employee;

public interface NotEligibleEmployeeService {
	List<Employee> getAllNotEligibleEmployee(Date date);
}
