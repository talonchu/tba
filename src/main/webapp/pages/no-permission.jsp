<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>TeamBuilding</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/loginCheck.js"></script>

</head>
<body>
<center>
<div id="wrap">
       <div class="header">
       </div> 
       
       
       <div class="center_content">
		   <div id="error" style="font-size:25px;color:red;">
			You don't have permission to view it.
		   </div>
		   <div id="link" style="font-size:25px;margin-top:10px;">
			Return to <A href="">HOME</A>
		   </div>
       </div><!--end of center content-->
        
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