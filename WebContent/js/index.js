function loginValidateForm() {
    var x = document.forms["loginForm"]["username"].value;
    var y = document.forms["loginForm"]["password"].value;
    if (x == null || x == ""){
        Materialize.toast("Username must be filled out", 4000)
        return false;
    }
    else if(y == null || y == ""){
		Materialize.toast("Password must be filled out", 4000)
		return false;
    }
}

function validateEmail(email) 
{
    var re = /\S+@\S+\.\S+/;
    return re.test(email);
}

function validateUsername(name){
	var re2 = /^\w+$/;
    return re2.test(name);
    }

function registerValidateForm() {
    var x = document.forms["registerForm"]["username"].value;
    var y = document.forms["registerForm"]["password"].value;
    var z = document.forms["registerForm"]["email"].value;
    var w = document.forms["registerForm"]["confirmPassword"].value;
    
    if (x == null || x == ""){
        Materialize.toast("Username must be filled out", 4000)
        registerForm.username.focus();
        return false;
    }
    else if(z == null || z == ""){
		Materialize.toast("Email must be filled out", 4000)
		registerForm.email.focus();
		return false;
    }
    else if(y == null || y == ""){
		Materialize.toast("Password must be filled out", 4000)
		registerForm.password.focus();
		return false;
    }
    else if(w == null || w == ""){
		Materialize.toast("Password must be filled out", 4000)
		registerForm.confirmPassword.focus();
		return false;
    }
    if(!validateEmail(z)){
    	Materialize.toast("Invalid email", 4000)
		registerForm.email.focus();
		return false;	
    }
    if(!validateUsername(x)){
      Materialize.toast("Username must contain only letters, numbers and underscores!", 4000)
      registerForm.username.focus();	
      return false;
    }
    if(y.length < 7){
   	  Materialize.toast("Password must contain at least six characters!", 4000)
      registerForm.password.focus();	
      return false;	
    }
    if(y!=w){
      Materialize.toast("Password do not match!", 4000)
      registerForm.confirmPassword.focus();	
      return false;    	
    }
}