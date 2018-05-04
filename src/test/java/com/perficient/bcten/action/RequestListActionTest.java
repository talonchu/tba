package com.perficient.bcten.action;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.Test;

import com.perficient.bcten.entity.Permission;
import com.perficient.bcten.entity.Request;
import com.perficient.bcten.model.MarkedRequest;
import com.perficient.bcten.service.ApproverService;
import com.perficient.bcten.service.EmailService;
import com.perficient.bcten.service.RequestListService;
import com.perficient.bcten.service.impl.EmailServiceImpl;
import com.perficient.bcten.service.impl.RequestListServiceImpl;
import com.perficient.bcten.util.Page;
import com.perficient.bcten.util.Status;
import com.perficient.bcten.util.VerifyUtil;

public class RequestListActionTest extends EasyMockSupport {

	private RequestListAction requestListAction;
	private RequestListService requestListService = createMock(RequestListServiceImpl.class);
	private EmailService emailService = createMock(EmailServiceImpl.class);
	private ApproverService approverService = createMock(ApproverService.class);

	public Permission initpermission() {
		Permission permission = new Permission();
		permission.setEmployeeName("Hello");
		permission.setRole(2);
		return permission;

	}

	@Test
	public void testInitSuccessBranch1() throws Exception {
		Map<String, Object> session = new HashMap<String, Object>();
		session.put("name", "soloman.wang");
		session.put("role", 1);
		requestListAction = new RequestListAction();
		requestListAction.setSession(session);
		List<MarkedRequest> list = new ArrayList<MarkedRequest>();

		Page page = new Page();
		requestListAction.setPage(page);
		requestListAction.setRequestListService(requestListService);
		requestListAction.setColumn("requestTime");

		EasyMock.expect(
				requestListService.getAllRequestList(new HashMap<String, String>(), (String) requestListAction
						.getSession().get("name"), 1, requestListAction.getStatus(), requestListAction.getColumn(),
						requestListAction.getSortStatus())).andReturn(list);
		EasyMock.expect(requestListService.getTotalPage(1, "soloman.wang", 0)).andReturn(2);
		EasyMock.replay(requestListService);

		assertEquals("success", requestListAction.init());

	}

	@Test
	public void testInitSuccessBranch2() throws Exception {
		Map<String, Object> session = new HashMap<String, Object>();
		session.put("name", "hunter.huang");
		session.put("role", 2);
		requestListAction = new RequestListAction();
		requestListAction.setSession(session);
		List<MarkedRequest> list = new ArrayList<MarkedRequest>();

		Page page = new Page();
		requestListAction.setPage(page);
		requestListAction.setRequestListService(requestListService);
		requestListAction.setColumn("requestTime");

		EasyMock.expect(
				requestListService.getAllRequestList(new HashMap<String, String>(), (String) requestListAction
						.getSession().get("name"), 1, requestListAction.getStatus(), requestListAction.getColumn(),
						requestListAction.getSortStatus())).andReturn(list);
		EasyMock.expect(
				requestListService.getTotalPage((Integer) requestListAction.getSession().get("role"), "hunter.huang",
						requestListAction.getStatus())).andReturn(2);
		EasyMock.replay(requestListService);
		assertEquals("success", requestListAction.init());
	}

	@Test
	public void testInitSuccessBranch3() throws Exception {
		Map<String, Object> session = new HashMap<String, Object>();
		session.put("name", "hunter.huang");
		session.put("role", 3);
		requestListAction = new RequestListAction();
		requestListAction.setSession(session);
		List<MarkedRequest> list = new ArrayList<MarkedRequest>();

		Page page = new Page();
		requestListAction.setPage(page);
		requestListAction.setRequestListService(requestListService);
		requestListAction.setColumn("requestTime");

		EasyMock.expect(
				requestListService.getPersonalRequestList(new HashMap<String, String>(), (String) requestListAction
						.getSession().get("name"), 1, requestListAction.getStatus(), requestListAction.getColumn(),
						requestListAction.getSortStatus())).andReturn(list);
		EasyMock.expect(
				requestListService.getTotalPage((Integer) requestListAction.getSession().get("role"), "hunter.huang",
						requestListAction.getStatus())).andReturn(2);
		EasyMock.replay(requestListService);
		assertEquals("success", requestListAction.init());
	}

