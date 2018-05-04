package com.perficient.bcten.service.impl;

import com.perficient.bcten.dao.RelationDao;
import com.perficient.bcten.dao.RequestDao;

import com.perficient.bcten.entity.Request;
import com.perficient.bcten.service.RequestService;

public class RequestServiceImpl implements RequestService {
	private RequestDao requestDao;
	private RelationDao relationDao;

	public RelationDao getRelationDao() {
		return relationDao;
	}

	public void setRelationDao(RelationDao relationDao) {
		this.relationDao = relationDao;
	}

	public RequestDao getRequestDao() {
		return requestDao;
	}

	public void setRequestDao(RequestDao requestDao) {
		this.requestDao = requestDao;
	}

	public void save(Request request) {
		requestDao.save(request);
	}

	public void delete(Request request) {
		requestDao.delete(request);
	}

	@Override
	public boolean update(Request request) {
		if (requestDao.update(request) && relationDao.delete(request.getId())) {
			return true;
		}
		return false;

	}
}
