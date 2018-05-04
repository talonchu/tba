package com.perficient.bcten.util;

import java.util.Iterator;
import java.util.Map;

public class SqlProvider {
	@SuppressWarnings("rawtypes")
	public String getQuery(Map<String, String> fields) {
		StringBuffer queryString = new StringBuffer();
		if (fields != null && !fields.isEmpty()) {
			queryString.append(" where");

			for (Iterator iterator = fields.entrySet().iterator(); iterator.hasNext();) {
				Map.Entry entry = (Map.Entry) iterator.next();
				queryString.append(" ");
				queryString.append(entry.getKey());
				queryString.append(" = '");
				queryString.append(entry.getValue());
				queryString.append("' ");
				if (iterator.hasNext()) {
					queryString.append("and");
				}
			}
		}
		return queryString.toString();
	}
}
