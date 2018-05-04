package com.perficient.bcten.dao;

import java.util.List;
import java.util.Map;

import com.perficient.bcten.entity.Request;
import com.perficient.bcten.model.MarkedRequest;

public interface RequestDao {
	void save(Request request);

	List<MarkedRequest> getAllRequestList(Map<String, String> fields, String username, int offset, int pageSize,
			int status, String column, int sortStatus);

	boolean approveRequest(Request request);

	boolean rejectRequest(Request request);

	Request getRequestById(int requestId);

	int getAllRow(int status);

	List<MarkedRequest> getPersonalRequestList(Map<String, String> fields, String username, int offset, int pageSize,
			int status, String column, int sortStatus);

	int getPersonalRow(String username, int status);

	void delete(Request request);

	boolean update(Request request);

	boolean repealApproved(Request request);
}
