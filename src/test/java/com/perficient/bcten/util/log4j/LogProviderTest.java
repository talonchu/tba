package com.perficient.bcten.util.log4j;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.perficient.bcten.util.TeamBuildingTimeClone;

public class LogProviderTest {

	@Test
	public void test() {
		LogProvider.debug("String", new String("test"));
		assertEquals("String", Logger.getLogger("String").getName());
	}

	@Test
	public void test2() {
		LogProvider.info("String", new String("test"));
		assertEquals("String", Logger.getLogger("String").getName());
	}

	@Test
	public void test3() {
		try {
			Constructor constructor = LogProvider.class.getDeclaredConstructor();
			constructor.setAccessible(true);
			LogProvider filter = (LogProvider) constructor.newInstance();
			assertNotNull(filter);
		} catch (Exception e) {
			fail("failed with exception: " + e.getMessage());
		}
	}

}
