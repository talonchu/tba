package com.perficient.bcten.util.interceptor;

import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.Test;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;

public class EmailInterceptorTest extends EasyMockSupport {
	ActionContext actionContext = createMock(ActionContext.class);
	ActionInvocation invocation = createMock(ActionInvocation.class);
	Map<String, Object> parameters = new HashMap<String, Object>();

	@Test
	public void testInterceptWithNoName() {
		Map<String, Object> session = new HashMap<String, Object>();
		EmailInterceptor emailInterceptor = new EmailInterceptor();
		EasyMock.expect(invocation.getInvocationContext()).andReturn(actionContext);
		EasyMock.expect(actionContext.getParameters()).andReturn(parameters);
		EasyMock.expect(actionContext.getSession()).andReturn(session);
		EasyMock.replay(invocation);
		EasyMock.replay(actionContext);
		assertEquals("toLogin", emailInterceptor.intercept(invocation));
	}

	@Test
	public void testInterceptWithName() throws Exception {
		Map<String, Object> session = new HashMap<String, Object>();
		session.put("name", "test");

		String[] arrStrings = new String[1];
		arrStrings[0] = "fafa";
		parameters.put("requestId", arrStrings);
		EmailInterceptor emailInterceptor = new EmailInterceptor();
		EasyMock.expect(invocation.getInvocationContext()).andReturn(actionContext);
		EasyMock.expect(invocation.invoke()).andReturn("success");
		EasyMock.expect(actionContext.getSession()).andReturn(session);
		EasyMock.expect(actionContext.getParameters()).andReturn(parameters);
		EasyMock.replay(invocation);
		EasyMock.replay(actionContext);
		assertEquals("success", emailInterceptor.intercept(invocation));
	}

}
