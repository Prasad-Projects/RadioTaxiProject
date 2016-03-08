<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profile</title>
</head>
<body>
	<%
		//allow access only if session exists
		String user = null;
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect("index.html");
		} else
			user = (String) session.getAttribute("user");
		/*
		String userName = null;
		String sessionID = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("user"))
					userName = cookie.getValue();
				if (cookie.getName().equals("JSESSIONID"))
					sessionID = cookie.getValue();
			}
		}
		*/
	%>
	<h3>
		Hi!
		<%=user%>, Login successful.
	</h3>

	<br>
	<a href="checkoutPage.jsp">Checkout Page</a><br />
	<a href="/RadioTaxiProject-Release-1/displayQueuedRides">Get Rides!</a>
	<form action="Logout" method="post">
		<input type="submit" value="Logout">
	</form>

</body>
</html>