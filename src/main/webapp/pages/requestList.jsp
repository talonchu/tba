<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>TeamBuilding</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" href="css/common.css" type="text/css" />
<link type="text/css" rel="stylesheet" href="css/blitzer/jquery-ui.css" />
<link type="text/css" href="css/ui.multiselect.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/requestlist.js"></script>
<script type="text/javascript">
	function sortCommit(column){
		$("#loadingdiv").css("display","block");
		$("#hiddendiv").css("display","block");
		var sortStatus;
		if(${sortStatus} == 0){
			sortStatus = 1;
		}else{
			sortStatus = 0;
		}
		if("${column}" != column){
			sortStatus = 1;
		}
		document.requestListForm.action="requestListAction?status=${status}&page.curPage=1&sortStatus="+sortStatus+"&column="+column;
		document.requestListForm.target="_self";
		document.requestListForm.submit();
	}
	$(function(){
		if("$(status)" == '1'){
			$("#approval_li").addClass("selected");
		}else
		{
			$("#pending_li").addClass("selected");
		}	
	});
</script>
<style>
#hiddendiv {
	width: 80%;
	height: 80%;
	z-index: 400;
	position: absolute;
	left: 3%;
}
</style>
</head>
<body>
	<form name="requestListForm" id="requestListForm" action=""
		method="post">
		<div style="display: none" id="hiddendiv"></div>
		<div style="display: none; left: 45%" id="loadingdiv">
			<img src="img/loading45.gif" />
		</div>
		<center>
			<div id="wrap">
				<div class="header">
					<div class="logInfo">
						Welcome&nbsp;&nbsp;&nbsp;<span>${name}&nbsp;&nbsp;&nbsp;</span> <a
							href="logoutAction">Log
							out&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
					</div>

					<div id="menu" align="left">
						<ul>
							<li><a href="employeeAction">Request&nbsp;&nbsp;</a></li>
							<li id="pending_li"><a href="requestListAction?status=0">Pending
									Requests&nbsp;&nbsp;</a></li>
							<li id="approval_li"><a href="requestListAction?status=1">Approval
									History&nbsp;&nbsp;</a></li>
						</ul>
					</div>
				</div>
				<div class="center_content">
					<div
						style="color: red; font-size: 14px; font-weight: bold; height: 18px; text-align: left;"></div>
					<table class="reqls_tb">
						<tr>
							<th width="100px">Requester</th>
							<th width="150px" onclick="sortCommit('requestTime')">Request
								Time</th>
							<th width="100px" onclick="sortCommit('totalNumber')">Total
								Participants</th>
							<th width="120px" onclick="sortCommit('totalCost')">Maximum
								Amount</th>
							<th width="150px" onclick="sortCommit('teamBuildingTime')">Team
								Building Time</th>
							<th width="160px" onclick="sortCommit('requestStatus')">Status</th>
							<th width="220px"></th>
						</tr>
						<c:forEach var="requestModel" items="${approveList}">
							<tr rid="${requestModel.request.id }"
								onclick="getDetail(${requestModel.request.id })">
								<td>${requestModel.request.requesterName }</td>
								<td><fmt:formatDate
										value="${requestModel.request.requestTime}" type="both"
										pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
								<td>${requestModel.request.totalNumber }</td>
								<td>${requestModel.request.totalCost }</td>
								<td>${requestModel.request.teamBuildingTime }</td>
								<td><span
									style="
										<c:if test="${requestModel.request.requestStatus == 'APPROVED'}">color: green">approved</c:if>
										<c:if
											test="${requestModel.request.requestStatus == 'DISAPPROVED'}">color: red">rejected</c:if>
										<c:if
											test="${requestModel.request.requestStatus == 'WAITING'}">color: blue">submitted</c:if>
								</span></td>
								<td>
									<div id="operation">
										<c:if test="${role == 1}">
											<c:if
												test="${requestModel.request.requestStatus == 'WAITING'}">
										&nbsp;<input class="req_btn" type="button" value="approve"
													onclick="approveBtn(${requestModel.request.id })" />
												&nbsp;&nbsp;&nbsp;
											<input class="req_btn" type="button" value="reject"
													onclick="rejectBtn(${requestModel.request.id })" />
											</c:if>
											<c:if
												test="${requestModel.request.requestStatus == 'APPROVED'}">
												<input class="req_btn" type="button" value="repeal"
													onclick="repealBtn(${requestModel.request.id })" />
											</c:if>
										</c:if>
										<c:if test="${requestModel.flag == true}">
											<input class="req_btn" type="button" value="edit"
												onclick="editBtn(${requestModel.request.id })" />&nbsp;
										</c:if>
									</div>
									<div id="requester"></div>
									<div id="participant"></div>
								</td>
							</tr>
						</c:forEach>
					</table>
					<br />
					<div style="width: 885px; border: none; text-align: right;"
						class="reqls_page">
						<a
							href="requestListAction?status=${status }&page.curPage=1&sortStatus=${sortStatus }&column=${column}"><img
							src="img/top_btn.gif" alt="top" width="14px" height="14px" /></a>&nbsp;&nbsp;<a
							href="requestListAction?status=${status }&page.curPage=${page.curPage-1}&sortStatus=${sortStatus }&column=${column}"><img
							src="img/prev_btn.gif" alt="prev" width="14px" height="14px" /></a>
						&nbsp;&nbsp;&nbsp;&nbsp;Page&nbsp;&nbsp;<span>${page.curPage}</span>&nbsp;&nbsp;of&nbsp;&nbsp;
						<span>${page.totalPage}</span>&nbsp;&nbsp;&nbsp;&nbsp; <a
							href="requestListAction?status=${status }&page.curPage=${page.curPage+1}&page.totalPage=${page.totalPage}&sortStatus=${sortStatus }&column=${column}"><img
							src="img/next_btn.gif" alt="next" width="14px" height="14px" /></a>&nbsp;&nbsp;<a
							href="requestListAction?status=${status }&page.curPage=${page.totalPage}&page.totalPage=${page.totalPage}&sortStatus=${sortStatus }&column=${column}"><img
							src="img/end_btn.gif" alt="end" width="14px" height="14px" /></a>
					</div>
					<div id="lock_div" class="lock_div"></div>
					<!--end of center content-->
					<div class="footer">
						<br /> <br />
						<hr width="500px" />
						Team Building (<a href="https://gdcgit.perficient.com/gdc-tools/team-building/tags/${version}" target="_blank">${version}</a>)
						Perficient © 2017. All rights Reserved.
						<a href="mailto:ChinaGDCTools@perficient.com?subject=Report a bug on Team Building v${version}">Report an Issue</a>
					</div>
				</div>
			</div>
		</center>
	</form>
</body>
</html>