package com.perficient.bcten.service;

import com.perficient.bcten.entity.Request;

public interface RequestService {
	void save(Request request);

	void delete(Request request);

	boolean update(Request request);
}
