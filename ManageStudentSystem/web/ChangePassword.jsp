<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password</title>
        <link href="css/login.css" rel="stylesheet">
    </head>

    <body>
        <form id="changePasswordForm" action="changePassword" method="post">
            <div class="imgcontainer">
                <img src="images/img_avatar2.png" alt="Avatar" class="avatar">
            </div>

            <div class="container">
                <label><b>Email:</b></label><label>*</label> 
                <input type="text" placeholder="example@email.com" name="email" id="email" >
                <label><b>Old Password:</b></label><label>*</label>
                <input type="password" id="oldPassword" name="oldPassword" placeholder="Old Password:">
                <label><b>New Password:</b></label><label>*</label>
                <input type="password" id="newPassword" name="newPassword" placeholder="New Password:"><br>
                <label><b>Confirm Password:</b></label><label>*</label>
                <input type="password" id="confirmPassword"  name="confirmPassword" placeholder="Re-enter New Password:" onblur="ComparePassword()"><br>
                <label id="alert" style="color: red"></label><br>
                <button type="button" onclick="return validateForm();">Change</button>
            </div>
        </form>

    </body>
    <script type="text/javascript">

        function validateForm() {
            if (!validateRequiredField()) {
                document.getElementById("alert").innerHTML = "Please fill in required fields marked with *";
                return false;
            } else if (!validateEmail(email)) {
                document.getElementById("alert").innerHTML = "Invalid email format";
                return false;
            }
            else if (!ComparePassword()) {
                document.getElementById("alert").innerHTML = "New Password and Retype Password not match";
                return false;
            }

            document.getElementById("alert").innerHTML = "";
            document.getElementById("changePasswordForm").submit();
            return true;
        }

        function validateRequiredField() {
            var email = document.getElementById("email").value;
            var oldPassword = document.getElementById("oldPassword").value;
            var newPassword = document.getElementById("newPassword").value;
            var confirmPassword = document.getElementById("confirmPassword").value;
            if ((!email || !oldPassword || !newPassword || !confirmPassword)) {
                return false;
            }
            return true;
        }


        function validateEmail() {
            var email = document.getElementById("email").value;
            var email_regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
            return email_regex.test(email);
        }

        function ComparePassword() {
            var firstInput = document.getElementById("newPassword").value;
            var secondInput = document.getElementById("confirmPassword").value;
            console.log(firstInput + "/" + secondInput);
            if (firstInput === secondInput) {
                document.getElementById("alert").innerHTML = "";
                return true;
            } else if (firstInput !== secondInput) {
                document.getElementById("alert").innerHTML = "New Password and Retype Password not match";
                return false;
            }
        }
    </script>


</html>
