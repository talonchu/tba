package com.perficient.bcten.action;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.Test;

import com.perficient.bcten.service.UserService;
import com.perficient.bcten.service.impl.UserServiceImpl;
import com.perficient.bcten.util.LDAPconnect;

public class UserActionTest extends EasyMockSupport {

	private UserAction userAction;
	private UserService userService = createMock(UserService.class);
	private UserServiceImpl userServiceImpl;
	private LDAPconnect ldap = createMock(LDAPconnect.class);
	private Map<String, Object> session;

	@Test
	public void LoginWithRightAccounts1() {
		LoginWithRightAccounts(1);
	}

	@Test
	public void LoginWithRightAccounts2() {
		LoginWithRightAccounts(2);
	}

	public void LoginWithRightAccounts(int role) {
		userAction = new UserAction();
		userAction.setUsername("username");
		userAction.setPassword("password");
		userAction.setSession(session);
		EasyMock.expect(userService.login(userAction.getUsername(), userAction.getPassword())).andReturn(true);
		if (role == 2) {
			EasyMock.expect(userService.getRole("username")).andReturn(role);
		}
		EasyMock.expect(userService.getRole("username")).andReturn(role);
		EasyMock.replay(userService);
		session = new HashMap<String, Object>();
		session.put("username", "username");
		userAction.setSession(session);
		userAction.getSession();
		userAction.setUserService(userService);
		assertEquals("success", userAction.login());
	}

	@Test
	public void LoginWithWrongAccounts() {
		userAction = new UserAction();
		userAction.setUsername("username");
		userAction.setPassword("password");
		userAction.setSession(session);
		EasyMock.expect(userService.login("username", "password")).andReturn(false);
		EasyMock.replay(userService);
		session = new HashMap<String, Object>();
		session.put("username", "username");
		userAction.setSession(session);
		userAction.setUserService(userService);
		assertEquals("error", userAction.login());
	}

	@Test
	public void LoginWithUnknowButRightAccount() {
		userServiceImpl = new UserServiceImpl();
		EasyMock.expect(ldap.getAuthentication("aaa", "123")).andReturn(true);
		EasyMock.replay(ldap);
		userServiceImpl.setLdap(null);
		userServiceImpl.getLdap();
		assertEquals(false, userServiceImpl.login("aaa", "123"));
		userServiceImpl.setLdap(ldap);
		userServiceImpl.getLdap();
		assertEquals(true, userServiceImpl.login("aaa", "123"));
	}

	@Test
	public void LoginWithUnknowButWrongAccount() {
		userServiceImpl = new UserServiceImpl();
		EasyMock.expect(ldap.getAuthentication("aaa", "124")).andReturn(false);
		EasyMock.replay(ldap);
		userServiceImpl.setLdap(ldap);
		assertEquals(false, userServiceImpl.login("aaa", "124"));
	}

	@Test
	public void setAndGetMsg() {
		UserAction userAction = new UserAction();
		userAction.setMsg("hello");
		assertEquals("hello", userAction.getMsg());
	}

	@Test
	public void setAndGetUserService() {
		UserService userService = EasyMock.createMock(UserService.class);
		UserAction userAction = new UserAction();
		EasyMock.expect(userService.login("aaa", "124")).andReturn(true);
		EasyMock.replay(userService);
		userAction.setUserService(userService);
		assertEquals(true, userAction.getUserService().login("aaa", "124"));
	}

	@Test
	public void logoutTest() {
		Map<String, Object> session = new HashMap<String, Object>();
		UserAction userAction = new UserAction();
		userAction.setSession(session);
		assertEquals("success", userAction.logout());
	}

}
