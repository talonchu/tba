package com.perficient.bcten.dao.impl;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

public abstract class RemoteDaoTestCase extends AbstractTransactionalDataSourceSpringContextTests {

	protected String[] getConfigLocations() {
		return new String[] { "RemoteDaoTest.xml" };
	}

}