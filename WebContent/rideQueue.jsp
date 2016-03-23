<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.datastax.driver.core.Row" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rides nearby</title>
</head>
<body>
Display nearby rides which are not matched with a driver here.
	<%
		//allow access only if session exists
		String user = null;
		if (session.getAttribute("type") == null) {
			response.sendRedirect("index.html");
		} else if(session.getAttribute("type").toString().compareTo("driver") != 0) {
			response.sendRedirect("error.jsp");
		} else
			user = (String) session.getAttribute("user");
		
		if(request.getAttribute("results") != null) {
			List<Row> results = (List<Row>) request.getAttribute("results");
			for (Row r : results) {
				out.println("<br /><pre><a href = \"/RadioTaxiProject-Release-1/ConfirmMatch?booking_id="
						+ r.getInt("booking_id") + "\">" + // TODO
						r.getInt("booking_id") + "</a>" + " <strong>Rider:</strong> " + r.getString("rider") + " <strong>Origin:</strong> " + r.getString("origin") + " <strong>Destination:</strong> "
						+ r.getString("destination") + " <strong>Time:</strong> " + r.getTimestamp("time") + "</pre>");
			}
		}
	%>
</body>
</html>