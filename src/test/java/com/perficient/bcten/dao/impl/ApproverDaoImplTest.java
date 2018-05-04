package com.perficient.bcten.dao.impl;

import java.util.List;

import com.perficient.bcten.dao.ApproverDao;
import com.perficient.bcten.entity.Permission;

public class ApproverDaoImplTest extends DaoTestCase {

	private ApproverDao approverDao;

	protected void onSetUpInTransaction() throws Exception {
		super.onSetUpInTransaction();
		approverDao = (ApproverDao) this.applicationContext.getBean("approverDao");
	}

	protected void onTearDownInTransaction() {
		approverDao = null;
	}

	public void testFindApprovers() {
		List<Permission> list = approverDao.findApprovers();
		Permission permission = list.get(0);
		String name = (String) jdbcTemplate.queryForObject("select employeeName from permission where employeeName=?",
				new Object[] { permission.getEmployeeName() }, String.class);
		assertEquals(permission.getEmployeeName(), name);
	}
}
