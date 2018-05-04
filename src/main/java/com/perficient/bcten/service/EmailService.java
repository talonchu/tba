package com.perficient.bcten.service;

public interface EmailService {
	void setDetail(String toAddress, String subject, String content);

	boolean send();

	boolean setResponseEmailInfo(int requestId);
}
