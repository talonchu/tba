package com.perficient.bcten.util.interceptor;

import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;
import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.Test;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;

public class LoginInterceptorTest extends EasyMockSupport {
	ActionContext actionContext = createMock(ActionContext.class);
	ActionInvocation invocation = createMock(ActionInvocation.class);

	@Test
	public void testInterceptWithNoName() {
		Map<String, Object> session = new HashMap<String, Object>();
		LoginInterceptor loginInterceptor = new LoginInterceptor();
		EasyMock.expect(invocation.getInvocationContext()).andReturn(actionContext);
		EasyMock.expect(actionContext.getSession()).andReturn(session);
		EasyMock.replay(invocation);
		EasyMock.replay(actionContext);
		assertEquals("toLogin", loginInterceptor.intercept(invocation));
	}

	@Test
	public void testInterceptWithName() throws Exception {
		Map<String, Object> session = new HashMap<String, Object>();
		session.put("name", "test");
		LoginInterceptor loginInterceptor = new LoginInterceptor();
		EasyMock.expect(invocation.getInvocationContext()).andReturn(actionContext);
		EasyMock.expect(invocation.invoke()).andReturn("success");
		EasyMock.expect(actionContext.getSession()).andReturn(session);
		EasyMock.replay(invocation);
		EasyMock.replay(actionContext);
		assertEquals("success", loginInterceptor.intercept(invocation));
	}

	@Test
	public void testInterceptWithNoSession() throws Exception {
		LoginInterceptor loginInterceptor = new LoginInterceptor();
		EasyMock.expect(invocation.getInvocationContext()).andReturn(actionContext);
		EasyMock.expect(invocation.invoke()).andReturn("success");
		EasyMock.expect(actionContext.getSession()).andReturn(null);
		EasyMock.replay(invocation);
		EasyMock.replay(actionContext);
		assertEquals("toLogin", loginInterceptor.intercept(invocation));
	}

	@Test
	public void testInterceptWithException() throws Exception {
		Map<String, Object> session = new HashMap<String, Object>();
		LoginInterceptor loginInterceptor = new LoginInterceptor();
		EasyMock.expect(invocation.getInvocationContext()).andReturn(actionContext);
		EasyMock.expect(actionContext.getSession()).andReturn(session);
		EasyMock.replay(invocation);
		EasyMock.replay(actionContext);
		assertEquals("toLogin", loginInterceptor.intercept(invocation));
	}

}
