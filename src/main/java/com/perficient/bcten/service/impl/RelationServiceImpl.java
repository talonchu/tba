package com.perficient.bcten.service.impl;

import com.perficient.bcten.dao.RelationDao;
import com.perficient.bcten.entity.Relation;
import com.perficient.bcten.service.RelationSerivce;

public class RelationServiceImpl implements RelationSerivce {
	private RelationDao relationDao;

	public RelationDao getRelationDao() {
		return relationDao;
	}

	public void setRelationDao(RelationDao relationDao) {
		this.relationDao = relationDao;
	}

	@Override
	public boolean saveRelation(int requestId, String[] selectedArr) {
		int len = selectedArr.length;
		for (int i = 0; i < len; i++) {
			Relation relation = new Relation();
			relation.setRequestId(requestId);
			relation.setEmployeeName(selectedArr[i]);
			relationDao.save(relation);
		}
		return true;
	}

}
