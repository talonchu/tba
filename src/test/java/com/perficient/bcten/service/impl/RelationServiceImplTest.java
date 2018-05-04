package com.perficient.bcten.service.impl;

import org.easymock.EasyMock;
import org.junit.Test;

import com.perficient.bcten.dao.RelationDao;
import com.perficient.bcten.dao.impl.RelationDaoImpl;
import com.perficient.bcten.entity.Relation;

public class RelationServiceImplTest {

	private RelationServiceImpl relationServiceImpl;

	@Test
	public void RelationServiceSaveTest() {
		RelationDao relationDao = EasyMock.createMock(RelationDaoImpl.class);
		relationServiceImpl = new RelationServiceImpl();
		relationServiceImpl.setRelationDao(relationDao);
		relationServiceImpl.getRelationDao().save(EasyMock.anyObject(Relation.class));
		EasyMock.expectLastCall().times(2);
		EasyMock.replay(relationDao);
		relationServiceImpl.saveRelation(1000, new String[] { "ds", "dd" });
		EasyMock.verify(relationDao);
	}

}
