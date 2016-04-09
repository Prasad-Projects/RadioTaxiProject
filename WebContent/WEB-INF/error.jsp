<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error</title>
</head>
<body> 
<%
	if(request.getAttribute("errMsg") != null) {
		String errMsg = (String)request.getAttribute("errMsg");
		if(errMsg.compareTo("accessdenied") == 0) {
			out.println("<font color=red>Access denied</font>");
		}
		else {
			out.println("<font color=red>An unexpected error occured. Please try again later.</font><br />");
			out.println(errMsg);	
		}
	}
	else {
		out.println("<font color=red>An unexpected error occured. Please try again later.</font>");
	}
%>
</body>
</html>
