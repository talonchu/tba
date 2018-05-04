package com.perficient.bcten.dao.impl;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.easymock.EasyMock;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import com.perficient.bcten.dao.RequestDao;
import com.perficient.bcten.entity.Request;
import com.perficient.bcten.model.MarkedRequest;
import com.perficient.bcten.util.SqlProvider;
import com.perficient.bcten.util.Status;

public class RequestDaoImplTest extends DaoTestCase {

	private RequestDao requestDao;
	private Request request;

	protected void onSetUpInTransaction() throws Exception {
		super.onSetUpInTransaction();
		requestDao = (RequestDao) this.applicationContext.getBean("requestDao");
	}

	protected void onTearDownInTransaction() {
		requestDao = null;
	}

	public void testSaveSuccess() { // author Daniel
		Request request = new Request();
		request.setPurpose("YES");
		request.setRequestTime(Date.valueOf("2000-01-01"));
		request.setTeamBuildingTime(Date.valueOf("2000-01-01"));
		requestDao.save(request);
		request.setApprovedTime(Date.valueOf("2012-01-01"));
		requestDao.save(request);
		String name = (String) jdbcTemplate.queryForObject("select purpose from request where id=?",
				new Object[] { request.getId() }, String.class);
		assertEquals(request.getPurpose(), name);
	}

	public void testDeleteSuccess() { // author Daniel
		Request request = new Request();
		request.setPurpose("YES");
		request.setRequestTime(Date.valueOf("2000-01-01"));
		request.setTeamBuildingTime(Date.valueOf("2000-01-01"));
		requestDao.save(request);
		requestDao.delete(request);
		int i = jdbcTemplate.queryForList("select purpose from request where purpose=?",
				new Object[] { request.getId() }, String.class).size();
		assertEquals(0, i);
	}

	/*---------author Jeffery ---------------*/
	public void testApproveRequest() {
		jdbcTemplate
				.update("insert into request (teamBuildingTime,requestTime,requestStatus) values ('2000-01-01','2000-01-01',0)");
		int id = jdbcTemplate.queryForInt("select distinct LAST_INSERT_ID() from request");
		request = new Request();
		request.setRequestStatus(Status.APPROVED);
		request.setId(id);
		request.setRequestTime(Date.valueOf("2000-01-01"));
		request.setTeamBuildingTime(Date.valueOf("2000-01-01"));
		request.setApprovedTime(Date.valueOf("2012-02-02"));
		request.setApprover("stone.zhang");
		requestDao.approveRequest(request);
		flushCurrentSession();
		Integer requestStatus = (Integer) jdbcTemplate.queryForObject("select requestStatus from request where id=?",
				new Object[] { id }, Integer.class);
		String approver = (String) jdbcTemplate.queryForObject("select approver from request where id=?",
				new Object[] { id }, String.class);
		Date approvedTime = (Date) jdbcTemplate.queryForObject("select approvedTime from request where id=?",
				new Object[] { id }, Date.class);
		assertEquals(new Integer("1"), requestStatus);
		assertEquals("stone.zhang", approver);
		assertEquals(Date.valueOf("2012-02-02"), approvedTime);
	}

	public void testApproveRequestFail() {
		assertEquals(false, requestDao.approveRequest(request));
	}

	public void testRejectRequest() {
		jdbcTemplate
				.update("insert into request (requestStatus,rejectReason,teamBuildingTime,requestTime) values (0,'test','2000-01-01','2000-01-01')");
		int id = jdbcTemplate.queryForInt("select distinct LAST_INSERT_ID() from request");
		request = new Request();
		request.setId(id);
		request.setRequestStatus(Status.WAITING);
		request.setRejectReason("test");
		request.setRequestTime(Date.valueOf("2000-01-01"));
		request.setTeamBuildingTime(Date.valueOf("2000-01-01"));
		requestDao.rejectRequest(request);
		flushCurrentSession();
		String rejectReason = (String) jdbcTemplate.queryForObject("select rejectReason from request where id=?",
				new Object[] { request.getId() }, String.class);

		Integer requestStatus = (Integer) jdbcTemplate.queryForObject("select requestStatus from request where id=?",
				new Object[] { request.getId() }, Integer.class);

		assertEquals(new Integer(2), requestStatus);
		assertEquals("test", rejectReason);
	}

	public void testRejectRequestFail() {
		assertEquals(false, requestDao.rejectRequest(request));
	}

	/*---------author shadow & jeffery ---------------*/
	public void testGetAllRequestList() {
		jdbcTemplate
				.update("insert into request (requesterName,purpose,activity,totalNumber,totalCost,requestStatus,rejectReason,teamBuildingTime,requestTime) values ('hunter.huang','inow','b-ball',3,270,0,'test','2000-01-01','1888-01-01')");
		SqlProvider sqlProvider = new SqlProvider();
		Map<String, String> fields = new HashMap<String, String>();
		RequestDaoImpl requestDaoImpl = (RequestDaoImpl) requestDao;
		requestDaoImpl.setSqlProvider(sqlProvider);
		String username = "hunter.huang";
		int offset = 0;
		int pageSize = 8;
		flushCurrentSession();
		List<MarkedRequest> testList = requestDao.getAllRequestList(fields, username, offset, pageSize, 0,
				"requestTime", 0);
		assertEquals(true, testList.size() >= 1);
	}

