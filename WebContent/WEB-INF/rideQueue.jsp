<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.datastax.driver.core.Row" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
<link type="text/css" rel="stylesheet" href="css/index.css"/>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rides nearby</title>
</head>
<body>
<h1 class="teal lighten-2 white-text" style="text-align:center;font-size:200%;font-family:Calibri;">Nearby Rides</h1>
<div class="row">
	<%
		if(request.getAttribute("results") != null) {
			List<Row> results = (List<Row>) request.getAttribute("results");
			out.println("<div class=\"row\">");
			out.println("<div class=\"col s2\">Id:</div><div class=\"col s2\">Rider:</div><div class=\"col s2\">Origin:</div><div class=\"col s2\">Destination:</div><div class=\"col s2\">Time:</div>");
			out.println("</div>");
			for (Row r : results) {
				out.println("<div class=\"row\">");
				out.println("<div class=\"col s2\"><a href = \"/RadioTaxiProject-Release/confirmmatch?booking_id="+ r.getInt("booking_id") + "\"></div><div class=\"col s2\">" + r.getInt("booking_id") + "</a></div><div class=\"col s2\">" +  "</div><div class=\"col s2\">" + r.getString("rider") + "</div><div class=\"col s2\">" + r.getString("origin") + "</div><div class=\"col s2\">"+ r.getString("destination") + "</div><div class=\"col s2\">" + r.getTimestamp("time")+"</div>");
				out.println("</div>");
			}
		}
	%>
	</div>
	<form action="profile" method="get">
	        <button id="profile_in_button" type="submit" value="profile">My Profile</button>
	</form>
	<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
      <script type="text/javascript" src="js/materialize.min.js"></script>
        <script type="text/javascript" src="js/index.js"></script>
</body>
</html>	