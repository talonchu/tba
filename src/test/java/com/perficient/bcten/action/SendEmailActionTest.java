package com.perficient.bcten.action;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.Test;

import com.perficient.bcten.entity.Permission;
import com.perficient.bcten.entity.Request;
import com.perficient.bcten.service.ApproverService;
import com.perficient.bcten.service.EmailService;
import com.perficient.bcten.util.VerifyUtil;

public class SendEmailActionTest extends EasyMockSupport {
	private EmailService emailService = createMock(EmailService.class);
	private ApproverService approverService = createMock(ApproverService.class);

	@Test
	public void sendSuccessTest() {
		VerifyUtil verifUtil = VerifyUtil.getInstance();
		verifUtil.setRandomString("c12f5323-1fc8-4166-b09f-e9247129c84c");
		List<Permission> approver = new ArrayList<Permission>();
		Permission permission = initpermission();
		approver.add(permission);
		Request request = initRequest();
		SendEmailAction sendEmail = new SendEmailAction();
		sendEmail.setRequest(request);
		sendEmail.getRequest();
		sendEmail.setApproverService(approverService);
		sendEmail.getApproverService();
		sendEmail.setEmailService(emailService);
		sendEmail.getEmailService();

		sendEmail.setApproveEmailInfo(permission.getEmployeeName());
		EasyMock.expect(approverService.getAllPermission()).andReturn(approver);
		EasyMock.expect(emailService.send()).andReturn(true);
		EasyMock.replay(emailService);
		EasyMock.replay(approverService);
		assertEquals("success", sendEmail.send());

	}

	@Test
	public void sendErrorTest() {
		List<Permission> approver = new ArrayList<Permission>();
		Permission permission = initpermission();
		approver.add(permission);
		Request request = initRequest();
		SendEmailAction sendEmail = new SendEmailAction();
		sendEmail.setRequest(request);
		sendEmail.getRequest();
		sendEmail.setApproverService(approverService);
		sendEmail.getApproverService();
		sendEmail.setEmailService(emailService);
		sendEmail.getEmailService();

		sendEmail.setApproveEmailInfo(permission.getEmployeeName());
		EasyMock.expect(approverService.getAllPermission()).andReturn(approver);
		EasyMock.expect(emailService.send()).andReturn(false);
		EasyMock.replay(emailService);
		EasyMock.replay(approverService);
		assertEquals("error", sendEmail.send());

	}

	public Request initRequest() {
		Request request = new Request();
		request.setId(1);
		request.setRequesterName("Dan");
		request.setTeamBuildingTime(new Date(2009));
		return request;

	}

	public Permission initpermission() {
		Permission permission = new Permission();
		permission.setEmployeeName("Hello");
		return permission;

	}

}
