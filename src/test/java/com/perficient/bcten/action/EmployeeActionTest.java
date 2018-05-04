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
import com.perficient.bcten.entity.Relation;
import com.perficient.bcten.entity.Request;
import com.perficient.bcten.model.MarkedEmployee;
import com.perficient.bcten.model.ReEditInfo;
import com.perficient.bcten.service.EmployeeService;
import com.perficient.bcten.service.ReEditInfoService;
import com.perficient.bcten.service.impl.ReEditInfoServiceImpl;

public class EmployeeActionTest extends EasyMockSupport {

	private EmployeeAction employeeAction;
	private EmployeeService mockEmployeeService = createMock(EmployeeService.class);

	@Test
	public void employeeActionWithServiceDataBackTest() {
		employeeAction = new EmployeeAction();
		Employee employee = new Employee();
		employee.setEmployeeId("HI194");
		MarkedEmployee markedEmployee = new MarkedEmployee();
		markedEmployee.setEmployee(employee);
		List<MarkedEmployee> mockMarkedEmployeesList = new ArrayList<MarkedEmployee>();
		mockMarkedEmployeesList.add(markedEmployee);

		Map<String, Object> mysession = new HashMap<String, Object>();
		mysession.put("id", "id");
		employeeAction.setSession(mysession);
		employeeAction.setActiveTime("2012-12-12");
		employeeAction.setRequestInfo(new Request());
		employeeAction.getRequestInfo();

		EasyMock.expect(mockEmployeeService.getMarkedEmployees(Date.valueOf(employeeAction.getActiveTime())))
				.andReturn(mockMarkedEmployeesList);
		EasyMock.replay(mockEmployeeService);
		employeeAction.setEmployeeService(mockEmployeeService);

		assertEquals("success", employeeAction.getMarkedEmployees());
	}

	@Test
	public void employeeActionWithNoServiceDataBackTest() {
		employeeAction = new EmployeeAction();

		Map<String, Object> mysession = new HashMap<String, Object>();
		mysession.put("id", "id");
		employeeAction.setSession(mysession);

		employeeAction.setActiveTime("2012-12-12");
		EasyMock.expect(mockEmployeeService.getMarkedEmployees(Date.valueOf(employeeAction.getActiveTime())))
				.andReturn(null);
		EasyMock.replay(mockEmployeeService);
		employeeAction.setEmployeeService(mockEmployeeService);

		assertEquals("success", employeeAction.getMarkedEmployees());
	}

	@Test
	public void employeeActionReEditTestWithRole1() {

		EmployeeAction employeeAction = superTest();
		employeeAction.getSession().put("role", 1);
		assertEquals("success", employeeAction.reEdit());
	}

	@Test
	public void employeeActionReEditTestWithoutRole() {

		EmployeeAction employeeAction = superTest();
		assertEquals("error", employeeAction.reEdit());
	}

	@Test
	public void requestDetailTestWithoutRole() {

		EmployeeAction employeeAction = superTest();

		assertEquals("error", employeeAction.requestDetail());
	}

	@Test
	public void requestDetailTestWithoutRoleWithRequestId() {

		EmployeeAction employeeAction = superTest();
		employeeAction.getSession().put("detailRequestId", 1);
		employeeAction.getUnSelectedMarkedEmployees();
		employeeAction.getSelectedMarkedEmployees();
		employeeAction.getReEditInfoService();
		assertEquals("error", employeeAction.requestDetail());
	}

	@Test
	public void requestDetailTestWithoutRoleWithRequestId1() {

		EmployeeAction employeeAction = superTest();
		employeeAction.getSession().put("detailRequestId", 1);

		assertEquals("error", employeeAction.requestDetail());
	}

	private EmployeeAction superTest() {
		EmployeeAction employeeAction = new EmployeeAction();
		ReEditInfoService reEditInfoService = EasyMock.createMock(ReEditInfoServiceImpl.class);
		// EmployeeService employeeService =
		// EasyMock.createMock(EmployeeServiceImpl.class);

		Map<String, Object> session = new HashMap<String, Object>();
		session.put("name", "daniel.cao");
		employeeAction.setSession(session);

		ReEditInfo reEditInfo = new ReEditInfo();
		reEditInfo.setActivity("tt");
		reEditInfo.setTeamBuildingTime(Date.valueOf("2112-12-12"));

		List<Relation> reEditList = new ArrayList<Relation>();
		Relation relation = new Relation();
		relation.setEmployeeName("t");
		relation.setRequestId(1);
		reEditList.add(relation);

		reEditInfo.setRelationList(reEditList);

		Request requestInfo = new Request();
		requestInfo.setId(1);
		employeeAction.setRequestInfo(requestInfo);

		EasyMock.expect(reEditInfoService.getReEditInfo(1)).andReturn(reEditInfo);

		List<MarkedEmployee> markedEmployees = new ArrayList<MarkedEmployee>();
		Employee employee = new Employee();
		employee.setName("t");
		employee.setEmployeeId("t");

		MarkedEmployee markedEmployee = new MarkedEmployee();
		markedEmployee.setEligible(true);
		markedEmployee.setEmployee(employee);

		Employee employee2 = new Employee();
		employee2.setName("tt");
		employee2.setEmployeeId("tt");

		MarkedEmployee markedEmployee2 = new MarkedEmployee();
		markedEmployee2.setEligible(true);
		markedEmployee2.setEmployee(employee2);

		markedEmployees.add(markedEmployee);
		markedEmployees.add(markedEmployee2);

		EasyMock.expect(
				mockEmployeeService.getMarkedEmployees(Date.valueOf(reEditInfo.getTeamBuildingTime().toString())))
				.andReturn(markedEmployees);
		EasyMock.replay(mockEmployeeService);
		Map<String, List<MarkedEmployee>> map = new HashMap<String, List<MarkedEmployee>>();
		map.put("selectedMarkedEmployees", markedEmployees);
		map.put("unSelectedMarkedEmployees", markedEmployees);

		EasyMock.expect(reEditInfoService.cutMarkedList(markedEmployees, reEditList)).andReturn(map);
		EasyMock.replay(reEditInfoService);
		employeeAction.setReEditInfoService(reEditInfoService);
		employeeAction.setEmployeeService(mockEmployeeService);
		return employeeAction;
	}

}
