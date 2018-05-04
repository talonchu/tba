package com.perficient.bcten.entity;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class RelationTest {
	Relation relation = new Relation();

	@Test
	public void setGetEmployeeName() {
		relation.setEmployeeName("test");
		assertEquals("test", relation.getEmployeeName());
	}

	@Test
	public void setGetRequestId() {
		relation.setRequestId(1);
		assertEquals(1, relation.getRequestId());
	}
}
