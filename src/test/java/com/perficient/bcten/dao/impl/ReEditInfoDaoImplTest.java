package com.perficient.bcten.dao.impl;

import java.sql.Date;

import com.perficient.bcten.dao.ReEditInfoDao;
import com.perficient.bcten.dao.RelationDao;
import com.perficient.bcten.dao.RequestDao;
import com.perficient.bcten.entity.Relation;
import com.perficient.bcten.entity.Request;

import com.perficient.bcten.model.ReEditInfo;
import com.perficient.bcten.util.Status;

public class ReEditInfoDaoImplTest extends DaoTestCase {

	private ReEditInfoDao reEditInfoDao;
	private RequestDao requestDao;
	private RelationDao relationDao;

	protected void onSetUpInTransaction() throws Exception {
		super.onSetUpInTransaction();
		reEditInfoDao = (ReEditInfoDao) this.applicationContext.getBean("reEditInfoDAO");
		requestDao = (RequestDao) this.applicationContext.getBean("requestDao");
		relationDao = (RelationDao) this.applicationContext.getBean("relationDAO");
	}

	protected void onTearDownInTransaction() {
		reEditInfoDao = null;
		requestDao = null;
		relationDao = null;
	}

	public void testFindSuccess() {
		Request request = new Request();
		request.setActivity("test");
		request.setPurpose("test");
		request.setRequestTime(Date.valueOf("2001-01-01"));
		request.setTeamBuildingTime(Date.valueOf("2001-01-01"));
		request.setRequestStatus(Status.APPROVED);
		request.setApprovedTime(Date.valueOf("2012-01-01"));
		request.setApprover("stone.zhang");
		request.setTotalCost(1.0);
		request.setTotalNumber(1);
		requestDao.save(request);
		Relation relation = new Relation();
		relation.setEmployeeName("test");
		relation.setRequestId(request.getId());
		relationDao.save(relation);
		ReEditInfo reEditInfo = reEditInfoDao.findReEditInfoByRequestId(request.getId());
		String activity = (String) jdbcTemplate.queryForObject("select activity from request where id=?",
				new Object[] { request.getId() }, String.class);
		assertEquals(reEditInfo.getActivity(), activity);
	}

}
