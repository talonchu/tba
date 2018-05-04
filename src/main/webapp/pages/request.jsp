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
	function dateChange() {
		var date = document.getElementById("text_date").value;
		var purpose = document.getElementById("purpose").value;
		var activity = document.getElementById("activity").value;

		location.replace("employeeAction?activeTime=" + date
				+ "&requestInfo.activity=" + activity + "&requestInfo.purpose="
				+ purpose);

	}
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
					Requester:<span>${name}</span>
				</div>
				<form action="requestAction" method="post">
					<table class="req_tb">
						<tr>
							<td width="225" align="right" align="center">Team Building
								Time</td>
							<td align="left"><input type="text" name="activeTime"
								onchange="dateChange()" value="${activeTime}" maxlength="100"
								class="text_calendar" id="text_date"
								onclick="SelectDate(this,'yyyy-MM-dd');" readonly="true"
								style="width: 223px; cursor: pointer" /> <span id="dateMsg"
								style="color: red"></span></td>
						</tr>
						<tr>
							<td width="225" align="right">Purpose</td>
							<td align="left"><textarea name="request.purpose"
									id="purpose"
									style="width: 458px; max-width: 458px; max-height: 80px"
									onclick="cleanPurposeMsg();" rows="5" cols="60">${requestInfo.purpose}</textarea>
								<span id="purposeMsg" style="color: red"></span></td>
						</tr>
						<tr>
							<td width="225" align="right">Activity</td>
							<td align="left"><textarea name="request.activity"
									id="activity"
									style="width: 458px; max-width: 458px; max-height: 80px"
									onclick="cleanActivityMsg();" rows="5" cols="60">${requestInfo.activity}</textarea>
								<span id="activityMsg" style="color: red"></span></td>
						</tr>
						<tr>
							<td width="225" align="right" id="before_markEmp">Participants</td>
							<td id="markEmp" align="left">
								<!--  drag and drop box--> <span id="content"> <select
									onchange="cleanSelectMsg();" id="countries" class="multiselect"
									multiple="multiple" name="selectedArr">
										<c:forEach items="${markedEmployeeList}" var="emp">
											<c:choose>
												<c:when test="${emp.isEligible}">

													<option value='${emp.employee.name}' title="eligible">
														${emp.employee.name}</option>
												</c:when>

												<c:otherwise>
													<option value='${emp.employee.name}' title="ineligible">
														${emp.employee.name}</option>
												</c:otherwise>

											</c:choose>

										</c:forEach>
								</select> <span id="selectMsg" style="color: red"></span>
							</span>
							</td>
						</tr>
					</table>
					<div class="req_info">
						Total Participants: <span id="pesNum">0</span>&nbsp;&nbsp;&nbsp;&nbsp;
						Maximum Reimbursable Amount: <span id="sumBudget">0.0</span>
					</div>
					<div align="center">
						<input type="submit" class="req_btn" value="submit" id="confirm"
							onclick="return isOk(500);" />
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