	@Test
	public void testInitSuccessBranch4() throws Exception {
		Map<String, Object> session = new HashMap<String, Object>();
		session.put("name", "hunter.huang");
		session.put("role", 1);
		requestListAction = new RequestListAction();
		requestListAction.setSession(session);
		List<MarkedRequest> list = new ArrayList<MarkedRequest>();

		Page page = new Page();
		requestListAction.setPage(page);
		requestListAction.setRequestListService(requestListService);
		requestListAction.setColumn("requestTime");

		EasyMock.expect(
				requestListService.getAllRequestList(new HashMap<String, String>(), (String) requestListAction
						.getSession().get("name"), 1, requestListAction.getStatus(), requestListAction.getColumn(),
						requestListAction.getSortStatus())).andReturn(list);
		EasyMock.expect(
				requestListService.getTotalPage((Integer) requestListAction.getSession().get("role"), "hunter.huang",
						requestListAction.getStatus())).andReturn(2);
		EasyMock.replay(requestListService);
		assertEquals("success", requestListAction.init());
	}

	@Test
	public void testInitSuccessBranch5() throws Exception {
		Map<String, Object> session = new HashMap<String, Object>();
		session.put("name", "hunter.huang");
		session.put("role", 1);
		requestListAction = new RequestListAction();
		requestListAction.setSession(session);
		List<MarkedRequest> list = new ArrayList<MarkedRequest>();

		requestListAction.setPage(null);
		requestListAction.setRequestListService(requestListService);
		requestListAction.setColumn("requestTime");

		EasyMock.expect(
				requestListService.getAllRequestList(new HashMap<String, String>(), (String) requestListAction
						.getSession().get("name"), 1, requestListAction.getStatus(), requestListAction.getColumn(),
						requestListAction.getSortStatus())).andReturn(list);
		EasyMock.expect(
				requestListService.getTotalPage((Integer) requestListAction.getSession().get("role"), "hunter.huang",
						requestListAction.getStatus())).andReturn(2);
		EasyMock.replay(requestListService);
		assertEquals("success", requestListAction.init());
	}

	public void testApproveBranch(boolean appBlo1, boolean appBlo2, boolean appBlo3, boolean appBlo4, String returnType) {
		VerifyUtil verifUtil = VerifyUtil.getInstance();
		verifUtil.setRandomString("a12f5323-1fc8-4166-b09f-e9247129c84c");
		requestListAction = new RequestListAction();
		requestListAction.setHosturl("host");
		Map<String, Object> session = new HashMap<String, Object>();
		session.put("name", "stone.zhang");
		Request request = new Request();
		request.setRequestStatus(Status.APPROVED);
		request.setApprovedTime(Date.valueOf("2012-02-02"));
		request.setId(1);
		// String requesId="";
		request.setRequesterName("xia");
		request.setTeamBuildingTime(Date.valueOf("2012-02-01"));
		// requestListAction.setRequestId(requesId);
		requestListAction.setRequest(request);
		requestListAction.getRequest();
		requestListAction.setEmailService(emailService);
		requestListAction.getEmailService();
		// requestListAction.setRequestId(requestId);
		requestListAction.setApproverService(approverService);
		requestListAction.getApproverService();
		List<Permission> approver = new ArrayList<Permission>();
		Permission permission = initpermission();
		approver.add(permission);
		requestListAction.setFinanceEmailInfo(permission.getEmployeeName());

		requestListAction.setSession(session);
		EasyMock.expect(requestListService.approveRequest(requestListAction.getRequest())).andReturn(appBlo1);
		EasyMock.expect(emailService.send()).andReturn(appBlo2);
		// EasyMock.expect(VerifyUtil.getCode(EasyMock.anyInt())).andReturn("OGI4NjVlODEtMzFiNS00NmZhLTg0NjYtOWVkNWUzY2Q5MjQ3LTEzMDY1 ");
		EasyMock.expect(requestListService.getRequestById(1)).andReturn(request);
		EasyMock.expect(emailService.send()).andReturn(appBlo3);
		EasyMock.expect(emailService.setResponseEmailInfo(1)).andReturn(appBlo4);
		EasyMock.expect(approverService.getAllPermission()).andReturn(approver);
		EasyMock.replay(approverService);
		EasyMock.replay(emailService);
		EasyMock.replay(requestListService);

		// EasyMock.replay(emailService);
		requestListAction.setRequestId("1");
		requestListAction.setRequestListService(requestListService);
		requestListAction.setEmailService(emailService);
		assertEquals(returnType, requestListAction.approve());
	}

	@Test
	public void testApproveBranch1() {
		testApproveBranch(true, true, true, true, "success");
	}

	@Test
	public void testApproveBranch2() {
		testApproveBranch(true, true, false, true, "error");
	}

	@Test
	public void testApproveBranch3() {
		testApproveBranch(false, true, true, true, "error");
	}

	@Test
	public void testApproveListSetAndGet() {
		requestListAction = new RequestListAction();
		List<MarkedRequest> approveList = new ArrayList<MarkedRequest>();
		requestListAction.setApproveList(approveList);
		assertEquals(requestListAction.getApproveList(), approveList);
	}

