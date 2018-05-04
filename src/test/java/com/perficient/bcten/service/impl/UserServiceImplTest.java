package com.perficient.bcten.service.impl;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.Test;

import com.perficient.bcten.dao.PermissionDao;
import com.perficient.bcten.service.impl.UserServiceImpl;

public class UserServiceImplTest extends EasyMockSupport {
	private UserServiceImpl UserServiceImpl;
	private PermissionDao permissionDao = createMock(PermissionDao.class);

	@Test
	public void testAdmin() {
		UserServiceImpl = new UserServiceImpl();
		EasyMock.expect(permissionDao.validatePermission("tony.deng")).andReturn(1);
		EasyMock.replay(permissionDao);
		UserServiceImpl.setPermissionDao(permissionDao);
		UserServiceImpl.getPermissionDao();
		assertEquals(1, UserServiceImpl.getRole("tony.deng"));
	}

	@Test
	public void testRequest() {
		UserServiceImpl = new UserServiceImpl();
		EasyMock.expect(permissionDao.validatePermission("shadow.zhu")).andReturn(0);
		EasyMock.replay(permissionDao);
		UserServiceImpl.setPermissionDao(permissionDao);
		assertEquals(0, UserServiceImpl.getRole("shadow.zhu"));
	}

	@Test
	public void testFinance() {
		UserServiceImpl = new UserServiceImpl();
		EasyMock.expect(permissionDao.validatePermission("shadow.zhu")).andReturn(2);
		EasyMock.replay(permissionDao);
		UserServiceImpl.setPermissionDao(permissionDao);
		assertEquals(2, UserServiceImpl.getRole("shadow.zhu"));
	}
}