	public void testGetPersonalRequestList() {
		jdbcTemplate
				.update("insert into request (requesterName,purpose,activity,totalNumber,totalCost,requestStatus,rejectReason,teamBuildingTime,requestTime) values ('shadow','inow','b-ball',3,270,0,'test','2000-01-01','1888-01-01')");
		SqlProvider sqlProvider = new SqlProvider();
		Map<String, String> fields = new HashMap<String, String>();
		RequestDaoImpl requestDaoImpl = (RequestDaoImpl) requestDao;
		requestDaoImpl.setSqlProvider(sqlProvider);
		String username = "shadow";
		int offset = 0;
		int pageSize = 8;
		List<MarkedRequest> testList = requestDao.getPersonalRequestList(fields, username, offset, pageSize, 0,
				"requestTime", 0);
		assertEquals(true, testList.size() >= 1);
	}

	public void testGetRequestById() {
		jdbcTemplate
				.update("insert into request (requesterName,purpose,activity,totalNumber,totalCost,requestStatus,rejectReason,teamBuildingTime,requestTime) values ('shadow','inow','b-ball',3,270,0,'test','2000-01-01','1888-01-01')");
		int id = jdbcTemplate.queryForInt("select distinct LAST_INSERT_ID() from request");
		request = requestDao.getRequestById(id);
		assertEquals("shadow", request.getRequesterName());
	}

	public void testGetAllRow() {
		int allRow = requestDao.getAllRow(0);
		System.out.println(allRow);
		jdbcTemplate
				.update("insert into request (requesterName,purpose,activity,totalNumber,totalCost,requestStatus,rejectReason,teamBuildingTime,requestTime) values ('shadow','inow','b-ball',3,270,0,'test','2000-01-01','1888-01-01')");
		assertEquals(allRow + 1, requestDao.getAllRow(0));
	}

	public void testGetPersonalRow() {
		String username = "test";
		int personalRow = requestDao.getPersonalRow(username, 0);
		jdbcTemplate
				.update("insert into request (requesterName,purpose,activity,totalNumber,totalCost,requestStatus,rejectReason,teamBuildingTime,requestTime) values ('test','inow','b-ball',3,270,0,'test','2000-01-01','1888-01-01')");
		assertEquals(personalRow + 1, requestDao.getPersonalRow(username, 0));
	}

	public void testSetGetSqlProvider() {
		RequestDaoImpl requestDaoImpl = (RequestDaoImpl) requestDao;
		SqlProvider sqlProvider = new SqlProvider();
		requestDaoImpl.setSqlProvider(sqlProvider);
		assertEquals(sqlProvider, requestDaoImpl.getSqlProvider());
	}

	/*---------author stone.zhang ---------------*/
	public void testRepealApproved() {
		jdbcTemplate
				.update("insert into request (requestStatus,approver,approvedTime) values (1,'stone.zhang','2011-01-01')");
		int id = jdbcTemplate.queryForInt("select distinct LAST_INSERT_ID() from request");
		request = new Request();
		request = requestDao.getRequestById(id);
		request.setRequestStatus(Status.WAITING);
		request.setApprover(null);
		request.setApprovedTime(null);
		boolean b = requestDao.repealApproved(request);
		request = requestDao.getRequestById(id);
		flushCurrentSession();
		Integer requestStatus = (Integer) jdbcTemplate.queryForObject("select requestStatus from request where id =?",
				new Object[] { id }, Integer.class);
		assertEquals(true, b);
		assertEquals(new Integer(0), requestStatus);
		assertEquals(null, request.getApprover());
		assertEquals(null, request.getApprovedTime());
	}

	public void testRepealApprovedFail() {
		assertEquals(false, requestDao.repealApproved(request));
	}

	public void testUpdate() {
		jdbcTemplate
				.update("insert into request (requesterName,totalCost,teamBuildingTime) values ('stone',270,'2000-01-01')");
		int id = jdbcTemplate.queryForInt("select distinct LAST_INSERT_ID() from request");
		request = new Request();
		request.setId(id);
		request.setPurpose("test");
		boolean b = requestDao.update(request);
		assertEquals(true, b);
	}

	public void testUpdateFail() {
		assertEquals(false, requestDao.update(request));
	}

	// to flush current session
	protected SessionFactory getSessionFactory() {
		return (SessionFactory) getApplicationContext().getBean("sessionFactory");
	}

	protected void flushCurrentSession() {
		Session session = SessionFactoryUtils.getSession(getSessionFactory(), false);
		if (session != null) {
			session.flush();
		}
	}

}
