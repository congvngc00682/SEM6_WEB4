<%-- 
    Document   : Register
    Created on : Mar 1, 2017, 8:56:48 AM
    Author     : power
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Register</title>
        <link rel="stylesheet" href="css/bootstrap.min.css" media="screen">
        <link rel="stylesheet" type="text/css" href="css/register.css">
        <link rel="shortcut icon" href="image/icon.jpg" type="image/x-icon" />
        <link rel="icon" href="image/icon.jpg" type="image/x-icon" />
        <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script type="text/javascript">
            function checkPass()
            {
                //Store the password field objects into variables ...
                var pass1 = document.getElementById('pass1');
                var pass2 = document.getElementById('pass2');
                //Store the Confimation Message Object ...
                var message = document.getElementById('confirmMessage');
                //Set the colors we will be using ...
                var goodColor = "#66cc66";
                var badColor = "#ff6666";
                //Compare the values in the password field 
                //and the confirmation field
                if (pass1.value == pass2.value) {
                    //The passwords match. 
                    //Set the color to the good color and inform
                    //the user that they have entered the correct password 
                    pass2.style.backgroundColor = goodColor;
                    message.style.color = goodColor;
                    message.innerHTML = "Passwords Match"
                } else {
                    //The passwords do not match.
                    //Set the color to the bad color and
                    //notify the user.
                    pass2.style.backgroundColor = badColor;
                    message.style.color = badColor;
                    message.innerHTML = "Passwords Do Not Match!"
                }
            }

// validates text only
            function Validate(txt) {
                txt.value = txt.value.replace(/[^a-zA-Z-'\n\r.]+/g, '');
            }
// validate email
            function email_validate(email)
            {
                var regMail = /^([_a-zA-Z0-9-]+)(\.[_a-zA-Z0-9-]+)*@([a-zA-Z0-9-]+\.)+([a-zA-Z]{2,3})$/;

                if (regMail.test(email) == false)
                {
                    document.getElementById("status").innerHTML = "<span class='warning'>Email address is not valid yet.</span>";
                }
                else
                {
                    document.getElementById("status").innerHTML = "<span class='valid'>Thanks, you have entered a valid Email address!</span>";
                }
            }
// validate date of birth
            function dob_validate(dob)
            {
                var regDOB = /^(\d{1,2})[-\/](\d{1,2})[-\/](\d{4})$/;

                if (regDOB.test(dob) == false)
                {
                    document.getElementById("statusDOB").innerHTML = "<span class='warning'>DOB is only used to verify your age.</span>";
                }
                else
                {
                    document.getElementById("statusDOB").innerHTML = "<span class='valid'>Thanks, you have entered a valid DOB!</span>";
                }
            }
// validate address
            function add_validate(address)
            {
                var regAdd = /^(?=.*\d)[a-zA-Z\s\d\/]+$/;

                if (regAdd.test(address) == false)
                {
                    document.getElementById("statusAdd").innerHTML = "<span class='warning'>Address is not valid yet.</span>";
                }
                else
                {
                    document.getElementById("statusAdd").innerHTML = "<span class='valid'>Thanks, Address looks valid!</span>";
                }
            }

        </script>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div>
                    <form action="" method="post" id="fileForm" role="form">
                        <fieldset><legend class="text-center">Register <span class="req"></span></legend>

                            <div class="form-group">
                                <label for="lastname"><span class="req">* </span> User name: </label> 
                                <input class="form-control" type="text" name="lastname" id = "txt" onkeyup = "Validate(this)" placeholder="Enter Your Name" required />  
                                <div id="errLast"></div>
                            </div>

                            <div class="form-group">
                                <label for="email"><span class="req">* </span> Email Address: </label> 
                                <input class="form-control" required type="text" name="email" id = "email" placeholder="Enter Your Email"  onchange="email_validate(this.value);" />   
                                <div class="status" id="status"></div>
                            </div>

                            <div class="form-group">
                                <label for="password"><span class="req">* </span> Password: </label>
                                <input required name="password" type="password" class="form-control inputpass" minlength="4" placeholder="Enter Password" maxlength="16"  id="pass1" /> </p>

                                <label for="password"><span class="req">* </span> Password Confirm: </label>
                                <input required name="password" type="password" class="form-control inputpass" minlength="4" maxlength="16" placeholder="Enter Password again"  id="pass2" onkeyup="checkPass();
                                        return false;" />
                                <span id="confirmMessage" class="confirmMessage"></span>
                            </div>

                            <div class="form-group">

                                <?php //$date_entered = date('m/d/Y H:i:s'); ?>
                                <input type="hidden" value="<?php //echo $date_entered; ?>" name="dateregistered">
                                <input type="hidden" value="0" name="activate" />
                                <hr>

                                <input type="checkbox" required name="terms" onchange="this.setCustomValidity(validity.valueMissing ? 'Please indicate that you accept the Terms and Conditions' : '');" id="field_terms">   <label for="terms">I agree with the <a href="terms.php" title="You may read our terms and conditions by clicking on this link">terms and conditions</a> for Registration.</label><span class="req">* </span>
                            </div>

                            <div class="form-group">
                                <input class="btn btn-success" type="submit" name="submit_reg" value="Register">
                            </div>
                            <h5>You will receive an email to complete the registration and validation process. </h5>
                            <h5>Be sure to check your spam folders. </h5>


                        </fieldset>
                    </form><!-- ends register form -->

                    <script type="text/javascript">
                        document.getElementById("field_terms").setCustomValidity("Please indicate that you accept the Terms and Conditions");
                    </script>
                </div><!-- ends col-6 -->

            </div>
        </div>
    </body>
</html>
