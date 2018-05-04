package com.perficient.bcten.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class VerifyUtilTest {

	@Test
	public void test() {
		VerifyUtil verifyUtil = VerifyUtil.getInstance();
		String code = verifyUtil.getCode(1);
		String id = verifyUtil.getID(code);
		assertEquals(id, "1");
	}

	@Test
	public void test2() {
		VerifyUtil verifyUtil = VerifyUtil.getInstance();
		verifyUtil.setRandomString("test");
		String code = verifyUtil.getCode(1);
		String id = verifyUtil.getID(code);
		assertEquals(id, "1");
	}

}
