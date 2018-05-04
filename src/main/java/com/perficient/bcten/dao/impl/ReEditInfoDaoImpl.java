package com.perficient.bcten.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.perficient.bcten.dao.ReEditInfoDao;
import com.perficient.bcten.entity.Relation;
import com.perficient.bcten.entity.Request;
import com.perficient.bcten.model.ReEditInfo;

public class ReEditInfoDaoImpl extends HibernateDaoSupport implements ReEditInfoDao {
	@Override
	public ReEditInfo findReEditInfoByRequestId(int requestId) {
		String hql = "from Relation where requestId=" + requestId;
		List<Relation> relationList = getHibernateTemplate().find(hql);

		String hql2 = "from Request where id=" + requestId;
		List<Request> requestList = getHibernateTemplate().find(hql2);
		Request request = requestList.get(0);
		ReEditInfo reEditInfo = new ReEditInfo();
		reEditInfo.setActivity(request.getActivity());
		reEditInfo.setPurpose(request.getPurpose());
		reEditInfo.setRequestStatus(request.getRequestStatus());
		reEditInfo.setApprover(request.getApprover());
		reEditInfo.setApprovedTime(request.getApprovedTime());
		reEditInfo.setTeamBuildingTime(request.getTeamBuildingTime());
		reEditInfo.setRelationList(relationList);
		reEditInfo.setRequesterName(request.getRequesterName());
		reEditInfo.setTotalCost(request.getTotalCost());
		reEditInfo.setTotalNumber(request.getTotalNumber());
		return reEditInfo;
	}

}
