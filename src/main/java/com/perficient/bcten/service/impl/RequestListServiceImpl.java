package com.perficient.bcten.service.impl;

import java.util.List;
import java.util.Map;

import com.perficient.bcten.dao.RequestDao;
import com.perficient.bcten.entity.Request;
import com.perficient.bcten.model.MarkedRequest;
import com.perficient.bcten.service.RequestListService;
import com.perficient.bcten.util.Page;

public class RequestListServiceImpl implements RequestListService {

	private RequestDao requestDao;
	private Page page = new Page();

	public RequestDao getRequestDao() {
		return requestDao;
	}

	public void setRequestDao(RequestDao requestDao) {
		this.requestDao = requestDao;
	}

	@Override
	public boolean approveRequest(Request request) {
		return requestDao.approveRequest(request);
	}

	@Override
	public List<MarkedRequest> getAllRequestList(Map<String, String> fields, String username, int curPage, int status,
			String column, int sortStatus) {
		int offset = page.countOffset(page.getPageSize(), curPage);
		return requestDao.getAllRequestList(fields, username, offset, page.getPageSize(), status, column, sortStatus);
	}

	@Override
	public Request getRequestById(int requestId) {
		return requestDao.getRequestById(requestId);
	}

	@Override
	public int getTotalPage(Integer role, String username, int status) {
		int allRow;
		if (role != null && (role == 1 || role == 2)) {
			allRow = requestDao.getAllRow(status);
		} else {
			allRow = requestDao.getPersonalRow(username, status);
		}
		return page.countTotalPage(page.getPageSize(), allRow);
	}

	@Override
	public boolean rejectRequest(Request request) {
		return requestDao.rejectRequest(request);
	}

	@Override
	public List<MarkedRequest> getPersonalRequestList(Map<String, String> fields, String username, int curPage,
			int status, String column, int sortStatus) {
		int offset = page.countOffset(page.getPageSize(), curPage);
		return requestDao.getPersonalRequestList(fields, username, offset, page.getPageSize(), status, column,
				sortStatus);
	}

	@Override
	public boolean repealApproved(Request request) {
		return requestDao.repealApproved(request);
	}

}
