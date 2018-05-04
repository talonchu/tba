package com.perficient.bcten.action;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.Test;

import com.perficient.bcten.entity.Employee;
import com.perficient.bcten.entity.Request;
import com.perficient.bcten.service.NotEligibleEmployeeService;
import com.perficient.bcten.service.RelationSerivce;
import com.perficient.bcten.service.RequestService;

public class RequestActionTest extends EasyMockSupport {
	private RequestService requestService = createMock(RequestService.class);
	private RelationSerivce relationSerivce = createMock(RelationSerivce.class);
	private NotEligibleEmployeeService notEligibleEmployeeService = createMock(NotEligibleEmployeeService.class);

	@Test
	public void SubmitErrorByInsertError() throws Exception {
		Map<String, Object> session = new HashMap<String, Object>();
		session.put("name", "daniel.cao");
		RequestAction requestAction = new RequestAction();
		Request request = initRequest("go hiding");
		requestAction.setActiveTime("1999-02-03");
		requestAction.getActiveTime();
		requestAction.setSession(session);
		requestAction.getSession();
		String[] arr = { "dd", "ds" };
		requestAction.setSelectedArr(arr);
		requestAction.getSelectedArr();
		requestAction.setRequest(request);
		requestAction.setRequestService(requestService);
		requestAction.getRequestService();
		requestAction.setRelationService(relationSerivce);
		requestAction.getRelationService();
		assertEquals("error", requestAction.submit());
	}

	@Test
	public void SubmitSuccess() throws Exception {
		Map<String, Object> session = new HashMap<String, Object>();
		session.put("name", "daniel.cao");
		RequestAction requestAction = new RequestAction();
		Request request = initRequest("go hiding");
		request.setId(10);
		Employee employee = new Employee();
		employee.setName("Hllo");
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(employee);
		requestAction.setActiveTime("1999-02-03");
		requestAction.getActiveTime();
		requestAction.setSession(session);
		requestAction.setNotEligibleEmployeeService(notEligibleEmployeeService);
		requestAction.getNotEligibleEmployeeService();
		requestAction.getSession();
		String[] arr = { "dd", "ds" };
		requestAction.setSelectedArr(arr);
		requestAction.getSelectedArr();
		requestAction.setRequest(request);
		requestAction.setRequestService(requestService);
		requestAction.getRequestService();
		EasyMock.expect(
				notEligibleEmployeeService.getAllNotEligibleEmployee(Date.valueOf(requestAction.getActiveTime())))
				.andReturn(employees);
		EasyMock.replay(notEligibleEmployeeService);
		requestAction.setRelationService(relationSerivce);
		requestAction.getRequestService().save(requestAction.getRequest());
		requestAction.getRequestService().delete(requestAction.getRequest());

		EasyMock.expectLastCall();
		EasyMock.replay(requestService);

		assertEquals("success", requestAction.submit());
	}

	@Test
	public void SubmitWrong() throws Exception {
		Map<String, Object> session = new HashMap<String, Object>();
		session.put("name", "daniel.cao");
		RequestAction requestAction = new RequestAction();
		Request request = initRequest("go hiding");
		request.setId(10);
		Employee employee = new Employee();
		employee.setName("Hllo");
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(employee);
		requestAction.setActiveTime("1999-02-03");
		requestAction.getActiveTime();
		requestAction.setSession(session);
		requestAction.setNotEligibleEmployeeService(notEligibleEmployeeService);
		requestAction.getNotEligibleEmployeeService();
		requestAction.getSession();
		String[] arr = { "Hllo", "ds" };
		requestAction.setSelectedArr(arr);
		requestAction.getSelectedArr();
		requestAction.setRequest(request);
		requestAction.setRequestService(requestService);
		requestAction.getRequestService();
		EasyMock.expect(
				notEligibleEmployeeService.getAllNotEligibleEmployee(Date.valueOf(requestAction.getActiveTime())))
				.andReturn(employees);
		EasyMock.replay(notEligibleEmployeeService);
		requestAction.setRelationService(relationSerivce);
		requestAction.getRequestService().save(requestAction.getRequest());
		requestAction.getRequestService().delete(requestAction.getRequest());
		EasyMock.expectLastCall();
		EasyMock.replay(requestService);

		assertEquals("error", requestAction.submit());
	}

	public Request initRequest(String purpose) {
		Request request = new Request();
		request.setPurpose(purpose);
		return request;
	}

	@Test
	public void testSetGetSelectedArr() {
		String[] temp = {};
		RequestAction requestAction = new RequestAction();
		String[] selectedArr = requestAction.getSelectedArr();
		assertEquals(temp, selectedArr);
	}

	@Test
	public void testSetGetPercost() {
		RequestAction requestAction = new RequestAction();
		requestAction.setPercost(909.0);
		assertEquals(String.valueOf(909.0), String.valueOf(requestAction.getPercost()));
	}

	@Test
	public void testGetSelectArr() {
		RequestAction requestAction = new RequestAction();
		assertEquals(true, requestAction.getSelectedArr() != null);
	}

	/*
	 * @Test public void testReEdit(){ RequestAction requestAction = new
	 * RequestAction(); Request request = new Request(); request.setId(1);
	 * Map<String, Object> session = new HashMap<String, Object>();
	 * requestAction.setActiveTime("2001-01-01");
	 * requestAction.setSelectedArr(new String[]{"rt"});
	 * requestAction.setRequest(request); requestAction.setSession(session);
	 * EasyMock.expect(requestService.update(request)).andReturn(true);
	 * EasyMock.
	 * expect(relationSerivce.saveRelation(request.getId(),requestAction
	 * .getSelectedArr())).andReturn(true); EasyMock.replay(requestService);
	 * EasyMock.replay(relationSerivce);
	 * requestAction.setRequestService(requestService);
	 * requestAction.setRelationService(relationSerivce);
	 * assertEquals("success",requestAction.reSubmit()); }
	 */
}
