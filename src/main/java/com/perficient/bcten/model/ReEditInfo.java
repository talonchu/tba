package com.perficient.bcten.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.perficient.bcten.entity.Relation;
import com.perficient.bcten.util.Status;
import com.perficient.bcten.util.TeamBuildingTimeClone;

public class ReEditInfo implements Serializable {

	private static final long serialVersionUID = 7429249354229165751L;
	private String purpose;
	private String activity;
	private Date teamBuildingTime;
	private List<Relation> relationList;
	private Status requestStatus;
	private String requesterName;
	private String approver;
	private Date approvedTime;
	private double totalCost;
	private int totalNumber;

	public Status getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(Status requestStatus) {
		this.requestStatus = requestStatus;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public Date getApprovedTime() {
		if (approvedTime == null) {
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

	public List<Relation> getRelationList() {
		return relationList;
	}

	public void setRelationList(List<Relation> relationList) {
		this.relationList = relationList;
	}

	public String getRequesterName() {
		return requesterName;
	}

	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public int getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}

}
