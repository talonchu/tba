package com.perficient.bcten.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.perficient.bcten.dao.PermissionDao;
import com.perficient.bcten.entity.Permission;

public class PermissionDaoImpl extends HibernateDaoSupport implements PermissionDao {

	@Override
	public int validatePermission(String username) {
		List<Permission> temp = getHibernateTemplate().find("from Permission where employeeName='" + username + "'");
		if (temp.size() == 0) {
			return 0;
		} else if (temp.get(0).getRole() == 1) {
			return 1;
		}
		return 2;
	}
}
