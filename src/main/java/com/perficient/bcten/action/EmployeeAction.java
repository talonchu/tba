package com.perficient.bcten.action;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import com.perficient.bcten.entity.Relation;
import com.perficient.bcten.entity.Request;
import com.perficient.bcten.model.MarkedEmployee;
import com.perficient.bcten.model.ReEditInfo;
import com.perficient.bcten.service.EmployeeService;
import com.perficient.bcten.service.ReEditInfoService;

public class EmployeeAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = -8413890939717028377L;
	private Map<String, Object> session;
	private EmployeeService employeeService;
	private ReEditInfoService reEditInfoService;
	private String activeTime;
	private Request requestInfo;

	private List<MarkedEmployee> selectedMarkedEmployees;
	private List<MarkedEmployee> unSelectedMarkedEmployees;

	public ReEditInfoService getReEditInfoService() {
		return reEditInfoService;
	}

	public void setReEditInfoService(ReEditInfoService reEditInfoService) {
		this.reEditInfoService = reEditInfoService;
	}

	public Request getRequestInfo() {
		return requestInfo;
	}

	public void setRequestInfo(Request requestInfo) {
		this.requestInfo = requestInfo;
	}

	public String getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(String activeTime) {
		this.activeTime = activeTime;
	}

	public Map<String, Object> getSession() {
		return this.session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public List<MarkedEmployee> getSelectedMarkedEmployees() {
		return selectedMarkedEmployees;
	}

	public void setSelectedMarkedEmployees(List<MarkedEmployee> selectedMarkedEmployees) {
		this.selectedMarkedEmployees = selectedMarkedEmployees;
	}

	public List<MarkedEmployee> getUnSelectedMarkedEmployees() {
		return unSelectedMarkedEmployees;
	}

	public void setUnSelectedMarkedEmployees(List<MarkedEmployee> unSelectedMarkedEmployees) {
		this.unSelectedMarkedEmployees = unSelectedMarkedEmployees;
	}

	public String reEdit() {
		ReEditInfo reEditInfo = reEditInfoService.getReEditInfo(requestInfo.getId());
		if (activeTime == null || activeTime.length() <= 0) {
			activeTime = reEditInfo.getTeamBuildingTime().toString();
		}
		Date teamBuildingTime = Date.valueOf(activeTime);
		setRequsetInfoFromReEditInfo(reEditInfo);
		List<Relation> relationList = reEditInfo.getRelationList();
		List<MarkedEmployee> markedEmployeeList = employeeService.getMarkedEmployees(teamBuildingTime);

		Map<String, List<MarkedEmployee>> markedEmployeeListMap = reEditInfoService.cutMarkedList(markedEmployeeList,
				relationList);

		setSelectedMarkedEmployees(markedEmployeeListMap.get("selectedMarkedEmployees"));
		setUnSelectedMarkedEmployees(markedEmployeeListMap.get("unSelectedMarkedEmployees"));
		if (hasPermissionViewRequest(reEditInfo))
			return SUCCESS;
		return ERROR;
	}

	private boolean hasPermissionViewRequest(ReEditInfo reEditInfo) {
		Integer role = (Integer) session.get("role");
		boolean isAdmin = (role == 1 || role == 2);
		String username = (String) session.get("name");
		boolean isParticipator = false;
		boolean isRequester = username.equalsIgnoreCase(requestInfo.getRequesterName());
		for (Relation relation : reEditInfo.getRelationList()) {
			if (relation.getEmployeeName().equalsIgnoreCase(username)) {
				isParticipator = true;
			}
		}

		return isAdmin || isRequester || isParticipator;
	}

	public String requestDetail() {
		if (session.get("detailRequestId") != null) {
			requestInfo = new Request();
			requestInfo.setId((Integer) session.get("detailRequestId"));
			session.remove("detailRequestId");
		}
		return reEdit();
	}

	public String getMarkedEmployees() {
		Date teamBuildingTime;
		if (activeTime == null || activeTime.length() <= 0) {
			teamBuildingTime = new Date(System.currentTimeMillis());
			activeTime = teamBuildingTime.toString();
		} else {
			teamBuildingTime = Date.valueOf(activeTime);
		}
		List<MarkedEmployee> markedEmployeeList = getEmployeeService().getMarkedEmployees(teamBuildingTime);
		session.remove("markedEmployeeList");
		session.put("markedEmployeeList", markedEmployeeList);
		return SUCCESS;
	}

	private void setRequsetInfoFromReEditInfo(ReEditInfo reEditInfo) {
		if (requestInfo.getActivity() == null) {
			requestInfo.setActivity(reEditInfo.getActivity());
		}
		if (requestInfo.getPurpose() == null) {
			requestInfo.setPurpose(reEditInfo.getPurpose());
		}
		requestInfo.setApprover(reEditInfo.getApprover());
		requestInfo.setRequestStatus(reEditInfo.getRequestStatus());
		requestInfo.setApprovedTime(reEditInfo.getApprovedTime());
		requestInfo.setRequesterName(reEditInfo.getRequesterName());
		requestInfo.setTotalNumber(reEditInfo.getTotalNumber());
		requestInfo.setTotalCost(reEditInfo.getTotalCost());
	}

}
