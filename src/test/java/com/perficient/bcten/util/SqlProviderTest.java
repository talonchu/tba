package com.perficient.bcten.util;

import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import java.util.Map;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import com.perficient.bcten.util.SqlProvider;

public class SqlProviderTest extends EasyMockSupport {
	SqlProvider sqlProvider;

	@Test
	public void SqlProviderForOneField() {
		sqlProvider = new SqlProvider();
		Map<String, String> fields = new HashMap<String, String>();
		fields.put("username", "root");
		assertEquals(" where username = 'root' ", sqlProvider.getQuery(fields));
	}

	@Test
	public void SqlProviderForFields() {
		sqlProvider = new SqlProvider();
		Map<String, String> fields = new HashMap<String, String>();
		fields.put("username", "root");
		fields.put("password", "123");
		assertEquals(" where username = 'root' and password = '123' ", sqlProvider.getQuery(fields));
	}

	@Test
	public void SqlProviderForNoField() {
		sqlProvider = new SqlProvider();
		Map<String, String> fields = new HashMap<String, String>();
		assertEquals("", sqlProvider.getQuery(fields));
	}

	@Test
	public void SqlProviderForNullField() {
		sqlProvider = new SqlProvider();
		assertEquals("", sqlProvider.getQuery(null));
	}
}
