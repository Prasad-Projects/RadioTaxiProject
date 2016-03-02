<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="css/materialize.min.css"
	media="screen,projection" />
<link type="text/css" rel="stylesheet" href="css/index.css" />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII"
	name="viewport">
<title>Login Success Page</title>

</head>
<body>
	<%
		//allow access only if session exists
		String user = null;
		if (session.getAttribute("user") == null) {
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
	<a href="book.jsp" class="waves-effect waves-light btn">Book</a>

	<br>
	<a href="CheckoutPage.jsp">Checkout Page</a>
	<form action="Logout" method="post">
		<input type="submit" value="Logout">
	</form>

	<script type="text/javascript"
		src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.min.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
	<script>
		$(document).ready(function() {
			// the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
			$('.modal-trigger').leanModal();
		});
	</script>
</body>
</html>