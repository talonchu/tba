<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>TeamBuilding</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/requestlist.js"></script>
</head>
<body>
	<center>
		<div id="wrap">
			<c:if test="${name!=null}">
			<div class="logInfo">
				Welcome&nbsp;&nbsp;&nbsp;<span>${name}&nbsp;&nbsp;&nbsp;</span> <a
					href="logoutAction">Log out&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
			</div>
			</c:if>
			<div class="header">

				<div id="menu">
					<ul>
						<li class="selected"><a href="employeeAction">Request&nbsp;&nbsp;</a></li>
						<li><c:if test="${name!=null}" var="rs">
								<a href="requestListAction">RequestList&nbsp;&nbsp;</a>
							</c:if> 
							<c:if test="${!rs}">
								<a href="pages/login.jsp">RequestList&nbsp;&nbsp;</a>
							</c:if></li>
					</ul>

				</div>

			</div>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>

			<span style="font-size:18px;font-weight:bold;">
			System error!
			
			</span>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<div class="footer">
				<br /> <br />
						<hr width="500px" />
						Team Building (<a href="https://gdcgit.perficient.com/gdc-tools/team-building/tags/${version}" target="_blank">${version}</a>)
						Perficient © 2017. All rights Reserved.
						<a href="mailto:ChinaGDCTools@perficient.com?subject=Report a bug on Team Building v${version}">Report an Issue</a>
			</div>
		</div>
	</center>
</body>
</html>