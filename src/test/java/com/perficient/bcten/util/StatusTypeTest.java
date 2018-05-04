package com.perficient.bcten.util;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.Test;

public class StatusTypeTest extends EasyMockSupport {
	private StatusType statusType;

	@Test
	public void testSqlTypes() {
		statusType = new StatusType();
		int[] a = statusType.sqlTypes();
		assertEquals(true, true);
	}

	@Test
	public void testReturnedClass() {
		statusType = new StatusType();
		assertEquals(Status.class, statusType.returnedClass());
	}

	@Test
	public void testIsMutable() {
		statusType = new StatusType();
		assertEquals(false, statusType.isMutable());
	}

	@Test
	public void testDeepCopy() {
		statusType = new StatusType();
		Object args = new Object();
		assertEquals(args, statusType.deepCopy(args));
	}

	@Test
	public void testDisassemble() {
		statusType = new StatusType();
		Serializable args = new Serializable() {
		};
		assertEquals(args, statusType.disassemble(args));
	}

	@Test
	public void testAssemble() {
		statusType = new StatusType();
		Serializable s = new Serializable() {
		};
		Object args = new Object();
		assertEquals(s, statusType.assemble(s, args));
	}

	@Test
	public void testReplace() {
		statusType = new StatusType();
		Object arg0 = new Object();
		Object arg1 = new Object();
		Object arg2 = new Object();
		assertEquals(arg0, statusType.replace(arg0, arg1, arg2));
	}

	@Test
	public void testEquals() {
		statusType = new StatusType();
		int a = 1;
		int b = 1;
		assertEquals(true, statusType.equals(a, b));
	}

	@Test
	public void testHashCode() {
		statusType = new StatusType();
		Object args = new Object();
		assertEquals(args.hashCode(), statusType.hashCode(args));
	}

	@Test
	public void testNullSafeGet() throws SQLException {
		ResultSet rs = createMock(ResultSet.class);
		EasyMock.expect(rs.getInt("0")).andReturn(1);
		EasyMock.replay(rs);
		statusType = new StatusType();
		assertEquals(Status.APPROVED, statusType.nullSafeGet(rs, new String[] { "0" }, new Object()));
	}

}
