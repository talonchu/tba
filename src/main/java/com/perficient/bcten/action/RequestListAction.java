package com.perficient.bcten.action;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.perficient.bcten.entity.Permission;
import com.perficient.bcten.entity.Request;
import com.perficient.bcten.model.MarkedRequest;
import com.perficient.bcten.service.ApproverService;
import com.perficient.bcten.service.EmailService;
import com.perficient.bcten.service.RequestListService;
import com.perficient.bcten.service.UserService;
import com.perficient.bcten.util.Page;
import com.perficient.bcten.util.VerifyUtil;

public class RequestListAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = -467929083019579327L;
	private UserService userService;
	private EmailService emailService;
	private RequestListService requestListService;
	private List<MarkedRequest> approveList;
	private String requestId;
	private Request request;
	private Map<String, Object> session;
	private String rejectReason;
	private String msg;
	private ApproverService approverService;
	private String hosturl;

	private int status;
	private String column = "";
	private int sortStatus = 0;

	public int getSortStatus() {
		return sortStatus;
	}

	public void setSortStatus(int sortStatus) {
		this.sortStatus = sortStatus;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public int getStatus() {
		return status;
	}

	public String getHosturl() {
		return hosturl;
	}

	public void setHosturl(String hosturl) {
		this.hosturl = hosturl;
	}

	/*---------get$set-----------*/
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	private Page page;

	public void setRequest(Request request) {
		if (request != null) {
			this.request = request;
		} else {
			this.request = new Request();
		}
	}

	public Request getRequest() {
		if (request == null) {
			this.request = new Request();
		}
		return request;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public RequestListService getRequestListService() {
		return requestListService;
	}

	public void setRequestListService(RequestListService requestListService) {
		this.requestListService = requestListService;
	}

	public List<MarkedRequest> getApproveList() {
		return approveList;
	}

	public void setApproveList(List<MarkedRequest> approveList) {
		this.approveList = approveList;
	}

	public EmailService getEmailService() {
		return emailService;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public ApproverService getApproverService() {
		return approverService;
	}

	public void setApproverService(ApproverService approverService) {
		this.approverService = approverService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public boolean setFinanceEmailInfo(String name) {
		String requestIdString = VerifyUtil.getInstance().getCode(request.getId());

		String toAddress = name + "@perficient.com";
		String subject = "Team Building Request is Approved";
		String content = "<body><p style='font-size:18px'>Hi " + name + ":</p>"
				+ "<p style='font-size:16px'> <span style='font-weight:bold'>" + request.getRequesterName() + "</span>"
				+ "'s new team building request is Approved. Totalcost: <span style='font-weight:bold'>"
				+ request.getTotalCost() + "</span>." + "</p>"

				+ "<a href='http://" + hosturl + "/tba/emailLinkAction?requestId=" + requestIdString + "'>" + "http://"
				+ hosturl + "/tba/emailLinkAction?requestId=" + requestIdString + "</a>" + "</body>";
		emailService.setDetail(toAddress, subject, content);
		return true;
	}

	/*--------init----------*/
	public String init() {

		Map<String, String> map = new HashMap<String, String>();
		String name = (String) session.get("name");
		Integer role = (Integer) session.get("role");

		if (page == null) {
			page = new Page();
		}
		if (page.getTotalPage() <= 0) {
			page.setTotalPage(requestListService.getTotalPage(role, name, status));
		}

		page.countCurPage(page.getCurPage());

		if (role != null && (role == 1 || role == 2)) {
			approveList = requestListService
					.getAllRequestList(map, name, page.getCurPage(), status, column, sortStatus);
		} else {
			approveList = requestListService.getPersonalRequestList(map, name, page.getCurPage(), status, column,
					sortStatus);
		}

		return SUCCESS;
	}

	/*----------send e-mail -------------*/
	public String approve() {
		setRequest(requestListService.getRequestById(Integer.parseInt(requestId)));
		getRequest().setId(Integer.parseInt(requestId));
		getRequest().setApprover((String) session.get("name"));
		getRequest().setApprovedTime(new Date(System.currentTimeMillis()));
		List<Permission> approver = (List<Permission>) approverService.getAllPermission();
		boolean isApproved = requestListService.approveRequest(getRequest());
		if (isApproved) {

			for (int i = 0; i < approver.size(); i++) {
				if (approver.get(i).getRole() == 2) {
					setFinanceEmailInfo(approver.get(i).getEmployeeName());
					emailService.send();
				}
			}
			emailService.setResponseEmailInfo(Integer.parseInt(requestId));
			boolean isSent = emailService.send();

			if (isSent) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return ERROR;
		}
	}

	public String reject() {
		getRequest().setId(Integer.parseInt(requestId));
		getRequest().setRejectReason(rejectReason);
		boolean isReject = requestListService.rejectRequest(getRequest());
		if (isReject) {
			emailService.setResponseEmailInfo(Integer.parseInt(requestId));
			boolean isSent = emailService.send();
			if (isSent) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return ERROR;
		}
	}

	public String repeal() {
		getRequest().setId(Integer.parseInt(requestId));
		boolean isRepealed = requestListService.repealApproved(request);
		if (isRepealed) {
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
