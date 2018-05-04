package com.perficient.bcten.entity;

import java.io.Serializable;

public class Relation extends Id implements Serializable {

	private static final long serialVersionUID = -5422119847957142742L;
	private String employeeName;
	private int requestId;

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

}
