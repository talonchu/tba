package com.perficient.bcten.dao;

import java.sql.Date;
import java.util.List;

import com.perficient.bcten.entity.Employee;

public interface NotEligibleEmployeeDao {
	List<Employee> findNotEligibleEmployees(Date teamBuildingTime);
}
