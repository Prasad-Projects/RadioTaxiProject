function registerValidateForm() {
    var firstname = document.forms["riderRegisterForm"]["firstname"].value;
    var lastname  = document.forms["riderRegisterForm"]["lastname"].value;
    var username = document.forms["riderRegisterForm"]["username"].value;
    var password = document.forms["riderRegisterForm"]["password"].value;
    var confirm_password = document.forms["riderRegisterForm"]["confirm_password"].value;
    var mobile  = document.forms["riderRegisterForm"]["mobile"].value;
    
    if(firstname  == null || firstname  == ""){
        Materialize.toast("first_name must be filled out", 4000)
        riderRegisterForm.firstname.focus();
        return false;
    }

    if(lastname  == null || lastname  == ""){
        Materialize.toast("last_name must be filled out", 4000)
        riderRegisterForm.lastname.focus();
        return false;
    }

    if (username == null || username == ""){
        Materialize.toast("Username must be filled out", 4000)
        riderRegisterForm.username.focus();
        return false;
    }
    if(password == null || password == ""){
        Materialize.toast("Password must be filled out", 4000)
        riderRegisterForm.password.focus();
        return false;
    }
    if(confirm_password == null || confirm_password == ""){
        Materialize.toast("confirm Password must be filled out", 4000)
        riderRegisterForm.confirm_password.focus();
        return false;
    }
    if(mobile  == null || mobile  == ""){
        Materialize.toast("mobile must be filled out", 4000)
        riderRegisterForm.mobile.focus();
        return false;
    }
  
    if(!validateMobile(mobile)){
        Materialize.toast("mobile no Invalid", 4000)
        riderRegisterForm.mobile.focus();
        return false;
    }

    if(!validateUsername(username)){
      Materialize.toast("Username must contain only letters, numbers and underscores!", 4000)
      riderRegisterForm.username.focus();    
      return false;
    }

    if(password.length < 6){
      Materialize.toast("Password must contain at least six characters!", 4000)
      riderRegisterForm.password.focus();    
      return false; 
    }
    if(password!=confirm_password){
      Materialize.toast("Password do not match!", 4000)
      riderRegisterForm.password.focus(); 
      return false;     
    }
    return true;
}

function validateMobile(mobile){
  var phoneno = /^\d{10}$/;  
  return mobile.match(phoneno);
}

function validateUsername(name){
  var re2 = /^\w+$/;
    return re2.test(name);
    }