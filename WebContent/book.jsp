<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
<link type="text/css" rel="stylesheet" href="css/index.css"/>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
html, body {
	height: 100%;
	margin: 0;
	padding: 0;
}

#map {
	height: 80%;
}
</style>
<title>Choose pickup and drop-off location - RadioTaxi</title>
</head>

<body>
	<%
		//allow access only if session exists
		String user = null;
		if (session.getAttribute("type") == null) {
			response.sendRedirect("index.html");
		} else if(session.getAttribute("type").toString().compareTo("rider") != 0) {
			response.sendRedirect("error.jsp");
		} else
			user = (String) session.getAttribute("user");
	%>
	<div id="map"></div>
	<!-- holds the google map -->
	<script type="text/javascript">
		// https://developers.google.com/maps/documentation/javascript/tutorial
		var map;

		function initMap() {

			var bounds = new google.maps.LatLngBounds;
			var defaultLatLng = {
				lat : 23.4461269,
				lng : 75.6002234
			};
			// https://developers.google.com/maps/documentation/javascript/controls
			var mapOptions = {
				center : defaultLatLng,
				zoom : 13,
				streetViewControl : false,
				mapTypeControl : false,
			}
			map = new google.maps.Map(document.getElementById('map'),
					mapOptions);

			// https://developers.google.com/maps/documentation/javascript/markers
			var originMarker = new google.maps.Marker({ // pickup location marker ('A')
				position : defaultLatLng,
				title : 'Pickup Location',
				draggable : true,
				label : 'A'
			});

			var destMarker = new google.maps.Marker({ // drop-off location marker ('B')
				position : defaultLatLng,
				title : 'Drop Location',
				draggable : true,
				label : 'B'
			});

			originMarker.setMap(map);
			destMarker.setMap(map);

			// get user location (geolocation)
			// https://developers.google.com/maps/documentation/javascript/examples/map-geolocation
			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(function(position) {
					var pos = {
						lat : position.coords.latitude,
						lng : position.coords.longitude
					};

					// http://stackoverflow.com/questions/8840727/api-v3-update-marker-position-dynamically
					originMarker.setPosition(pos);
					// http://stackoverflow.com/questions/11030730/getting-coordinates-of-marker-in-google-maps-api
					destMarker.setPosition(new google.maps.LatLng(originMarker
							.getPosition().lat() + 0.01, originMarker
							.getPosition().lng() + 0.01));
					// Added 0.01 to lat-long to place destMarker near the originMarker
					//console.log("origin marker at " + originMarker.position);
					map.setCenter(pos);

				}, function() {
					handleLocationError(true, map.getCenter());
				});
			} else {
				// Browser doesn't support Geolocation
				handleLocationError(false, map.getCenter());
			}
			// end get user location

			// distance calculation
			// https://developers.google.com/maps/documentation/javascript/distancematrix#distance_matrix_requests
			// https://developers.google.com/maps/documentation/javascript/distancematrix#distance_matrix_parsing_the_results
			// https://developers.google.com/maps/documentation/javascript/examples/distance-matrix

			var geocoder = new google.maps.Geocoder;
			var service = new google.maps.DistanceMatrixService;

			// https://developers.google.com/maps/documentation/javascript/reference#DistanceMatrixRequest
			var getDistanceMatrixRequest = {
				origins : [ defaultLatLng ],
				destinations : [ defaultLatLng ],
				travelMode : google.maps.TravelMode.DRIVING,
				unitSystem : google.maps.UnitSystem.METRIC,
				avoidHighways : false,
				avoidTolls : false
			}

			// https://developers.google.com/maps/documentation/javascript/events
			// https://developers.google.com/maps/documentation/javascript/reference#Marker
			originMarker
					.addListener(
							'dragend',
							function() {
								//console.log("dragend on originMarker");
								getDistanceMatrixRequest.origins = [ new google.maps.LatLng(
										originMarker.getPosition().lat(),
										originMarker.getPosition().lng()) ];
								getDistanceMatrixRequest.destinations = [ new google.maps.LatLng(
										destMarker.getPosition().lat(),
										destMarker.getPosition().lng()) ];
								//console.log(getDistanceMatrixRequest);
								service.getDistanceMatrix(
										getDistanceMatrixRequest,
										getDistanceMatrixCallback);
							});
			destMarker
					.addListener(
							'dragend',
							function() {
								//console.log("dragend on destMarker");
								getDistanceMatrixRequest.origins = [ new google.maps.LatLng(
										originMarker.getPosition().lat(),
										originMarker.getPosition().lng()) ];
								getDistanceMatrixRequest.destinations = [ new google.maps.LatLng(
										destMarker.getPosition().lat(),
										destMarker.getPosition().lng()) ];
								//console.log(getDistanceMatrixRequest);
								service.getDistanceMatrix(
										getDistanceMatrixRequest,
										getDistanceMatrixCallback);
							});

			function getDistanceMatrixCallback(response, status) {
				//console.log("service.getDistanceMatrixCallback() called");
				if (status !== google.maps.DistanceMatrixStatus.OK) {
					alert('Error was: ' + status);
				} else {
					var origins = response.originAddresses;
					var destinations = response.destinationAddresses;
					var outputDiv = document.getElementById('output');
					outputDiv.innerHTML = '';

					for (var i = 0; i < origins.length; i++) {
						var results = response.rows[i].elements;
						for (var j = 0; j < results.length; j++) {
							outputDiv.innerHTML += 'Distance: '
									+ results[j].distance.text + '<br />'
									+ 'Estimated time: '
									+ results[j].duration.text + '<br>';
							document.getElementById("origin").value = origins[i];
							document.getElementById("dest").value = destinations[j];
						}
					}
				}
			}
			;
			// end distance calculation

		}

		// function to handle geolocation errors
		// https://developers.google.com/maps/documentation/javascript/infowindows
		function handleLocationError(browserHasGeolocation, pos) {
			var infoWindow = new google.maps.InfoWindow({
				map : map
			});
			infoWindow.setPosition(pos);
			infoWindow
					.setContent(browserHasGeolocation ? 'Error: The Geolocation service failed.'
							: 'Error: Your browser doesn\'t support geolocation.');
		}
	</script>
	<!-- Google Maps API load configuration
    https://developers.google.com/maps/documentation/javascript/basics#Region
    https://developers.google.com/maps/documentation/javascript/versions
     -->
	<script async defer
		src="https://maps.googleapis.com/maps/api/js?v=3&region=IN&callback=initMap">
		
	</script>
	<div id="output">Drag and drop marker B at the destination, or enter your destination in the field below!</div>
	
	<form action="Book" method="post" class="col s12">
		<div class="row">
			<div class="input-field col s4">
				<input placeholder=" "  type="text" name="origin" id="origin" class="validate">
				<label for="origin" id="origin">Origin</label>
			</div>
			<div class="input-field col s4">
				<input placeholder=" "  type="text" name="dest" id="dest" class="validate">
				<label for="dest" id="dest">Destination</label>
			</div>
			<div class="input-field col s4">
				<button class="btn waves-effect waves-light" id="book_button" type="submit" value="Book">Book</button>
			</div>
		</div>
	</form>

<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
      <script type="text/javascript" src="js/materialize.min.js"></script>
        <script type="text/javascript" src="js/index.js"></script>

</body>

</html>