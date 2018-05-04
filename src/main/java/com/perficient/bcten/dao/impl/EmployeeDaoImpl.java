package com.perficient.bcten.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.perficient.bcten.dao.EmployeeDao;
import com.perficient.bcten.entity.Employee;

public class EmployeeDaoImpl extends HibernateDaoSupport implements EmployeeDao {

	@SuppressWarnings("unchecked")
	public List<Employee> findEmlpoyees() {
		String hql = "from Employee ";
		return getHibernateTemplate().find(hql);
	}
}
