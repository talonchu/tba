package com.perficient.bcten.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.Test;

import com.perficient.bcten.dao.RequestDao;
import com.perficient.bcten.entity.Request;
import com.perficient.bcten.model.MarkedRequest;
import com.perficient.bcten.util.Page;

public class RequestListServeImplTest extends EasyMockSupport {
	private RequestDao requestDao = EasyMock.createMock(RequestDao.class);
	private RequestListServiceImpl requestListServiceImpl = new RequestListServiceImpl();

	@Test
	public void setAndGetTest() {
		requestListServiceImpl.setRequestDao(requestDao);
		assertEquals(requestListServiceImpl.getRequestDao(), requestDao);
	}

	@Test
	public void getAllRequestList() {
		List<MarkedRequest> requestList = new ArrayList<MarkedRequest>();
		EasyMock.expect(
				requestDao.getAllRequestList(new HashMap<String, String>(), "jack.ye", 0, 8, 0, "requestTime", 0))
				.andReturn(requestList);
		EasyMock.replay(requestDao);
		requestListServiceImpl.setRequestDao(requestDao);
		assertEquals(requestList, requestListServiceImpl.getAllRequestList(new HashMap<String, String>(), "jack.ye", 1,
				0, "requestTime", 0));
	}

	@Test
	public void approveRequestTest() {
		Request req = new Request();
		EasyMock.expect(requestDao.approveRequest(req)).andReturn(true);
		EasyMock.replay(requestDao);
		requestListServiceImpl.setRequestDao(requestDao);
		assertEquals(true, requestListServiceImpl.approveRequest(req));
	}

	@Test
	public void testRepealApproved() {
		Request req = new Request();
		EasyMock.expect(requestDao.repealApproved(req)).andReturn(true);
		EasyMock.replay(requestDao);
		requestListServiceImpl.setRequestDao(requestDao);
		assertEquals(true, requestListServiceImpl.repealApproved(req));
	}

	@Test
	public void getRequestByIdTest() {
		Request req = new Request();
		EasyMock.expect(requestDao.getRequestById(1)).andReturn(req);
		EasyMock.replay(requestDao);
		requestListServiceImpl.setRequestDao(requestDao);
		assertEquals(req, requestListServiceImpl.getRequestById(1));
	}

	@Test
	public void getTotalPageTestWithRole1() {
		EasyMock.expect(requestDao.getAllRow(0)).andReturn(8);
		EasyMock.replay(requestDao);
		Page page = new Page();
		int totalPage = page.countTotalPage(8, 8);
		requestListServiceImpl.setRequestDao(requestDao);
		assertEquals(totalPage, requestListServiceImpl.getTotalPage(1, "shadow.zhu", 0));
	}

	@Test
	public void getTotalPageTestWithRole3() {
		EasyMock.expect(requestDao.getAllRow(0)).andReturn(8);
		EasyMock.replay(requestDao);
		Page page = new Page();
		int totalPage = page.countTotalPage(8, 8);
		requestListServiceImpl.setRequestDao(requestDao);
		assertEquals(totalPage, requestListServiceImpl.getTotalPage(2, "shadow.zhu", 0));
	}

	@Test
	public void getTotalPageTestWithRole4() {
		EasyMock.expect(requestDao.getPersonalRow("shadow.zhu", 0)).andReturn(8);
		EasyMock.replay(requestDao);
		Page page = new Page();
		int totalPage = page.countTotalPage(8, 8);
		requestListServiceImpl.setRequestDao(requestDao);
		assertEquals(totalPage, requestListServiceImpl.getTotalPage(3, "shadow.zhu", 0));
	}

	@Test
	public void getTotalPageTestWithRole5() {
		EasyMock.expect(requestDao.getPersonalRow("shadow.zhu", 0)).andReturn(8);
		EasyMock.replay(requestDao);
		Page page = new Page();
		int totalPage = page.countTotalPage(8, 8);
		requestListServiceImpl.setRequestDao(requestDao);
		assertEquals(totalPage, requestListServiceImpl.getTotalPage(null, "shadow.zhu", 0));
	}

	@Test
	public void getTotalPageTestWithRole2() {
		EasyMock.expect(requestDao.getAllRow(1)).andReturn(8);
		EasyMock.replay(requestDao);
		Page page = new Page();
		int totalPage = page.countTotalPage(8, 8);
		requestListServiceImpl.setRequestDao(requestDao);
		assertEquals(totalPage, requestListServiceImpl.getTotalPage(1, "shadow.zhu", 1));
	}

	@Test
	public void getTotalPageTestWithoutRoleOne() {
		EasyMock.expect(requestDao.getPersonalRow("jack.ye", 0)).andReturn(8);
		EasyMock.replay(requestDao);
		Page page = new Page();
		int totalPage = page.countTotalPage(8, 8);
		requestListServiceImpl.setRequestDao(requestDao);
		assertEquals(totalPage, requestListServiceImpl.getTotalPage(0, "jack.ye", 0));
	}

