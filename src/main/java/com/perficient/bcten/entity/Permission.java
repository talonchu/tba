package com.perficient.bcten.entity;

import java.io.Serializable;

public class Permission extends Id implements Serializable {

	private static final long serialVersionUID = 4142425666803916252L;
	private String employeeName;
	private Integer role;

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

}
