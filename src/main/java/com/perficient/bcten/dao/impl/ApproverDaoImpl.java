package com.perficient.bcten.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.perficient.bcten.dao.ApproverDao;
import com.perficient.bcten.entity.Permission;

public class ApproverDaoImpl extends HibernateDaoSupport implements ApproverDao {

	public List<Permission> findApprovers() {
		String hql = "from Permission where role=1 or role=2";
		return getHibernateTemplate().find(hql);
	}
}
