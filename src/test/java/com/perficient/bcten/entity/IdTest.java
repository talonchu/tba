package com.perficient.bcten.entity;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class IdTest {
	@Test
	public void setGetId() {
		Id id = new Id();
		Integer i = 2;
		id.setId(i);
		assertEquals(i, id.getId());
	}
}
