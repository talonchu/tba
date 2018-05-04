package com.perficient.bcten.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.perficient.bcten.dao.ReEditInfoDao;
import com.perficient.bcten.entity.Relation;
import com.perficient.bcten.model.MarkedEmployee;
import com.perficient.bcten.model.ReEditInfo;
import com.perficient.bcten.service.ReEditInfoService;
import com.perficient.bcten.util.exception.CException;

public class ReEditInfoServiceImpl implements ReEditInfoService {
	private ReEditInfoDao reEditInfoDao;

	public ReEditInfoDao getReEditInfoDao() {
		return reEditInfoDao;
	}

	public void setReEditInfoDao(ReEditInfoDao reEditInfoDao) {
		this.reEditInfoDao = reEditInfoDao;
	}

	@Override
	public ReEditInfo getReEditInfo(int requestId) {
		ReEditInfo reEditInfo = reEditInfoDao.findReEditInfoByRequestId(requestId);
		if (reEditInfo == null) {
			throw new CException();
		}
		return reEditInfo;

	}

	@Override
	public Map<String, List<MarkedEmployee>> cutMarkedList(List<MarkedEmployee> markedEmployees,
			List<Relation> reEditList) {

		List<MarkedEmployee> selectedMarkedEmployees = new ArrayList<MarkedEmployee>();
		List<MarkedEmployee> unSelectedMarkedEmployees = new ArrayList<MarkedEmployee>();

		c: for (MarkedEmployee mkEmployee : markedEmployees) {
			for (Relation relation : reEditList) {
				if (relation.getEmployeeName().equals(mkEmployee.getEmployee().getName())) {
					selectedMarkedEmployees.add(mkEmployee);
					continue c;
				}

			}
			unSelectedMarkedEmployees.add(mkEmployee);
		}

		Map<String, List<MarkedEmployee>> markedEmployeeListsMap = new HashMap<String, List<MarkedEmployee>>();
		markedEmployeeListsMap.put("selectedMarkedEmployees", selectedMarkedEmployees);
		markedEmployeeListsMap.put("unSelectedMarkedEmployees", unSelectedMarkedEmployees);

		return markedEmployeeListsMap;
	}

}
