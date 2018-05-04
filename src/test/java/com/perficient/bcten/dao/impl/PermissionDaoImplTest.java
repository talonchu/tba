package com.perficient.bcten.dao.impl;

import com.perficient.bcten.dao.PermissionDao;

public class PermissionDaoImplTest extends DaoTestCase {

	private PermissionDao permissionDAO;

	protected void onSetUpInTransaction() throws Exception {
		super.onSetUpInTransaction();
		permissionDAO = (PermissionDao) this.applicationContext.getBean("permissionDAO");
	}

	protected void onTearDownInTransaction() {
		permissionDAO = null;
	}

	public void testIlliglePermissions0() {
		int flag = permissionDAO.validatePermission("outman");
		assertEquals(0, flag);
	}

	public void testCorrectPermissions1() {
		jdbcTemplate.update("insert into permission (employeeName) values ('test.name')");
		int flag = permissionDAO.validatePermission("test.name");
		assertEquals(1, flag);
	}

	public void testCorrectPermissions2() {
		jdbcTemplate.update("insert into permission (employeeName,role) values ('spuerman',2)");
		int flag = permissionDAO.validatePermission("spuerman");
		assertEquals(2, flag);
	}

}
