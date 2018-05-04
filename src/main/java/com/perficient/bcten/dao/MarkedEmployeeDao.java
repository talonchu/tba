package com.perficient.bcten.dao;

import java.util.List;

import com.perficient.bcten.entity.Employee;

public interface MarkedEmployeeDao {
	List<Employee> findNotEligibleEmlpoyees();
}
