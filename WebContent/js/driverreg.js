function registerValidateForm() {
    var firstname = document.forms["driverRegisterForm"]["firstname"].value;
    var lastname  = document.forms["driverRegisterForm"]["lastname"].value;
    var username = document.forms["driverRegisterForm"]["username"].value;
    var password = document.forms["driverRegisterForm"]["password"].value;
    var confirm_password = document.forms["driverRegisterForm"]["confirm_password"].value;
    var mobile  = document.forms["driverRegisterForm"]["mobile"].value;
    var car_no  = document.forms["driverRegisterForm"]["car_no"].value;
    var licence  = document.forms["driverRegisterForm"]["licence"].value;
    
    if(firstname  == null || firstname  == ""){
        Materialize.toast("first_name must be filled out", 4000)
        driverRegisterForm.firstname.focus();
        return false;
    }

    if(lastname  == null || lastname  == ""){
        Materialize.toast("last_name must be filled out", 4000)
        driverRegisterForm.lastname.focus();
        return false;
    }

    if (username == null || username == ""){
        Materialize.toast("Username must be filled out", 4000)
        driverRegisterForm.username.focus();
        return false;
    }
    if(password == null || password == ""){
        Materialize.toast("Password must be filled out", 4000)
        driverRegisterForm.password.focus();
        return false;
    }
    if(confirm_password == null || confirm_password == ""){
        Materialize.toast("confirm Password must be filled out", 4000)
        driverRegisterForm.confirm_password.focus();
        return false;
    }
    if(mobile  == null || mobile  == ""){
        Materialize.toast("mobile must be filled out", 4000)
        driverRegisterForm.mobile.focus();
        return false;
    }
    if(licence  == null || licence  == ""){
        Materialize.toast("licence must be filled out", 4000)
        driverRegisterForm.licence.focus();
        return false;
    }
    if(car_no  == null || car_no  == ""){
        Materialize.toast("car no. must be filled out", 4000)
        driverRegisterForm.car_no.focus();
        return false;
    }
  
    if(!validateMobile(mobile)){
        Materialize.toast("mobile no Invalid", 4000)
        driverRegisterForm.mobile.focus();
        return false;
    }

    if(!validateUsername(username)){
      Materialize.toast("Username must contain only letters, numbers and underscores!", 4000)
      driverRegisterForm.username.focus();    
      return false;
    }

    if(password.length < 6){
      Materialize.toast("Password must contain at least six characters!", 4000)
      driverRegisterForm.password.focus();    
      return false; 
    }
    if(password!=confirm_password){
      Materialize.toast("Password do not match!", 4000)
      driverRegisterForm.password.focus(); 
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