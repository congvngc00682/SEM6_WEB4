<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password Result</title>
        <link href="css/login.css" rel="stylesheet">
    </head>
    <body>
        <form>
            <div class="imgcontainer">
                <img src="images/img_avatar2.png" alt="Avatar" class="avatar">
            </div>
            <div class="container" style="text-align: center;">
                <c:choose>
                    <c:when test="${result eq 'success'}">
                        <h3>Your password has been changed successfully!</h3>
                        <a href="Login.jsp">Back to login</a>
                    </c:when>
                    <c:otherwise>
                        <h3>Incorrect email or old password</h3>
                        <a href="#" onclick="window.history.go(-1);">Back</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </form>
    </body>
</html>
