<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
<link type="text/css" rel="stylesheet" href="css/index.css"/>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Driver Profile</title>
</head>
<body>
	<%
		//allow access only if session exists
		String user = null;
		if (session.getAttribute("type") == null) {
			response.sendRedirect("index.html");
		} else if(session.getAttribute("type").toString().compareTo("driver") != 0) {
			response.sendRedirect("error.jsp");
		} else
			user = (String) session.getAttribute("user");
	%>
	
 <h1 class="teal lighten-2 white-text" style="text-align:center;font-size:200%;font-family:Calibri;">SpeedX </h1>

<div class="parallax-container" style="height:200px">
<div class="parallax"><img src="images/car-driving.jpg"></div>
  </div>
  <div class="section white">
    <div class="row container">
    </div>
  </div>
  <h1 class="teal lighten-2 white-text" style="text-align:center;font-size:200%;font-family:Calibri;">Hi! <%=user%></h1>
        
    <br>    
    <div class="row">
    <div class="col s6"><a href="/RadioTaxiProject-Release-1/displayQueuedRides" class="waves-effect waves-light btn">Get Rides!</a>
    </div>
     <div class="col s6">
     <form action="Logout" method="get">
        <button class="btn waves-effect waves-light" id="logout_in_button" type="submit" value="Logout">Logout</button>
    </form>
    </div>
    </div>

	<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
      <script type="text/javascript" src="js/materialize.min.js"></script>
        <script type="text/javascript" src="js/index.js"></script>
        <script>
        $(document).ready(function() {
          $('.modal-trigger').leanModal();
        });
            $(document).ready(function(){
            $('.parallax').parallax();
        });

        </script>


</body>
</html>