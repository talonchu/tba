
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>TeamBuilding</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/request.js"></script>
<link rel="stylesheet" href="css/common.css" type="text/css" />
<link type="text/css" rel="stylesheet" href="css/blitzer/jquery-ui.css" />
<link type="text/css" href="css/ui.multiselect.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="js/plugins/localisation/jquery.localisation-min.js"></script>
<script type="text/javascript"
	src="js/plugins/scrollTo/jquery.scrollTo-min.js"></script>
<script type="text/javascript" src="js/ui.multiselect.js"></script>
<script type="text/javascript" src="js/themeswitch.js"></script>
<script type="text/javascript" src="js/webcalendar.js"></script>
<script type="text/javascript" src="js/requestlist.js"></script>
<script type="text/javascript">
	$(function() {
		$.localise('ui-multiselect', {/*language: 'en',*/
			path : 'js/locale/'
		});
		$(".multiselect").multiselect();
		$('#switcher').themeswitcher();
	});
</script>
<script type="text/javascript">
	
</script>
</head>
<body>
	<center>
		<div id="wrap">
			<div class="header">
				<div class="logInfo">
					Welcome&nbsp;&nbsp;&nbsp;<span>${name}&nbsp;&nbsp;&nbsp;</span> <a
						href="logoutAction">Log out&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
				</div>
				<div id="menu">
					<ul>
						<li class="selected"><a href="employeeAction">Request&nbsp;&nbsp;</a></li>
						<li><a href="requestListAction?status=0">Pending Requests&nbsp;&nbsp;</a></li>
						<li><a href="requestListAction?status=1">Approval History&nbsp;&nbsp;</a></li>
					</ul>
				</div>

			</div>

			<div class="center_content">
				<div class="req_info">
					Requester:<span>${requestInfo.requesterName}</span>
					<c:if test="${requestInfo.requestStatus == 'APPROVED'}">
						<a>Approved by ${requestInfo.approver} at <fmt:formatDate
								value="${requestInfo.approvedTime}" type="both"
								pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
						</a>
					</c:if>
				</div>

				<input type="hidden" id="requestInfo" value="${requestInfo.id }" />
				<form method="post" id="requestListForm">
					<table class="req_tb">
						<tr>
							<td width="225" align="right" align="center">Team Building
								Time</td>
							<td align="left"><input type="text" name="activeTime"
								disabled="disabled" onchange="reEditdateChange()"
								value="${activeTime}" maxlength="100" class="text_calendar"
								id="text_date" onclick="SelectDate(this,'yyyy-MM-dd');"
								readonly="true" style="width: 223px; cursor: pointer" /> <span
								id="dateMsg" style="color: red"></span></td>
						</tr>
						<tr>
							<td width="225" align="right">Purpose</td>
							<td align="left"><textarea name="request.purpose"
									id="purpose" disabled="disabled"
									style="width: 458px; max-width: 458px; max-height: 80px;"
									rows="5" cols="60">${requestInfo.purpose}</textarea> <span
								id="reasonMsg" style="color: red"></span></td>
						</tr>
						<tr>
							<td width="225" align="right">Activity</td>
							<td align="left"><textarea name="request.activity" rows="5"
									cols="60" id="activity" disabled="disabled"
									style="width: 458px; max-width: 458px; max-height: 80px;">${requestInfo.activity}</textarea></td>
						</tr>
						<tr>
							<td width="225" align="right" id="before_markEmp">Participants</td>
							<td id="markEmp" align="left">
								<!--  drag and drop box 
							<span id="content">
								 <select
									id="countries" class="multiselect" multiple="multiple"
									name="selectedArr">
										<c:forEach items="${selectedMarkedEmployees}" var="semp">

											<option value='${semp.employee.name}' selected="selected"
												title="eligible">${semp.employee.name}</option>

										</c:forEach>
								</select>
							
							</span>
							--> <span id="content">
									
									<table  border="1" width="460" height="200">
									<c:choose>
										<c:when test="${fn:length(selectedMarkedEmployees)>40}">	
										<c:forEach items="${selectedMarkedEmployees}" var="semp" step="4" varStatus="s" >
											<tr  >	
												<td valign= "top" width="80" height="1">${selectedMarkedEmployees[s.index].employee.name}</td>
												<td valign= "top" width="80" height="1">${selectedMarkedEmployees[s.index+1].employee.name}</td>
												<td valign= "top" width="80" height="1">${selectedMarkedEmployees[s.index+2].employee.name}</td>
												<td valign= "top" width="80" height="1">${selectedMarkedEmployees[s.index+3].employee.name}</td>
											</tr>
										</c:forEach>
										</c:when>
										<c:otherwise>
											
										<c:forEach var="s" begin="0" end="9"  step="1">
											<tr valign= "top" >	
												<td valign= "top" width="80" height="1">${selectedMarkedEmployees[s*4].employee.name}</td>
												<td valign= "top" width="80" height="1">${selectedMarkedEmployees[s*4+1].employee.name}</td>
												<td valign= "top" width="80" height="1">${selectedMarkedEmployees[s*4+2].employee.name}</td>
												<td valign= "top" width="80" height="1">${selectedMarkedEmployees[s*4+3].employee.name}</td>
											</tr>
										</c:forEach>
										
										 </c:otherwise>	
									</c:choose>	
									</table>
							</span>
							</td>
						</tr>
					</table>
					<div class="req_info">
						Total Participants: <span id="pesNum">${requestInfo.totalNumber }</span>&nbsp;&nbsp;&nbsp;&nbsp;
						Maximum Reimbursable Amount: <span id="sumBudget">${requestInfo.totalCost }</span>
					</div>
					<div align="center">
						<c:if test="${role == 1}">
							<c:if test="${requestInfo.requestStatus == 'WAITING'}">
									&nbsp;
								<input class="req_btn" type="button" value="approve"
									onclick="approveBtn(${requestInfo.id })" />
									&nbsp;&nbsp;&nbsp;
								<input class="req_btn" type="button" value="reject"
									onclick="rejectBtn(${requestInfo.id })" />
							</c:if>
						</c:if>
					</div>
				</form>

			</div>

			<!--end of center content-->
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