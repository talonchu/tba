package com.perficient.bcten.util;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;

import org.junit.Test;

import com.perficient.bcten.util.exception.CException;

public class TeamBuildingTimeCloneTest {

	@Test
	public void test() {
		try {
			Constructor constructor = TeamBuildingTimeClone.class.getDeclaredConstructor();
			constructor.setAccessible(true);
			TeamBuildingTimeClone filter = (TeamBuildingTimeClone) constructor.newInstance();
			assertNotNull(filter);
		} catch (Exception e) {
			fail("failed with exception: " + e.getMessage());
		}
	}

}
