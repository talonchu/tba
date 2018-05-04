package com.perficient.bcten.util.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;

import com.perficient.bcten.service.UserService;

public class MyCasValidatorFilter extends Cas20ProxyReceivingTicketValidationFilter {

	private UserService userService = null;

	@Override
	protected void onSuccessfulValidation(HttpServletRequest request, HttpServletResponse response, Assertion assertion) {
		
		AttributePrincipal principal = assertion.getPrincipal();
		HttpSession session = request.getSession();
		String username = principal.getName();
		session.setAttribute("name", username);
		int role = userService.getRole(username);
		session.setAttribute("role", role);
		session.setAttribute("version", userService.getVersion());
		super.onSuccessfulValidation(request, response, assertion);
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
