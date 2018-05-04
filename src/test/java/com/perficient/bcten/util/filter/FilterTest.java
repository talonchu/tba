package com.perficient.bcten.util.filter;

import static org.junit.Assert.assertEquals;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.Test;

import com.perficient.bcten.util.filter.TBAFilter;

public class FilterTest extends EasyMockSupport {

	private HttpServletRequest request = createMock(HttpServletRequest.class);
	private HttpServletResponse response = createMock(HttpServletResponse.class);
	private FilterChain chain = createMock(FilterChain.class);
	private TBAFilter tbaFilter;
	private HttpSession session = createMock(HttpSession.class);

	@Test
	public void isLoginJSP() throws Exception {
		tbaFilter = new TBAFilter();
		tbaFilter.init(null);
		EasyMock.expect(request.getRequestURI()).andReturn("loginAction");
		EasyMock.expect(request.getSession()).andStubReturn(session);
		EasyMock.expect(request.getRequestDispatcher("/pages/login.jsp")).andStubReturn(null);
		EasyMock.replay(request);
		tbaFilter.setRequest(request);
		tbaFilter.doFilter(request, response, chain);
		assertEquals(false, tbaFilter.doFilterOrNot());
	}

	@Test
	public void isLoginJSP2() throws Exception {
		tbaFilter = new TBAFilter();
		tbaFilter.init(null);
		EasyMock.expect(request.getRequestURI()).andReturn("loginAction.css");
		EasyMock.expect(request.getSession()).andStubReturn(session);
		EasyMock.expect(request.getRequestDispatcher("/pages/login.jsp")).andStubReturn(null);
		EasyMock.replay(request);
		tbaFilter.setRequest(request);
		tbaFilter.doFilter(request, response, chain);
		assertEquals(false, tbaFilter.doFilterOrNot());
	}

	@Test
	public void isLoginJSP3() throws Exception {
		tbaFilter = new TBAFilter();
		tbaFilter.init(null);
		EasyMock.expect(request.getRequestURI()).andReturn("loginAction.js");
		EasyMock.expect(request.getSession()).andStubReturn(session);
		EasyMock.expect(request.getRequestDispatcher("/pages/login.jsp")).andStubReturn(null);
		EasyMock.replay(request);
		tbaFilter.setRequest(request);
		tbaFilter.doFilter(request, response, chain);
		assertEquals(false, tbaFilter.doFilterOrNot());
	}

	@Test
	public void isLoginJSP4() throws Exception {
		tbaFilter = new TBAFilter();
		tbaFilter.init(null);
		EasyMock.expect(request.getRequestURI()).andReturn("loginAction.jpg");
		EasyMock.expect(request.getSession()).andStubReturn(session);
		EasyMock.expect(request.getRequestDispatcher("/pages/login.jsp")).andStubReturn(null);
		EasyMock.replay(request);
		tbaFilter.setRequest(request);
		tbaFilter.doFilter(request, response, chain);
		assertEquals(false, tbaFilter.doFilterOrNot());
	}

	@Test
	public void isLoginJSP5() throws Exception {
		tbaFilter = new TBAFilter();
		tbaFilter.init(null);
		EasyMock.expect(request.getRequestURI()).andReturn("loginAction.gif");
		EasyMock.expect(request.getSession()).andStubReturn(session);
		EasyMock.expect(request.getRequestDispatcher("/pages/login.jsp")).andStubReturn(null);
		EasyMock.replay(request);
		tbaFilter.setRequest(request);
		tbaFilter.doFilter(request, response, chain);
		assertEquals(false, tbaFilter.doFilterOrNot());
	}

	@Test
	public void isLoginJSP6() throws Exception {
		tbaFilter = new TBAFilter();
		tbaFilter.init(null);
		EasyMock.expect(request.getRequestURI()).andReturn("loginAction.png");
		EasyMock.expect(request.getSession()).andStubReturn(session);
		EasyMock.expect(request.getRequestDispatcher("/pages/login.jsp")).andStubReturn(null);
		EasyMock.replay(request);
		tbaFilter.setRequest(request);
		tbaFilter.doFilter(request, response, chain);
		assertEquals(false, tbaFilter.doFilterOrNot());
	}

	@Test
	public void isDestroy() {
		tbaFilter = new TBAFilter();
		tbaFilter.destroy();
	}

	@Test
	public void isInit() throws ServletException {
		tbaFilter = new TBAFilter();
		tbaFilter.init(null);
	}

	@Test
	public void getSetRequest() {
		tbaFilter = new TBAFilter();
		tbaFilter.setRequest(request);
		assertEquals(request, tbaFilter.getRequest());
	}

	@Test
	public void isSetFlag() {
		tbaFilter = new TBAFilter();
		tbaFilter.setFlag(false);
		assertEquals(false, tbaFilter.isFlag());
	}
}
