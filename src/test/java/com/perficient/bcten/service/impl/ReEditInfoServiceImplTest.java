package com.perficient.bcten.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.Test;

import com.perficient.bcten.dao.ReEditInfoDao;
import com.perficient.bcten.dao.impl.ReEditInfoDaoImpl;
import com.perficient.bcten.entity.Employee;
import com.perficient.bcten.entity.Relation;
import com.perficient.bcten.model.MarkedEmployee;
import com.perficient.bcten.model.ReEditInfo;

public class ReEditInfoServiceImplTest extends EasyMockSupport {
	private ReEditInfoServiceImpl reEditInfoServiceImpl;
	private ReEditInfoDao reEditInfoDao = EasyMock.createMock(ReEditInfoDaoImpl.class);

	@Test
	public void ReEditServiceImplSuccessTest() {
		reEditInfoServiceImpl = new ReEditInfoServiceImpl();
		ReEditInfo reEditInfo = new ReEditInfo();
		reEditInfo.setActivity("testActivity");
		reEditInfo.setPurpose("testPurpose");
		EasyMock.expect(reEditInfoDao.findReEditInfoByRequestId(1)).andReturn(reEditInfo);
		EasyMock.replay(reEditInfoDao);
		reEditInfoServiceImpl.setReEditInfoDao(reEditInfoDao);
		reEditInfoServiceImpl.getReEditInfoDao();
		assertEquals(reEditInfo, reEditInfoServiceImpl.getReEditInfo(1));
	}

	@Test
	public void ReEditServiceImplExceptionTest() {
		boolean flag = false;
		reEditInfoServiceImpl = new ReEditInfoServiceImpl();
		EasyMock.expect(reEditInfoDao.findReEditInfoByRequestId(1)).andReturn(null);
		EasyMock.replay(reEditInfoDao);
		reEditInfoServiceImpl.setReEditInfoDao(reEditInfoDao);
		try {
			reEditInfoServiceImpl.getReEditInfo(1);
		} catch (Exception e) {
			flag = true;
		}
		assertEquals(true, flag);
	}

	@Test
	public void ReEditServiceImplCutMarkedListTest() {
		reEditInfoServiceImpl = new ReEditInfoServiceImpl();
		List<MarkedEmployee> markedEmployees = new ArrayList<MarkedEmployee>();
		List<Relation> reEditList = new ArrayList<Relation>();

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

		Relation relation = new Relation();
		relation.setEmployeeName("t");
		relation.setRequestId(1);

		markedEmployees.add(markedEmployee);
		markedEmployees.add(markedEmployee2);
		reEditList.add(relation);

		assertEquals(1, reEditInfoServiceImpl.cutMarkedList(markedEmployees, reEditList).get("selectedMarkedEmployees")
				.size());
		assertEquals(1,
				reEditInfoServiceImpl.cutMarkedList(markedEmployees, reEditList).get("unSelectedMarkedEmployees")
						.size());

	}

}
