package com.perficient.bcten.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.perficient.bcten.entity.Request;
import com.perficient.bcten.model.ReEditInfo;
import com.perficient.bcten.service.ReEditInfoService;
import com.perficient.bcten.util.VerifyUtil;

public class EmailLinkAction extends ActionSupport implements SessionAware {
	private ReEditInfoService reEditInfoService;
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Request requestInfo;

	public Request getRequestInfo() {
		return requestInfo;
	}

	public void setRequestInfo(Request requestInfo) {
		this.requestInfo = requestInfo;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public ReEditInfoService getReEditInfoService() {
		return reEditInfoService;
	}

	public void setReEditInfoService(ReEditInfoService reEditInfoService) {
		this.reEditInfoService = reEditInfoService;
	}

	public String isLogin() {
		requestInfo = new Request();
		String requestId = (String) session.get("requestId");
		requestInfo.setId(Integer.parseInt(VerifyUtil.getInstance().getID(requestId)));
		ReEditInfo reEditInfo = null;
		try {
			reEditInfo = reEditInfoService.getReEditInfo(requestInfo.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		} finally {
			session.remove("requestId");
		}
		if (reEditInfo.getRequestStatus().toString().equals("DISAPPROVED")
				&& reEditInfo.getRequesterName().equalsIgnoreCase((String) session.get("name"))) {

			return "toEdit";

		}
		return "toDetail";

	}

}
