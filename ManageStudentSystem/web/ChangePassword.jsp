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

                <label><b>Old Password</b></label>
                <input type="password" name="oldPassword" placeholder="Old Password:">
                New Password:<input type="password" name="newPassword" placeholder="New Password:"><br>
                Re-enter Password:<input type="password" name="confirmPassword" placeholder="Re-enter New Password:"><br>
                <button type="submit">Change</button>


            </div>

        </form>

    </body>
</html>