	@Test
	public void getTotalPageTestWithoutRoleTwo() {
		EasyMock.expect(requestDao.getPersonalRow("jack.ye", 1)).andReturn(8);
		EasyMock.replay(requestDao);
		Page page = new Page();
		int totalPage = page.countTotalPage(8, 8);
		requestListServiceImpl.setRequestDao(requestDao);
		assertEquals(totalPage, requestListServiceImpl.getTotalPage(0, "jack.ye", 1));
	}

	@Test
	public void testRejectRequest() {
		Request req = new Request();
		EasyMock.expect(requestDao.rejectRequest(req)).andReturn(true);
		EasyMock.replay(requestDao);
		requestListServiceImpl.setRequestDao(requestDao);
		assertEquals(true, requestListServiceImpl.rejectRequest(req));
	}

	@Test
	public void testGetPersonalRequestListWithStatusOne() {
		List<MarkedRequest> list = new ArrayList<MarkedRequest>();
		Page page = new Page();
		int curPage = 1;
		int offset = page.countOffset(8, curPage);
		EasyMock.expect(
				requestDao.getPersonalRequestList(new HashMap<String, String>(), "shadow.zhu", offset, 8, 0,
						"requestTime", 0)).andReturn(list);
		EasyMock.expect(requestDao);
		requestListServiceImpl.setRequestDao(requestDao);
		assertEquals(null, requestListServiceImpl.getPersonalRequestList(new HashMap<String, String>(), "shadow.zhu",
				curPage, 0, "requestTime", 0));
	}

	@Test
	public void testGetPersonalRequestListWithStatusTwo() {
		List<MarkedRequest> list = new ArrayList<MarkedRequest>();
		Page page = new Page();
		int curPage = 1;
		int offset = page.countOffset(8, curPage);
		EasyMock.expect(
				requestDao.getPersonalRequestList(new HashMap<String, String>(), "suri.lv", offset, 8, 1,
						"requestTime", 0)).andReturn(list);
		EasyMock.expect(requestDao);
		requestListServiceImpl.setRequestDao(requestDao);
		assertEquals(null, requestListServiceImpl.getPersonalRequestList(new HashMap<String, String>(), "suri.lv",
				curPage, 1, "requestTime", 0));
	}

	@Test
	public void testGetPersonalRequestListWithSortStatusOne() {
		List<MarkedRequest> list = new ArrayList<MarkedRequest>();
		Page page = new Page();
		int curPage = 1;
		int offset = page.countOffset(8, curPage);
		EasyMock.expect(
				requestDao.getPersonalRequestList(new HashMap<String, String>(), "suri.lv", offset, 8, 1,
						"requestTime", 1)).andReturn(list);
		EasyMock.expect(requestDao);
		requestListServiceImpl.setRequestDao(requestDao);
		assertEquals(null, requestListServiceImpl.getPersonalRequestList(new HashMap<String, String>(), "suri.lv",
				curPage, 1, "requestTime", 1));
	}

	@Test
	public void testGetPersonalRequestListWithColumnOne() {
		List<MarkedRequest> list = new ArrayList<MarkedRequest>();
		Page page = new Page();
		int curPage = 1;
		int offset = page.countOffset(8, curPage);
		EasyMock.expect(
				requestDao.getPersonalRequestList(new HashMap<String, String>(), "suri.lv", offset, 8, 1,
						"totalParticipants", 1)).andReturn(list);
		EasyMock.expect(requestDao);
		requestListServiceImpl.setRequestDao(requestDao);
		assertEquals(null, requestListServiceImpl.getPersonalRequestList(new HashMap<String, String>(), "suri.lv",
				curPage, 1, "totalParticipants", 1));
	}

	@Test
	public void testGetPersonalRequestListWithColumnTwo() {
		List<MarkedRequest> list = new ArrayList<MarkedRequest>();
		Page page = new Page();
		int curPage = 1;
		int offset = page.countOffset(8, curPage);
		EasyMock.expect(
				requestDao.getPersonalRequestList(new HashMap<String, String>(), "suri.lv", offset, 8, 1,
						"maximumAmount", 1)).andReturn(list);
		EasyMock.expect(requestDao);
		requestListServiceImpl.setRequestDao(requestDao);
		assertEquals(null, requestListServiceImpl.getPersonalRequestList(new HashMap<String, String>(), "suri.lv",
				curPage, 1, "maximumAmount", 1));
	}

	@Test
	public void testGetPersonalRequestListWithColumnThree() {
		List<MarkedRequest> list = new ArrayList<MarkedRequest>();
		Page page = new Page();
		int curPage = 1;
		int offset = page.countOffset(8, curPage);
		EasyMock.expect(
				requestDao.getPersonalRequestList(new HashMap<String, String>(), "suri.lv", offset, 8, 1,
						"teamBuildingTime", 1)).andReturn(list);
		EasyMock.expect(requestDao);
		requestListServiceImpl.setRequestDao(requestDao);
		assertEquals(null, requestListServiceImpl.getPersonalRequestList(new HashMap<String, String>(), "suri.lv",
				curPage, 1, "teamBuildingTime", 1));
	}
}
