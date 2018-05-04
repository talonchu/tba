package com.perficient.bcten.service.impl;

import java.util.List;

import com.perficient.bcten.dao.ApproverDao;
import com.perficient.bcten.entity.Permission;
import com.perficient.bcten.service.ApproverService;

public class ApproverServiceImpl implements ApproverService {
	private ApproverDao approverDao;

	public ApproverDao getApproverDao() {
		return approverDao;
	}

	public void setApproverDao(ApproverDao approverDao) {
		this.approverDao = approverDao;
	}

	public List<Permission> getAllPermission() {
		return approverDao.findApprovers();
	}

}
