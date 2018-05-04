package com.perficient.bcten.model;

import com.perficient.bcten.entity.Request;

public class MarkedRequest {
	private Request request;
	private boolean flag;

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
