<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My bookings</title>
</head>
<body>
	<%
		String user = null;
		if (session.getAttribute("type") == null) {
			response.sendRedirect("index.html");
		} else if(session.getAttribute("type").toString().compareTo("rider") != 0) {
			response.sendRedirect("error.jsp");
		} else
			user = (String) session.getAttribute("user");
	 %>
Display rider bookings here.
<a href="riderProfile.jsp">My Profile</a>
</body>
</html>