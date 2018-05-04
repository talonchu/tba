package com.perficient.bcten.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.perficient.bcten.entity.Permission;
import com.perficient.bcten.entity.Request;
import com.perficient.bcten.service.ApproverService;
import com.perficient.bcten.service.EmailService;
import com.perficient.bcten.util.VerifyUtil;

public class SendEmailAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private ApproverService approverService;
	private Request request;
	private EmailService emailService;
	private Map<String, Object> session;
	private String hosturl;

	public String getHosturl() {
		return hosturl;
	}

	public void setHosturl(String hosturl) {
		this.hosturl = hosturl;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public ApproverService getApproverService() {
		return approverService;
	}

	public void setApproverService(ApproverService approverService) {
		this.approverService = approverService;
	}

	public EmailService getEmailService() {
		return emailService;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	public boolean setApproveEmailInfo(String name) {
		String requestId = VerifyUtil.getInstance().getCode(request.getId());
		String toAddress = name + "@perficient.com";
		String subject = "New Team Building Request ";
		String content = "<body><p style='font-size:18px'>Hi " + name + ":</p>"
				+ "<p style='font-size:16px'>Please check <span style='font-weight:bold'>" + request.getRequesterName()
				+ "</span>" + "'s new team building request. It will be held on <span style='font-weight:bold'>"
				+ request.getTeamBuildingTime() + "</span>." + "</p>" + "<a href='http://" + hosturl
				+ "/emailLinkAction?requestId=" + requestId + "'>" + "http://" + hosturl
				+ "/emailLinkAction?requestId=" + requestId + "</a>" + "</body>";
		emailService.setDetail(toAddress, subject, content);
		return true;
	}

	public String send() {
		List<Permission> approver;
		approver = (List<Permission>) approverService.getAllPermission();
		int j = 0;
		for (int i = 0; i < approver.size(); i++) {

			setApproveEmailInfo(approver.get(i).getEmployeeName());
			if (!emailService.send()) {
				j++;
			}

		}
		if (j > 0) {
			return ERROR;
		} else

		{
			return SUCCESS;
		}
	}

}
