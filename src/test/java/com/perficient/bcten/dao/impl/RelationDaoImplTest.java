package com.perficient.bcten.dao.impl;

import com.perficient.bcten.dao.RelationDao;
import com.perficient.bcten.dao.RequestDao;
import com.perficient.bcten.entity.Relation;

public class RelationDaoImplTest extends DaoTestCase {

	private RelationDao relationDAO;

	protected void onSetUpInTransaction() throws Exception {
		super.onSetUpInTransaction();
		relationDAO = (RelationDao) this.applicationContext.getBean("relationDAO");
	}

	protected void onTearDownInTransaction() {
		relationDAO = null;
	}

	public void testSaveSuccess() {
		jdbcTemplate
				.update("insert into request (teamBuildingTime,requestTime,requestStatus) values ('3000-01-01','3000-01-01',0)");
		int id = jdbcTemplate.queryForInt("select distinct LAST_INSERT_ID() from request");
		Relation relation = new Relation();
		relation.setEmployeeName("test000000");
		relation.setRequestId(id);
		relationDAO.save(relation);
		String name = (String) jdbcTemplate.queryForObject("select employeeName from relation where employeeName=?",
				new Object[] { relation.getEmployeeName() }, String.class);
		assertEquals(relation.getEmployeeName(), name);
	}

	public void testDeleteSuccess() {
		jdbcTemplate
				.update("insert into request (teamBuildingTime,requestTime,requestStatus) values ('3000-01-01','3000-01-01',0)");
		int requestId = jdbcTemplate.queryForInt("select distinct LAST_INSERT_ID() from request");
		jdbcTemplate.update("insert into relation (employeeName,requestId) values ('test'," + requestId + ")");

		boolean flag = relationDAO.delete(requestId);
		assertEquals(true, flag);
	}

	public void testDeleteFail() {
		jdbcTemplate
				.update("insert into request (teamBuildingTime,requestTime,requestStatus) values ('3000-01-01','3000-01-01',0)");
		int requestId = jdbcTemplate.queryForInt("select distinct LAST_INSERT_ID() from request");
		jdbcTemplate.update("insert into relation (employeeName,requestId) values ('test'," + requestId + ")");
		jdbcTemplate.update("delete from Relation where requestId=" + requestId);
		boolean flag = relationDAO.delete(requestId);
		assertEquals(false, flag);
	}

}
