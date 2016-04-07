<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.datastax.driver.core.Row" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Unregistered Drivers</title>
</head>
<body>
	<h1>List of pending approvals</h1>
	<%
	if(request.getAttribute("results") != null) {
		List<Row> results = (List<Row>) request.getAttribute("results");
		for (Row r : results) {
			out.println("<br /><pre>"
					+ " <strong>Username:</strong> " + r.getString("username")
					+ " <strong>Car No:</strong> " + r.getString("car_no")
					+ " <strong>First Name:</strong> " + r.getString("first_name")
					+ " <strong>Last Name:</strong> " + r.getString("last_name")
					+ " <strong>License No:</strong> " + r.getString("license_no") 
					+ " <strong>Mobile No:</strong> " + r.getString("mobile_no")
					+ " 	<a href = \"/RadioTaxiProject-Release/authorisedriver?username=" 
										// change this to avoid using absolute path
					+ r.getString("username") + "\">Approve</a>"
					+ "</pre>");
		}
	}
	%>
</body>
</html>