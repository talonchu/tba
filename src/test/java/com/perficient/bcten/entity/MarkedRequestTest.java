package com.perficient.bcten.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import com.perficient.bcten.model.MarkedRequest;

public class MarkedRequestTest {
	MarkedRequest markedRequest = new MarkedRequest();
	Request request = new Request();

	@Test
	public void setGetRequest() {
		markedRequest.setRequest(request);
		assertEquals(request, markedRequest.getRequest());
	}

	@Test
	public void testSetGetFlag() {
		markedRequest.setFlag(true);
		assertEquals(true, markedRequest.getFlag());
	}
}
