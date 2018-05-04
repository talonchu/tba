package com.perficient.bcten.dao;

import com.perficient.bcten.entity.Relation;

public interface RelationDao {
	void save(Relation relation);

	boolean delete(int requestId);
}
