package com.perficient.bcten.model;

import java.io.Serializable;

import com.perficient.bcten.entity.Employee;

public class MarkedEmployee implements Serializable {

	private static final long serialVersionUID = 7240595680107957191L;
	private Employee employee;
	private boolean isEligible;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public boolean getIsEligible() {
		return isEligible;
	}

	public void setEligible(boolean isEligible) {
		this.isEligible = isEligible;
	}

}
