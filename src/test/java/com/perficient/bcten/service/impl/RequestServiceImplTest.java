package com.perficient.bcten.service.impl;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.Test;

import com.perficient.bcten.dao.RelationDao;
import com.perficient.bcten.dao.RequestDao;
import com.perficient.bcten.dao.impl.RelationDaoImpl;
import com.perficient.bcten.dao.impl.RequestDaoImpl;
import com.perficient.bcten.entity.Request;
import com.perficient.bcten.service.impl.RequestServiceImpl;

public class RequestServiceImplTest extends EasyMockSupport {

	@Test
	public void SaveSuccess() {
		RequestServiceImpl requestServiceImpl = new RequestServiceImpl();

		RequestDao requestDaoImpl = createMock(RequestDaoImpl.class);
		Request request = initRequest("go hiding");
		requestServiceImpl.setRequestDao(requestDaoImpl);
		requestServiceImpl.getRequestDao();
		requestServiceImpl.getRequestDao().save(request);
		EasyMock.expectLastCall();
		EasyMock.replay(requestDaoImpl);
		requestServiceImpl.save(request);
		EasyMock.verify(requestDaoImpl);
	}

	@Test
	public void DeleteSuccess() {
		RequestServiceImpl requestServiceImpl = new RequestServiceImpl();

		RequestDao requestDaoImpl = createMock(RequestDaoImpl.class);
		Request request = initRequest("go hiding");
		requestServiceImpl.setRequestDao(requestDaoImpl);
		requestServiceImpl.getRequestDao();
		requestServiceImpl.getRequestDao().delete(request);
		EasyMock.expectLastCall();
		EasyMock.replay(requestDaoImpl);
		requestServiceImpl.delete(request);
		EasyMock.verify(requestDaoImpl);
	}

	@Test
	public void UpdateSuccess() {
		Request request = initRequest("go hiding");
		request.setId(1);
		RequestServiceImpl requestServiceImpl = new RequestServiceImpl();
		RelationDao relationDao = createMock(RelationDaoImpl.class);
		RequestDao requestDaoImpl = createMock(RequestDaoImpl.class);
		EasyMock.expect(relationDao.delete(request.getId())).andReturn(true);
		EasyMock.expect(requestDaoImpl.update(request)).andReturn(true);
		EasyMock.replay(requestDaoImpl);
		EasyMock.replay(relationDao);
		requestServiceImpl.setRelationDao(relationDao);
		requestServiceImpl.setRequestDao(requestDaoImpl);

		assertEquals(true, requestServiceImpl.update(request));
	}

	@Test
	public void UpdateFail1() {
		Request request = initRequest("go hiding");
		request.setId(1);
		RequestServiceImpl requestServiceImpl = new RequestServiceImpl();
		RelationDao relationDao = createMock(RelationDaoImpl.class);
		RequestDao requestDaoImpl = createMock(RequestDaoImpl.class);
		EasyMock.expect(relationDao.delete(request.getId())).andReturn(true);
		EasyMock.expect(requestDaoImpl.update(request)).andReturn(false);
		EasyMock.replay(requestDaoImpl);
		EasyMock.replay(relationDao);
		requestServiceImpl.setRelationDao(relationDao);
		requestServiceImpl.setRequestDao(requestDaoImpl);

		assertEquals(false, requestServiceImpl.update(request));
	}

	public void UpdateFail2() {
		Request request = initRequest("go hiding");
		request.setId(1);
		RequestServiceImpl requestServiceImpl = new RequestServiceImpl();
		RelationDao relationDao = createMock(RelationDaoImpl.class);
		RequestDao requestDaoImpl = createMock(RequestDaoImpl.class);
		EasyMock.expect(relationDao.delete(request.getId())).andReturn(false);
		EasyMock.expect(requestDaoImpl.update(request)).andReturn(true);
		EasyMock.replay(requestDaoImpl);
		EasyMock.replay(relationDao);
		requestServiceImpl.setRelationDao(relationDao);
		requestServiceImpl.setRequestDao(requestDaoImpl);

		assertEquals(false, requestServiceImpl.update(request));
	}

	public Request initRequest(String purpose) {
		Request request = new Request();
		request.setPurpose(purpose);
		return request;
	}

	@Test
	public void test() {
		RequestServiceImpl requestServiceImpl = new RequestServiceImpl();
		RelationDaoImpl rdi = new RelationDaoImpl();
		requestServiceImpl.setRelationDao(rdi);
		assertEquals(rdi, requestServiceImpl.getRelationDao());
	}
}
