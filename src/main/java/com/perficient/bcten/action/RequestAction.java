package com.perficient.bcten.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.perficient.bcten.entity.Employee;
import com.perficient.bcten.entity.Request;
import com.perficient.bcten.service.NotEligibleEmployeeService;
import com.perficient.bcten.service.RelationSerivce;
import com.perficient.bcten.service.RequestService;
import com.perficient.bcten.util.Status;

public class RequestAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 2699437141706513443L;
	private RequestService requestService;
	private Request request;
	private String activeTime;
	private String[] selectedArr;
	private RelationSerivce relationService;
	private Map<String, Object> session;
	private double percost;
	private NotEligibleEmployeeService notEligibleEmployeeService;

	public NotEligibleEmployeeService getNotEligibleEmployeeService() {
		return notEligibleEmployeeService;
	}

	public void setNotEligibleEmployeeService(NotEligibleEmployeeService notEligibleEmployeeService) {
		this.notEligibleEmployeeService = notEligibleEmployeeService;
	}

	public double getPercost() {
		return percost;
	}

	public void setPercost(double percost) {
		this.percost = percost;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String[] getSelectedArr() {
		if (this.selectedArr == null) {
			return new String[] {};
		} else {
			return (String[]) selectedArr.clone();
		}
	}

	public void setSelectedArr(String[] selectedArrTemp) {
		this.selectedArr = new String[selectedArrTemp.length];
		System.arraycopy(selectedArrTemp, 0, this.selectedArr, 0, selectedArr.length);
	}

	public String getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(String activeTime) {
		this.activeTime = activeTime;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public RequestService getRequestService() {
		return requestService;
	}

	public void setRequestService(RequestService requestService) {
		this.requestService = requestService;
	}

	public RelationSerivce getRelationService() {
		return relationService;
	}

	public void setRelationService(RelationSerivce relationService) {
		this.relationService = relationService;
	}

	/* submit */
	public String submit() {
		Date date = Date.valueOf(this.getActiveTime());
		request.setRequestStatus(Status.WAITING);
		request.setTeamBuildingTime(date);
		request.setTotalNumber(selectedArr.length);
		request.setTotalCost(selectedArr.length * percost);
		request.setRequestTime(new Date(System.currentTimeMillis()));
		request.setRequesterName((String) session.get("name"));
		requestService.save(request);
		if (request.getId() == null) {
			return ERROR;
		} else {
			if (isNotEligible(date)) {
				requestService.delete(request);
				return ERROR;
			}
			relationService.saveRelation(request.getId(), getSelectedArr());
			session.remove("markedEmployeeList");
			return SUCCESS;
		}
	}

	public boolean isNotEligible(Date date) {
		List<Employee> employees = (ArrayList<Employee>) notEligibleEmployeeService.getAllNotEligibleEmployee(date);
		for (int i = 0; i < selectedArr.length; i++) {
			for (Employee e : employees) {
				if (selectedArr[i].equals(e.getName())) {
					return true;
				}
			}
		}

		return false;
	}

	/* submit after reEdit */

	public String reSubmit() {
		Date date = Date.valueOf(this.getActiveTime());
		request.setRequestStatus(Status.WAITING);
		request.setTeamBuildingTime(date);
		request.setTotalNumber(selectedArr.length);
		request.setTotalCost(selectedArr.length * percost);
		request.setRequestTime(new Date(System.currentTimeMillis()));
		request.setRequesterName((String) session.get("name"));
		if (!isNotEligible(date) && requestService.update(request)
				&& relationService.saveRelation(request.getId(), getSelectedArr())) {
			return SUCCESS;
		}

		return ERROR;

	}

}
