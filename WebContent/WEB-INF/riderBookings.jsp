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
		String user = (String) session.getAttribute("user");
	%>
<!-- TODO: add conditional to display below message only when booking succesful -->
<p align="center"><font color=green>Booking successful!</font></p><br />
Display rider bookings here.
<form action="profile" method="get">
        <button id="profile_in_button" type="submit" value="profile">My Profile</button>
</form>
</body>
</html>