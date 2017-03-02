<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
        <link href="css/login.css" rel="stylesheet">
    </head>
    <body>
        <form action="ResetPassword">
            <div class="imgcontainer">
                <img src="images/img_avatar2.png" alt="Avatar" class="avatar">
            </div>

            <div class="container">
                <label><b>Please enter your email:</b></label>
                <input type="text" placeholder="Email" name="email" required="true">
                <button type="submit">Submit</button>
            </div>

        </form>
    </body>
</html>
