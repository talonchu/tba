package com.perficient.bcten.util.exception;

import static org.junit.Assert.*;

import org.junit.Test;

public class CExceptionTest {

	@Test
	public void test1() {
		assertNotNull(new CException());
		assertNotNull(new CException(new Throwable()));
		assertNotNull(new CException("test", new Throwable()));
		assertNotNull(new CException("test"));
	}

}
