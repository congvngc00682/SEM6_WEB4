<%-- 
    Document   : Login
    Created on : Feb 28, 2017, 3:09:50 PM
    Author     : power
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link href="css/login.css" rel="stylesheet">
    </head>
    <body>
        <form action="changePassword">
            <div class="imgcontainer">
                <img src="images/img_avatar2.png" alt="Avatar" class="avatar">
            </div>

            <div class="container">
                <label><b>Email:</b></label>
                <input type="text" placeholder="Enter Username" name="email" required>
                    
                <label><b>Old Password:</b></label>
                <input type="password"  name="oldPassword" placeholder="Old Password:">
                <label><b>New Password:</b></label>
                <input type="password" id="newPassword" name="newPassword" placeholder="New Password:"><br>
                <label><b>Re-enter Password:</b></label>
                <input type="password" id="confirmPassword"  name="confirmPassword" placeholder="Re-enter New Password:" onblur="ComparePassword()"><br>
                <label id="alert" style="color: red"></label><br>
                <button type="submit">Change</button>
            </div>
        </form>

    </body>
    <script type="text/javascript">
        function ComparePassword(){
            var firstInput = document.getElementById("newPassword").value;
            var secondInput = document.getElementById("confirmPassword").value;
            console.log(firstInput + "/" + secondInput);
            if(firstInput===secondInput){
                document.getElementById("alert").innerHTML = "";
            }else if(firstInput!==secondInput){
                document.getElementById("alert").innerHTML = "New Password and Retype Password not match";
            }
        }
    </script>
   
        
</html>
