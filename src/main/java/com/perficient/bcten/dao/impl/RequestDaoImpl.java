package com.perficient.bcten.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.perficient.bcten.dao.RequestDao;
import com.perficient.bcten.entity.Request;
import com.perficient.bcten.model.MarkedRequest;
import com.perficient.bcten.util.SqlProvider;
import com.perficient.bcten.util.Status;

public class RequestDaoImpl<T> extends HibernateDaoSupport implements RequestDao {

	private Request request;
	private SqlProvider sqlProvider;
	private String mainSql = "select * from request";
	private String sqlCondition = " where requesterName='";

	public SqlProvider getSqlProvider() {
		return sqlProvider;
	}

	public void setSqlProvider(SqlProvider sqlProvider) {
		this.sqlProvider = sqlProvider;
	}

	@Override
	public void save(Request request) {
		getHibernateTemplate().save(request);
	}

	@Override
	public boolean approveRequest(Request request) {
		if (request == null) {
			return false;
		} else {
			int id = request.getId();
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			this.request = getRequestById(id);
			this.request.setRequestStatus(Status.APPROVED);
			this.request.setApprover(request.getApprover());
			this.request.setApprovedTime(request.getApprovedTime());
			session.clear();
			session.update(this.request);
			return true;
		}
	}

	@Override
	public List<MarkedRequest> getAllRequestList(Map<String, String> fields, String username, int offset, int pageSize,
			int status, String column, int sortStatus) {
		String myCondition = null;
		String sortSql = null;
		String sort = null;
		if (sortStatus == 0) {
			sort = "desc";
		} else {
			sort = "asc";
		}
		if (column.equals("")) {
			sortSql = " order by requestStatus,requestTime desc limit ";
		} else {
			sortSql = " order by " + column + " " + sort + " limit ";
		}
		if (status == 0) {
			myCondition = " where requestStatus=0";
		} else {
			myCondition = " where requestStatus<>0";
		}

		Query query = this
				.getSession()
				.createSQLQuery(
						mainSql + myCondition + " " + sqlProvider.getQuery(fields) + sortSql + offset + "," + pageSize)
				.addEntity(Request.class);
		String s = mainSql + myCondition + " " + sqlProvider.getQuery(fields) + sortSql + offset + "," + pageSize;
		System.out.println("s = " + s);
		List<Request> requestList = query.list();
		List<MarkedRequest> modelList = new ArrayList<MarkedRequest>();
		for (int i = 0; i < requestList.size(); i++) {
			MarkedRequest requestModel = new MarkedRequest();
			Request req = requestList.get(i);
			requestModel.setRequest(req);
			String sql = mainSql + " where requestStatus=2 and id=" + req.getId();
			query = this.getSession().createSQLQuery(sql).addEntity(Request.class);
			List<Request> rejectList = query.list();
			if (rejectList.size() == 1) {
				requestModel.setFlag(true);
			} else {
				requestModel.setFlag(false);
			}
			modelList.add(requestModel);
		}
		return modelList;
	}

	@Override
	public List<MarkedRequest> getPersonalRequestList(Map<String, String> fields, String username, int offset,
			int pageSize, int status, String column, int sortStatus) {
		String myCondition = null;
		String sortSql = null;
		String sort = null;
		System.out.println("sortStatus = " + sortStatus);
		System.out.println("column = " + column);
		if (sortStatus == 0) {
			sort = "desc";
		} else {
			sort = "asc";
		}
		if (column.equals("")) {
			sortSql = " order by requestStatus,requestTime desc limit ";
		} else {
			sortSql = " order by " + column + " " + sort + " limit ";
		}
		if (status == 0) {
			myCondition = " and requestStatus=0";
		} else {
			myCondition = " and requestStatus<>0";
		}

		String sql = mainSql + sqlCondition + username + "' " + myCondition
				+ " or id in (select requestId from relation where employeeName='" + username + "')";
		Query query = this.getSession()
				.createSQLQuery(sql + sqlProvider.getQuery(fields) + sortSql + offset + "," + pageSize)
				.addEntity(Request.class);
		List<Request> requestList = query.list();
		List<MarkedRequest> modelList = new ArrayList<MarkedRequest>();
		for (int i = 0; i < requestList.size(); i++) {
			MarkedRequest requestModel = new MarkedRequest();
			Request req = requestList.get(i);
			requestModel.setRequest(req);
			sql = mainSql + sqlCondition + username + "' and requestStatus=2 and id=" + req.getId();
			query = this.getSession().createSQLQuery(sql).addEntity(Request.class);
			List<Request> rejectList = query.list();
			if (rejectList.size() == 1) {
				requestModel.setFlag(true);
			} else {
				requestModel.setFlag(false);
			}
			modelList.add(requestModel);
		}
		return modelList;
	}

	@Override
	public Request getRequestById(int requestId) {
		return getHibernateTemplate().get(Request.class, requestId);
	}

	@Override
	public boolean rejectRequest(Request request) {
		if (request == null) {
			return false;
		} else {
			int id = request.getId();
			String reason = request.getRejectReason();
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			this.request = getRequestById(id);
			this.request.setRequestStatus(Status.DISAPPROVED);
			this.request.setRejectReason(reason);
			session.clear();
			session.update(this.request);
			return true;
		}
	}

	@Override
	public boolean repealApproved(Request request) {
		if (request == null) {
			return false;
		} else {
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			this.request = getRequestById(request.getId());
			this.request.setRequestStatus(Status.WAITING);
			this.request.setApprover(null);
			this.request.setApprovedTime(null);
			session.clear();
			session.update(this.request);
			return true;
		}
	}

	@Override
	public int getAllRow(int status) {
		String myCondition = null;
		if (status == 0) {
			myCondition = " where requestStatus=0";
		} else {
			myCondition = " where requestStatus<>0";
		}
		return getHibernateTemplate().find("from Request" + myCondition).size();
	}

	@Override
	public int getPersonalRow(String username, int status) {
		String myCondition = null;
		if (status == 0) {
			myCondition = " and requestStatus=0";
		} else {
			myCondition = " and requestStatus<>0";
		}

		String sql = mainSql + sqlCondition + username + "'" + myCondition
				+ " or id in (select requestId from relation where employeeName='" + username + "')";
		Query query = this.getSession().createSQLQuery(sql).addEntity(Request.class);
		System.out.println("size = " + query.list().size());
		return query.list().size();
	}

	@Override
	public void delete(Request request) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(request);
	}

	@Override
	public boolean update(Request request) {
		if (request == null) {
			return false;
		} else {
			getHibernateTemplate().update(request);
			return true;
		}

	}

}
