function validateBookForm() {
    var origin = document.forms["bookForm"]["origin"].value;
    var dest = document.forms["bookForm"]["dest"].value;
    
    if (origin == null || origin == ""){
        Materialize.toast("origin must be filled out", 4000)
        bookForm.origin.focus();
        return false;
    }
    else if(dest == null || dest == ""){
        Materialize.toast("dest must be filled out", 4000)
        bookForm.dest.focus();
        return false;
    }
    
    return true;
}

function displayRoughEstimate() {
	
	var distance = parseInt(document.forms["bookForm"]["distance"].value.split(" "));
	var time = parseInt(document.forms["bookForm"]["time"].value.split(" "));
	var cabtype = document.forms["bookForm"]["cabtype"].value;
	var fare;
	switch(cabtype) {
	case 'regular': fare = distance*7 + time; break;
	case 'extended': fare = distance*10 + time; break;
	case 'double': fare = distance*15 + time; break;
	default: fare = 100;
	}
	window.alert("The rough estimate is: " + fare);
}