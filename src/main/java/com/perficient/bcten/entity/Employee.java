package com.perficient.bcten.entity;

import java.io.Serializable;
import java.sql.Date;

public class Employee extends Id implements Serializable {
	private static final long serialVersionUID = 1671321938546129187L;
	private String employeeId;
	private String name;
	private String level;
	private Date onboardDate;
	private Date workStartDate;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Date getOnboardDate() {
		return onboardDate;
	}

	public void setOnboardDate(Date onboardDate) {
		this.onboardDate = onboardDate;
	}

	public Date getWorkStartDate() {
		return workStartDate;
	}

	public void setWorkStartDate(Date workStartDate) {
		this.workStartDate = workStartDate;
	}
}
