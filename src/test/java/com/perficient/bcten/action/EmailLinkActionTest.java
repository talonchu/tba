package com.perficient.bcten.action;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.Test;

import com.perficient.bcten.entity.Request;
import com.perficient.bcten.model.ReEditInfo;
import com.perficient.bcten.service.ReEditInfoService;
import com.perficient.bcten.util.Status;
import com.perficient.bcten.util.VerifyUtil;

public class EmailLinkActionTest extends EasyMockSupport {

	private ReEditInfoService reEditInfoService = createMock(ReEditInfoService.class);

	@Test
	public void isLoginTest1() {

		EmailLinkAction emailLinkAction = new EmailLinkAction();
		String requestId = "OGI4NjVlODEtMzFiNS00NmZhLTg0NjYtOWVkNWUzY2Q5MjQ3LTEzMDY1 ";
		Map<String, Object> session = new HashMap<String, Object>();
		session.put("name", "sdfsda");
		Request requestInfo = new Request();
		emailLinkAction.setSession(session);
		emailLinkAction.setRequestInfo(requestInfo);
		session.put("requestId", requestId);
		emailLinkAction.getSession();

		emailLinkAction.getRequestInfo();
		ReEditInfo reEditInfo = new ReEditInfo();
		reEditInfo.setRequestStatus(Status.APPROVED);
		emailLinkAction.setReEditInfoService(reEditInfoService);
		EasyMock.expect(reEditInfoService.getReEditInfo(Integer.parseInt(VerifyUtil.getInstance().getID(requestId))))
				.andReturn(reEditInfo);
		EasyMock.replay(reEditInfoService);
		assertEquals("toDetail", emailLinkAction.isLogin());

	}

	@Test
	public void isLoginTest2() {

		EmailLinkAction emailLinkAction = new EmailLinkAction();
		String requestId = "OGI4NjVlODEtMzFiNS00NmZhLTg0NjYtOWVkNWUzY2Q5MjQ3LTEzMDY1 ";
		Map<String, Object> session = new HashMap<String, Object>();
		session.put("name", "sdfsda");
		session.put("role", 1);
		Request requestInfo = new Request();
		emailLinkAction.setSession(session);
		emailLinkAction.setRequestInfo(requestInfo);
		emailLinkAction.getSession();
		session.put("requestId", requestId);
		emailLinkAction.getRequestInfo();
		ReEditInfo reEditInfo = new ReEditInfo();
		reEditInfo.setRequestStatus(Status.DISAPPROVED);
		reEditInfo.setRequesterName("sdfsda");
		emailLinkAction.setReEditInfoService(reEditInfoService);
		emailLinkAction.getReEditInfoService();
		EasyMock.expect(reEditInfoService.getReEditInfo(Integer.parseInt(VerifyUtil.getInstance().getID(requestId))))
				.andReturn(reEditInfo);
		EasyMock.replay(reEditInfoService);
		assertEquals("toEdit", emailLinkAction.isLogin());

	}

	@Test
	public void isLoginTest3() {

		EmailLinkAction emailLinkAction = new EmailLinkAction();
		String requestId = "OGI4NjVlODEtMzFiNS00NmZhLTg0NjYtOWVkNWUzY2Q5MjQ3LTEzMDY1 ";
		Map<String, Object> session = new HashMap<String, Object>();
		session.put("name", "sdfsda");
		Request requestInfo = new Request();
		emailLinkAction.setSession(session);
		emailLinkAction.setRequestInfo(requestInfo);
		emailLinkAction.getSession();
		session.put("requestId", requestId);
		emailLinkAction.getRequestInfo();
		ReEditInfo reEditInfo = new ReEditInfo();
		reEditInfo.setRequestStatus(Status.DISAPPROVED);
		reEditInfo.setRequesterName("sdfsda");
		emailLinkAction.setReEditInfoService(reEditInfoService);
		emailLinkAction.getReEditInfoService();
		EasyMock.expect(reEditInfoService.getReEditInfo(Integer.parseInt(VerifyUtil.getInstance().getID(requestId))))
				.andReturn(reEditInfo);
		EasyMock.replay(reEditInfoService);
		assertEquals("toEdit", emailLinkAction.isLogin());

	}

}
