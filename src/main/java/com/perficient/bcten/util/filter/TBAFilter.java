package com.perficient.bcten.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class TBAFilter implements Filter {
	private HttpServletRequest request;
	private boolean flag = false;;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
			ServletException {
		request = (HttpServletRequest) req;
		HttpSession session = request.getSession();
		Object name = session.getAttribute("name");
		String uri = request.getRequestURI();
		if (uri.indexOf("login.jsp") == -1 && !isConfigPages(uri) && name == null) {
			RequestDispatcher temp = request.getRequestDispatcher("/pages/login.jsp");
			if (temp != null) {
				temp.forward(req, res);
				flag = true;
			}
		}
		chain.doFilter(req, res);
	}

	public boolean doFilterOrNot() {

		return flag;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	private boolean isConfigPages(String url) {
		boolean a = url.endsWith(".css");
		boolean b = url.endsWith(".js") || url.endsWith(".gif") || url.endsWith(".jpg") || url.endsWith(".png");
		return a || b;
	}
}
