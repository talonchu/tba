package com.perficient.bcten.service;

import java.sql.Date;
import java.util.List;

import com.perficient.bcten.entity.Employee;
import com.perficient.bcten.model.MarkedEmployee;

public interface EmployeeService {
	List<Employee> getAllEmployees();

	List<MarkedEmployee> getMarkedEmployees(Date teamBuildingTime);
}
