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