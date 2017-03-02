<%-- 
    Document   : resetPassword
    Created on : Mar 1, 2017, 2:37:13 PM
    Author     : Thuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
    </head>
    <body>
        <form method="POST" action="MyServlet?action=resetPassword">
            <input type="text" name="email" placeholder="Email:">
            <button type="submit">Submit</button>
        </form>
    </body>
</html>
