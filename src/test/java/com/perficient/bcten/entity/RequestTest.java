package com.perficient.bcten.entity;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.sql.Date;

import org.junit.Test;

import com.perficient.bcten.util.Status;

public class RequestTest {
	Request request = new Request();
	Date date = new Date(2012, 4, 1);

	@Test
	public void setGetRequesterName() {
		request.setRequesterName("team");
		assertEquals("team", request.getRequesterName());
	}

	@Test
	public void setGetTotalNumber() {
		request.setTotalNumber(10);
		assertNotNull(request.getTotalNumber());
	}

	@Test
	public void setGetActivity() {
		request.setActivity("active");
		assertEquals("active", request.getActivity());
	}

	@Test
	public void setGetTeamBuildingTime() {
		Date date = null;
		request.setTeamBuildingTime(date);
		assertEquals(null, request.getTeamBuildingTime());
		request.setTeamBuildingTime(date);
		assertEquals(date, request.getTeamBuildingTime());
	}

	@Test
	public void setGetTotalCost() {
		String s = "";
		request.setTotalCost(88.8);
		assertEquals(s.valueOf(88.8), s.valueOf(request.getTotalCost()));
	}

	@Test
	public void setGetRequestStatus() {
		request.setRequestStatus(Status.WAITING);
		assertNotNull(request.getRequestStatus());
	}

	@Test
	public void setGetpurpose() {
		request.setPurpose("requestReason");
		assertEquals("requestReason", request.getPurpose());
	}

	@Test
	public void setGetRejectReason() {
		request.setRejectReason("rejectiveReason");
		assertEquals("rejectiveReason", request.getRejectReason());
	}

	@Test
	public void setGetRequestTime() {
		request.setRequestTime(null);
		assertEquals(null, request.getRequestTime());
		request.setRequestTime(date);
		assertEquals(date, request.getRequestTime());
	}

	@Test
	public void setGetApprover() {
		request.setApprover("stone.zhang");
		assertEquals("stone.zhang", request.getApprover());
	}

	@Test
	public void setGetApprovedTime() {
		Date date = null;
		request.setApprovedTime(date);
		assertEquals(null, request.getApprovedTime());
		request.setApprovedTime(date);
		assertEquals(date, request.getApprovedTime());
	}

}
