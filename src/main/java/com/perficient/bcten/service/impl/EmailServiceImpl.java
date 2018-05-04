package com.perficient.bcten.service.impl;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.perficient.bcten.entity.EmailInfo;
import com.perficient.bcten.entity.Request;
import com.perficient.bcten.service.EmailService;
import com.perficient.bcten.service.RequestListService;
import com.perficient.bcten.util.MyAuthenticator;
import com.perficient.bcten.util.VerifyUtil;
import com.perficient.bcten.util.log4j.LogProvider;

public class EmailServiceImpl implements EmailService {
	private EmailInfo emailInfo;
	private Session session;
	private Properties properties;
	private RequestListService requestListService;

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public RequestListService getRequestListService() {
		return requestListService;
	}

	public void setRequestListService(RequestListService requestListService) {
		this.requestListService = requestListService;
	}

	public Properties getProperties() {
		return properties;
	}

	public EmailInfo getEmailInfo() {
		return emailInfo;
	}

	public void setEmailInfo(EmailInfo emailInfo) {
		this.emailInfo = emailInfo;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public void setDetail(String toAddress, String subject, String content) {
		emailInfo.setToAddress(toAddress);
		emailInfo.setSubject(subject);
		emailInfo.setContent(content);
	}

	public boolean send() {
		MyAuthenticator authenticator = null;
		authenticator = new MyAuthenticator(emailInfo.getUsername(), emailInfo.getPassword());
		if (session == null) {
			session = Session.getDefaultInstance(this.getProperties(), authenticator);
		}
		try {
			MimeMessage mailMessage = new MimeMessage(session);
			Address from = new InternetAddress(emailInfo.getFromAddress());
			mailMessage.setFrom(from);
			Address to = new InternetAddress(emailInfo.getToAddress());
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			mailMessage.setSubject(emailInfo.getSubject());
			mailMessage.setSentDate(new Date());

			MimeMultipart mainPart = new MimeMultipart();

			BodyPart html = new MimeBodyPart();
			html.setContent(emailInfo.getContent(), "text/html; charset=utf-8");
			mainPart.addBodyPart(html);
			mailMessage.setContent(mainPart);

			if (mailMessage != null) {
				Transport.send(mailMessage);
			}
			return true;
		} catch (MessagingException ex) {
			LogProvider.error(EmailServiceImpl.class.getName(), ex);
		}
		return false;
	}

	public boolean setResponseEmailInfo(int requestId) {
		Request request = requestListService.getRequestById(requestId);
		if (request != null) {
			String dateBuf = request.getTeamBuildingTime().toString();
			String defaultToAddress = emailInfo.getDefaultToAddress();
			String toAddress = defaultToAddress.replace("toAddress", request.getRequesterName());
			String requestStatus = "";
			String defaultContent = emailInfo.getDefaultContent();
			String defaultSubject = emailInfo.getDefaultSubject();

			String content = defaultContent.replace("requesterName", request.getRequesterName());
			String requestIdString = VerifyUtil.getInstance().getCode(request.getId());
			content = content.replaceAll("requestIdString", requestIdString);
			switch (request.getRequestStatus()) {
			case APPROVED:
				requestStatus = "Approved";
				content = content.replace("Etiquette", "Congratulations");
				content = content.replace("responseReason", "");
				break;
			case DISAPPROVED:
				requestStatus = "Rejected";
				String responseReason = "The reason is :  " + request.getRejectReason() + ".";
				content = content.replace("Etiquette", "Sorry");
				content = content.replace("responseReason", responseReason);
				break;
			default:
				break;
			}
			String subject = defaultSubject.replace("requestStatus", requestStatus);
			content = content.replace("buildingDate", dateBuf);
			content = content.replace("requestStatus", requestStatus);
			setDetail(toAddress, subject, content);
		}
		return true;
	}
}
