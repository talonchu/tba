package com.perficient.bcten.dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.perficient.bcten.dao.NotEligibleEmployeeDao;
import com.perficient.bcten.entity.Employee;

public class NotEligibleEmployeeDaoImpl extends HibernateDaoSupport implements NotEligibleEmployeeDao {

	@Override
	public List<Employee> findNotEligibleEmployees(Date teamBuildingTime) {
		String sql = "select Employee.employeeName name  from relation Employee left join request rq on(Employee.requestid=rq.id) where rq.teamBuildingTime > DATE_FORMAT(DATE_SUB(str_to_date('"
				+ teamBuildingTime + "','%Y-%m-%d') , INTERVAL 60 DAY) , '" + "%Y%m%d') and rq.requestStatus <> 2 ";
		List<String> nameList = getSession().createSQLQuery(sql).addScalar("NAME", Hibernate.STRING).list();
		List<Employee> employeeList = new ArrayList<Employee>();
		for (String name : nameList) {
			Employee employee = new Employee();
			employee.setName(name);
			employeeList.add(employee);
		}
		return employeeList;
	}

}
