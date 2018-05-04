package com.perficient.bcten.entity;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.perficient.bcten.model.ReEditInfo;
import com.perficient.bcten.util.Status;
import com.perficient.bcten.util.TeamBuildingTimeClone;

public class ReEditInfoTest {
	ReEditInfo reEditInfo = new ReEditInfo();

	@Test
	public void setGetPurpose() {
		reEditInfo.setPurpose("test");
		assertEquals("test", reEditInfo.getPurpose());
	}

	@Test
	public void setGetActivity() {
		reEditInfo.setActivity("test");
		assertEquals("test", reEditInfo.getActivity());
	}

	@Test
	public void setGeTTeamBuildingTime() {
		Date date = null;
		reEditInfo.setTeamBuildingTime(date);
		assertEquals(null, reEditInfo.getTeamBuildingTime());
		date = new Date(System.currentTimeMillis());
		reEditInfo.setTeamBuildingTime(date);
		assertEquals(date, reEditInfo.getTeamBuildingTime());
	}

	@Test
	public void setGetRelationList() {
		List<Relation> relationList = new ArrayList<Relation>();
		Relation relation = new Relation();
		relation.setEmployeeName("test");
		relationList.add(relation);
		reEditInfo.setRelationList(relationList);
		assertEquals("test", reEditInfo.getRelationList().get(0).getEmployeeName());
	}

	@Test
	public void setGetAdds() {
		Date date = null;
		reEditInfo.setApprovedTime(date);
		assertEquals(null, reEditInfo.getApprovedTime());
		date = new Date(System.currentTimeMillis());
		reEditInfo.setApprovedTime(date);
		assertEquals(date, reEditInfo.getApprovedTime());

		reEditInfo.setApprover("stone.zhang");
		assertEquals("stone.zhang", reEditInfo.getApprover());

		reEditInfo.setRequestStatus(Status.APPROVED);
		assertEquals(Status.APPROVED, reEditInfo.getRequestStatus());

	}

	@Test
	public void setGetTotalCost() {
		reEditInfo.setTotalCost(2.0);
		assertEquals(2.0, reEditInfo.getTotalCost(), 0.5);

	}

	@Test
	public void setGetTotalNumber() {
		reEditInfo.setTotalNumber(2);
		assertEquals(2, reEditInfo.getTotalNumber());

	}

	@Test
	public void setGetRequestName() {
		reEditInfo.setRequesterName("test");
		assertEquals("test", reEditInfo.getRequesterName());

	}
}
