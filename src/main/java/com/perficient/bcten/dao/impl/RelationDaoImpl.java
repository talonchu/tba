package com.perficient.bcten.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.perficient.bcten.dao.RelationDao;
import com.perficient.bcten.entity.Relation;

public class RelationDaoImpl extends HibernateDaoSupport implements RelationDao {

	@Override
	public void save(Relation relation) {
		getHibernateTemplate().save(relation);

	}

	@Override
	public boolean delete(int requestId) {
		int result = getSession().createQuery("delete from Relation where requestId=" + requestId).executeUpdate();
		if (result == 0) {
			return false;
		}
		return true;
	}

}
