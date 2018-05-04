package com.perficient.bcten.service;

import java.util.List;
import java.util.Map;

import com.perficient.bcten.entity.Relation;
import com.perficient.bcten.model.MarkedEmployee;
import com.perficient.bcten.model.ReEditInfo;

public interface ReEditInfoService {
	ReEditInfo getReEditInfo(int requestId);

	Map<String, List<MarkedEmployee>> cutMarkedList(List<MarkedEmployee> markedEmployees, List<Relation> reEditList);
}
