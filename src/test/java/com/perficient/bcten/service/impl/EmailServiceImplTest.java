package com.perficient.bcten.service.impl;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.Test;

import com.perficient.bcten.entity.EmailInfo;
import com.perficient.bcten.entity.Request;
import com.perficient.bcten.service.RequestListService;
import com.perficient.bcten.util.Status;

public class EmailServiceImplTest extends EasyMockSupport {
	EmailServiceImpl emailServiceImpl = new EmailServiceImpl();
	Properties properties = new Properties();
	Session sendMailSession = Session.getInstance(properties);
	EmailInfo emailInfo = new EmailInfo();
	RequestListService requestListService = createMock(RequestListServiceImpl.class);

	@Test
	public void setGetEmailInfo() {
		emailServiceImpl.setEmailInfo(emailInfo);
		assertEquals(emailInfo, emailServiceImpl.getEmailInfo());
	}

	@Test
	public void setGetRequestListService() {
		emailServiceImpl.setRequestListService(requestListService);
		assertEquals(requestListService, emailServiceImpl.getRequestListService());
	}

	@Test
	public void setGetProperties() {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "10.2.1.10");
		properties.put("mail.smtp.port", "25");
		properties.put("mail.smtp.auth", "true");
		emailServiceImpl.setProperties(properties);
		assertEquals(properties, emailServiceImpl.getProperties());
	}

	@Test
	public void setAndGetSession() {
		emailServiceImpl.setSession(sendMailSession);
		assertEquals(sendMailSession, emailServiceImpl.getSession());
	}

	@Test
	public void sendTest() throws MessagingException {
		Properties properties = new Properties();
		emailInfo.setContent("testContent");
		emailInfo.setFromAddress("fromAddress");
		emailInfo.setPassword("root");
		emailInfo.setUsername("root");
		emailInfo.setSubject("subject");
		emailInfo.setToAddress("toAddress");
		emailServiceImpl.setProperties(properties);
		emailServiceImpl.setEmailInfo(emailInfo);
		emailServiceImpl.setSession(sendMailSession);
		assertEquals(false, emailServiceImpl.send());
	}

	@Test
	public void sendTestWithoutSession() throws MessagingException {
		sendMailSession = null;
		Properties properties = new Properties();
		emailInfo.setContent("testContent");
		emailInfo.setFromAddress("fromAddress");
		emailInfo.setPassword("root");
		emailInfo.setUsername("root");
		emailInfo.setSubject("subject");
		emailInfo.setToAddress("toAddress");
		emailServiceImpl.setProperties(properties);
		emailServiceImpl.setEmailInfo(emailInfo);
		emailServiceImpl.setSession(sendMailSession);
		assertEquals(false, emailServiceImpl.send());
	}

	@Test
	public void setApproveDetailTest() {
		emailServiceImpl.setEmailInfo(emailInfo);
		emailServiceImpl.setDetail("aa", "bb", "cc");
		assertEquals("aa", emailServiceImpl.getEmailInfo().getToAddress());
		assertEquals("bb", emailServiceImpl.getEmailInfo().getSubject());
		assertEquals("cc", emailServiceImpl.getEmailInfo().getContent());

	}

	@Test
	public void setApproveEmailInfoTest() {
		Request request = new Request();
		emailInfo.setToAddress("test");
		emailInfo.setSubject("test");
		emailInfo.setContent("test");
		emailInfo.setDefaultContent("DefaultContent");
		emailInfo.setDefaultSubject("DefaultSubject");
		emailInfo.setDefaultToAddress("DefaultToAddress");
		request.setId(1);
		request.setRequesterName("vorce.shen");
		request.setTeamBuildingTime(new Date());
		request.setRequestStatus(Status.APPROVED);
		EasyMock.expect(requestListService.getRequestById(1)).andReturn(request);
		EasyMock.replay(requestListService);
		emailServiceImpl.setEmailInfo(emailInfo);
		emailServiceImpl.setRequestListService(requestListService);
		assertEquals(true, emailServiceImpl.setResponseEmailInfo(1));
	}

	@Test
	public void setRejectEmailInfoTest() {
		Request request = new Request();
		emailInfo.setToAddress("some.one");
		emailInfo.setSubject("Reject");
		emailInfo.setContent("Sorry");
		emailInfo.setDefaultContent("DefaultContent");
		emailInfo.setDefaultSubject("DefaultSubject");
		emailInfo.setDefaultToAddress("DefaultToAddress");
		request.setId(1);
		request.setRequesterName("leo.li");
		request.setTeamBuildingTime(new Date());
		request.setRequestStatus(Status.DISAPPROVED);
		EasyMock.expect(requestListService.getRequestById(1)).andReturn(request);
		EasyMock.replay(requestListService);
		emailServiceImpl.setEmailInfo(emailInfo);
		emailServiceImpl.setRequestListService(requestListService);
		assertEquals(true, emailServiceImpl.setResponseEmailInfo(1));
	}
}
