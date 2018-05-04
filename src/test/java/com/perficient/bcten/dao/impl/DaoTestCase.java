package com.perficient.bcten.dao.impl;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

public abstract class DaoTestCase extends AbstractTransactionalDataSourceSpringContextTests {

	protected String[] getConfigLocations() {
		return new String[] { "DaoTest.xml" };
	}

}