package com.perficient.bcten.service;

import java.util.List;
import java.util.Map;

import com.perficient.bcten.entity.Request;
import com.perficient.bcten.model.MarkedRequest;

public interface RequestListService {

	boolean approveRequest(Request request);

	boolean rejectRequest(Request request);

	List<MarkedRequest> getAllRequestList(Map<String, String> field, String username, int offset, int status,
			String column, int sortStatus);

	Request getRequestById(int requestId);

	int getTotalPage(Integer role, String username, int status);

	List<MarkedRequest> getPersonalRequestList(Map<String, String> fields, String username, int curPage, int status,
			String column, int sortStatus);

	boolean repealApproved(Request request);
}
