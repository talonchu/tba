package com.perficient.bcten.service.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.Test;

import com.perficient.bcten.dao.ApproverDao;
import com.perficient.bcten.entity.Permission;

public class ApproverServiceImplTest extends EasyMockSupport {

	private ApproverDao approverDao = createMock(ApproverDao.class);
	private List<Permission> mockPermissions;
	private ApproverServiceImpl approverServiceImpl;

	private static List<Permission> createPermissionList() {
		Permission permission = new Permission();
		permission.setEmployeeName("you");

		List<Permission> mockPermissionList = new ArrayList<Permission>();
		mockPermissionList.add(permission);
		return mockPermissionList;
	}

	@Test
	public void getAllPermissionTest() {
		approverServiceImpl = new ApproverServiceImpl();
		mockPermissions = createPermissionList();

		EasyMock.expect(approverDao.findApprovers()).andReturn(mockPermissions);
		EasyMock.replay(approverDao);
		approverServiceImpl.setApproverDao(approverDao);
		approverServiceImpl.getApproverDao();

		assertEquals(mockPermissions, approverServiceImpl.getAllPermission());
	}
}
