package com.perficient.bcten.entity;

import java.io.Serializable;
import java.util.Date;

import com.perficient.bcten.util.TeamBuildingTimeClone;
import com.perficient.bcten.util.Status;

public class Request extends Id implements Serializable {

	private static final long serialVersionUID = -4971463442320672777L;
	private String requesterName;
	private String purpose;
	private String activity;
	private Integer totalNumber;
	private Date teamBuildingTime;
	private Double totalCost;
	private Status requestStatus;
	private String rejectReason;
	private Date requestTime;
	private String approver;
	private Date approvedTime;

	public String getRequesterName() {
		return requesterName;
	}

	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public Integer getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}

	public Date getTeamBuildingTime() {
		return TeamBuildingTimeClone.getTeamBuildingTime(this.teamBuildingTime);
	}

	public void setTeamBuildingTime(Date teamBuildingTime) {
		if (teamBuildingTime == null) {
			this.teamBuildingTime = null;
		} else {
			this.teamBuildingTime = (Date) teamBuildingTime.clone();
		}
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public Status getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(Status requestStatus) {
		this.requestStatus = requestStatus;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public Date getRequestTime() {
		if (this.requestTime == null) {
			return null;
		} else {
			return (Date) requestTime.clone();
		}
	}

	public void setRequestTime(Date requestTime) {
		if (requestTime == null) {
			this.requestTime = null;
		} else {
			this.requestTime = (Date) requestTime.clone();
		}
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public Date getApprovedTime() {
		if (this.approver == null) {
			return null;
		} else {
			return (Date) approvedTime.clone();
		}

	}

	public void setApprovedTime(Date approvedTime) {
		if (approvedTime == null) {
			this.approvedTime = null;
		} else {
			this.approvedTime = (Date) approvedTime.clone();
		}
	}

}