	public void testRejectBranch(boolean returnBlo1, boolean returnBlo2, String returnType) {
		requestListAction = new RequestListAction();
		EasyMock.expect(requestListService.rejectRequest(requestListAction.getRequest())).andReturn(returnBlo1);
		EasyMock.expect(emailService.send()).andReturn(returnBlo2);
		EasyMock.expect(requestListService.getRequestById(1)).andReturn(null);
		EasyMock.expect(emailService.setResponseEmailInfo(1)).andReturn(true);
		EasyMock.replay(requestListService);
		EasyMock.replay(emailService);
		requestListAction.setRequestId("1");
		requestListAction.setRejectReason("test");
		requestListAction.setRequestListService(requestListService);
		requestListAction.setEmailService(emailService);
		assertEquals(returnType, requestListAction.reject());
	}

	@Test
	public void testReject() {
		testRejectBranch(true, true, "success");
	}

	@Test
	public void testRejectBranch1() {
		testRejectBranch(false, true, "error");

	}

	@Test
	public void testRejectBranch2() {
		testRejectBranch(true, false, "error");
	}

	@Test
	public void testRepealSuccess() {
		requestListAction = new RequestListAction();
		EasyMock.expect(requestListService.repealApproved(requestListAction.getRequest())).andReturn(true);
		EasyMock.replay(requestListService);
		requestListAction.setRequestId("1");
		requestListAction.setRequestListService(requestListService);
		assertEquals("success", requestListAction.repeal());
	}

	@Test
	public void testRepealError() {
		requestListAction = new RequestListAction();
		EasyMock.expect(requestListService.repealApproved(requestListAction.getRequest())).andReturn(false);
		EasyMock.replay(requestListService);
		requestListAction.setRequestId("1");
		requestListAction.setRequestListService(requestListService);
		assertEquals("error", requestListAction.repeal());
	}

	@Test
	public void testGetRejectReason() {
		requestListAction = new RequestListAction();
		requestListAction.setRejectReason("test");
		assertEquals("test", requestListAction.getRejectReason());
	}

	@Test
	public void testGetSetMsg() {
		String msg = "";
		requestListAction = new RequestListAction();
		requestListAction.setMsg(msg);
		assertEquals(msg, requestListAction.getMsg());
	}

	@Test
	public void testGetSetPage() {
		Page page = new Page();
		requestListAction = new RequestListAction();
		requestListAction.setPage(page);
		assertEquals(page, requestListAction.getPage());
	}

	@Test
	public void testSetAndGet() {
		requestListAction = new RequestListAction();
		requestListAction.setRequestListService(requestListService);
		assertEquals(requestListAction.getRequestListService(), requestListService);
	}

	@Test
	public void testEmailServiceSetAndGet() {
		requestListAction = new RequestListAction();
		requestListAction.setEmailService(emailService);
		assertEquals(requestListAction.getEmailService(), emailService);
	}

	@Test
	public void testGetSetSession() {
		requestListAction = new RequestListAction();
		Map<String, Object> session = new HashMap<String, Object>();
		requestListAction.setSession(session);
		assertEquals(session, requestListAction.getSession());
	}

	@Test
	public void testGetSetRequest() {
		Request request = new Request();
		requestListAction = new RequestListAction();
		requestListAction.setRequest(request);
		assertEquals(request, requestListAction.getRequest());
	}

	@Test
	public void testSetRequest() {
		requestListAction = new RequestListAction();
		requestListAction.setRequest(null);
		assertEquals(true, requestListAction.getRequest() != null);
	}

	@Test
	public void testGetSetRequestId() {
		requestListAction = new RequestListAction();
		requestListAction.setRequestId("1");
		assertEquals("1", requestListAction.getRequestId());
	}

	/*
	 * private int status; private String column = ""; private int sortStatus =
	 * 0;
	 */

	@Test
	public void testGetSetStatus() {
		requestListAction = new RequestListAction();
		requestListAction.setStatus(0);
		assertEquals(0, requestListAction.getStatus());
	}

	@Test
	public void testGetSetColumn() {
		requestListAction = new RequestListAction();
		requestListAction.setColumn("requestTime");
		assertEquals("requestTime", requestListAction.getColumn());
	}

	@Test
	public void testGetSetSortStatus() {
		requestListAction = new RequestListAction();
		requestListAction.setSortStatus(1);
		assertEquals(1, requestListAction.getSortStatus());
	}

	@Test
	public void testGetSetHosturl() {
		requestListAction = new RequestListAction();
		requestListAction.setHosturl("test");
		assertEquals("test", requestListAction.getHosturl());
	}

}