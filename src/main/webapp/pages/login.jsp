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
       
       
       <table width="850" height="450" border="0" rules=none >
       <tr>
       	<td>
       		<img width="500" height="400" src="img/log_right.jpg" />
       	</td>
       	<td>
       		 <form name="loginForm" action="loginAction" method="post" onSubmit="return loginCheck();" >
   			 <table width="90%" border="1" rules=none align="center" cellpadding="0" cellspacing="0" class="login_table">
     		    <tr>
        		    <td height="35" align="right">
        		    	<font size="2" ><b>&nbsp;&nbsp;Username:&nbsp;&nbsp;</b></font>
        		    </td>
          			<td><input name="username" style="width:150px;" class="login_input" id="username" value="${username}"/>
          			</td>
     			</tr>
       			<tr>
      			    <td height="35" align="right" >
      			    <font size="2" ><b>&nbsp;&nbsp;Password:&nbsp;&nbsp;</b></font>
      			    </td>
         			<td><input name="password" style="width:150px;" id="password" type="password" class="login_input" id="password" /></td>
        		</tr>
     		    <tr><td colspan="2" style="color:red;text-align:center;"><a id="errorMsg" >${msg}</a></td>
     			</tr>
       			<tr>
           			<td height="40" colspan="2" align="center">
           				<input type="submit" id="Submit" value="Login" class="login_btn"/>  &nbsp;&nbsp;
           				<input type="reset" id="Reset" value="Reset" class="login_btn" />
           			</td>
        		</tr>
   			 </table>
   			 </form>
       	</td>
       </tr>

       </table>
       
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