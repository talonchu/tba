package com.perficient.bcten.util;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.hibernate.Hibernate;
import org.hibernate.usertype.UserType;

public class StatusType implements UserType {
	public int[] sqlTypes() {
		return new int[] { Hibernate.SHORT.sqlType() };
	}

	public Class returnedClass() {
		return Status.class;
	}

	public boolean isMutable() {
		return false;
	}

	public Object deepCopy(Object arg0) {
		return arg0;
	}

	public Serializable disassemble(Object arg0) {
		return (Serializable) arg0;
	}

	public Object assemble(Serializable arg0, Object arg1) {
		return arg0;
	}

	public Object replace(Object original, Object target, Object owner) {
		return original;
	}

	@Override
	public boolean equals(Object arg0, Object arg1) {
		return arg0.equals(arg1);
	}

	public int hashCode(Object o) {
		return o.hashCode();
	}

	public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws SQLException {
		int value = rs.getInt(names[0]);
		return Status.getGender(value);
	}

	public void nullSafeSet(PreparedStatement ps, Object value, int index) throws SQLException {
		if (value == null) {
			ps.setInt(index, Hibernate.SHORT.sqlType());
		} else {
			ps.setInt(index, ((Status) value).getValue());
		}
	}
}