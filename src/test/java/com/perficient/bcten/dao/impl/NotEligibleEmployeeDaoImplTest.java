package com.perficient.bcten.dao.impl;

import java.sql.Date;
import java.util.List;

import com.perficient.bcten.dao.NotEligibleEmployeeDao;
import com.perficient.bcten.entity.Employee;

;

public class NotEligibleEmployeeDaoImplTest extends DaoTestCase {

	private NotEligibleEmployeeDao notEligibleEmployeeDao;

	protected void onSetUpInTransaction() throws Exception {
		super.onSetUpInTransaction();
		notEligibleEmployeeDao = (NotEligibleEmployeeDao) this.applicationContext.getBean("notEligibleEmployeeDAO");
	}

	protected void onTearDownInTransaction() {
		notEligibleEmployeeDao = null;
	}

	public void testSaveSuccess() {
		jdbcTemplate
				.update("insert into request (teamBuildingTime,requestTime,requestStatus) values ('2001-01-01','2000-01-01',0)");
		int requestId = jdbcTemplate.queryForInt("select distinct LAST_INSERT_ID() from request");
		jdbcTemplate.update("insert into relation (employeeName,requestId) values ('Test','" + requestId + "')");
		List<Employee> list = notEligibleEmployeeDao.findNotEligibleEmployees(Date.valueOf("2001-01-02"));
		Employee employee = new Employee();
		;
		for (Employee e : list) {
			if (e.getName().equals("Test")) {
				employee = e;
				break;
			}
		}

		String name = (String) jdbcTemplate.queryForObject(
				"select Employee.employeeName name  from relation Employee left join request rq "
						+ "on(Employee.requestid=rq.id) where rq.teamBuildingTime > DATE_FORMAT(DATE_SUB(str_to_date('"
						+ "2001-01-02" + "','%Y-%m-%d') , INTERVAL 60 DAY) , '" + "%Y%m%d') and Employee.requestId=?",
				new Object[] { requestId }, String.class);
		assertEquals(employee.getName(), name);
	}

}
