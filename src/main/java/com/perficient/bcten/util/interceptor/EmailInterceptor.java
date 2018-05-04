package com.perficient.bcten.util.interceptor;

import java.util.Map;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class EmailInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 4621025076646770313L;
	public static final String USER_SESSION_KEY = "name";

	@Override
	public String intercept(ActionInvocation invocation) {

		ActionContext actionContext = invocation.getInvocationContext();
		Map<String, Object> session = actionContext.getSession();
		Map<String, Object> parameters = actionContext.getParameters();
		String[] arrStrings = (String[]) parameters.get("requestId");
		String requestId = null;
		if (arrStrings != null) {
			requestId = arrStrings[0];
			session.put("requestId", requestId);
		}
		if (session != null && session.get(USER_SESSION_KEY) != null) {
			try {
				return invocation.invoke();
			} catch (Exception e) {
				return "toLogin";
			}
		}
		return "toLogin";
	}

}