package com.perficient.bcten.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.perficient.bcten.util.Status;

public class StatusTest {

	@Test
	public void test() {
		Status status = Status.APPROVED;
		assertEquals(Status.WAITING, Status.getGender(0));
		assertEquals(Status.APPROVED, Status.getGender(1));
		assertEquals(Status.DISAPPROVED, Status.getGender(2));
		assertEquals(Status.WAITING, Status.getGender(3));
		assertEquals(1, status.getValue());
	}

}
