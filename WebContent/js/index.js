function loginValidateForm() {
    var username = document.forms["loginForm"]["username"].value;
    var password = document.forms["loginForm"]["password"].value;
    
    if (username == null || username == ""){
        Materialize.toast("Username must be filled out", 4000)
        loginForm.username.focus();
        return false;
    }
    else if(password == null || password == ""){
		Materialize.toast("Password must be filled out", 4000)
        loginForm.password.focus();
		return false;
    }
    
    return true;
}