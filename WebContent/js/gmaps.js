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
	// event listener function for origin and destination markers
	function markerEventListener() {
		console.log("dragend on destMarker");
		getDistanceMatrixRequest.origins = [ new google.maps.LatLng(
				originMarker.getPosition().lat(),
				originMarker.getPosition().lng()) ];
		document.getElementById("orig_lat").value = originMarker.getPosition().lat();
		document.getElementById("orig_lng").value = originMarker.getPosition().lng();
		getDistanceMatrixRequest.destinations = [ new google.maps.LatLng(
				destMarker.getPosition().lat(),
				destMarker.getPosition().lng()) ];
		document.getElementById("dest_lat").value = destMarker.getPosition().lat();
		document.getElementById("dest_lng").value = destMarker.getPosition().lng();
		//console.log(getDistanceMatrixRequest);
		service.getDistanceMatrix(
				getDistanceMatrixRequest,
				getDistanceMatrixCallback);
	}

	originMarker.addListener('dragend', markerEventListener);
	originMarker.addListener('position_changed', markerEventListener);
	destMarker.addListener('dragend', markerEventListener);
	destMarker.addListener('position_changed', markerEventListener);

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
					document.getElementById("distance").value = results[j].distance.text;
					document.getElementById("time").value = results[j].duration.text;
				}
			}
		}
	}
	;
	// end distance calculation
	
	
	// place ID finder
	// https://developers.google.com/maps/documentation/javascript/examples/places-placeid-finder
	
	var originInputPlaceId = document.getElementById('origin-auto');
	var destInputPlaceId = document.getElementById('dest-auto');

	var originAutocomplete = new google.maps.places.Autocomplete(originInputPlaceId);
	var destAutocomplete = new google.maps.places.Autocomplete(destInputPlaceId);
	originAutocomplete.bindTo('bounds', map);
	destAutocomplete.bindTo('bounds', map);

	map.controls[google.maps.ControlPosition.TOP_LEFT].push(originInputPlaceId);
	map.controls[google.maps.ControlPosition.LEFT_TOP].push(destInputPlaceId);

  	originAutocomplete.addListener('place_changed', function() {

	    var place = originAutocomplete.getPlace();
	    if (!place.geometry) {
	      return;
	    }

	    if (place.geometry.viewport) {
	      map.fitBounds(place.geometry.viewport);
	    } else {
	      map.setCenter(place.geometry.location);
	      map.setZoom(17);
	    }

	    // Set the position of the marker using the place ID and location.
	    originMarker.setPlace({
	      placeId: place.place_id,
	      location: place.geometry.location
	    });	
  	});
  	
  	destAutocomplete.addListener('place_changed', function() {

  		var place = destAutocomplete.getPlace();
	    if (!place.geometry) {
	      return;
	    }

	    if (place.geometry.viewport) {
	      map.fitBounds(place.geometry.viewport);
	    } else {
	      map.setCenter(place.geometry.location);
	      map.setZoom(17);
	    }

	    // Set the position of the marker using the place ID and location.
	    destMarker.setPlace({
	      placeId: place.place_id,
	      location: place.geometry.location
	    });			
  	});
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
