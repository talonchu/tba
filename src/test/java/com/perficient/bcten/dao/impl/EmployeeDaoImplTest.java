package com.perficient.bcten.dao.impl;

import java.util.List;

import com.perficient.bcten.dao.EmployeeDao;
import com.perficient.bcten.entity.Employee;

public class EmployeeDaoImplTest extends RemoteDaoTestCase {

	private EmployeeDao employeeDAO;

	protected void onSetUpInTransaction() throws Exception {
		super.onSetUpInTransaction();
		employeeDAO = (EmployeeDao) this.applicationContext.getBean("employeeDAO");
	}

	protected void onTearDownInTransaction() {
		employeeDAO = null;
	}

	public void testFindPermissions() {
		List<Employee> list = employeeDAO.findEmlpoyees();
		Employee employee = list.get(0);
		String name = (String) jdbcTemplate.queryForObject("select name from employee_info_view where name=?",
				new Object[] { employee.getName() }, String.class);
		assertEquals(employee.getName(), name);
	}

}
