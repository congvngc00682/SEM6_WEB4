<%-- 
    Document   : result
    Created on : Mar 9, 2017, 10:10:48 AM
    Author     : power
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="result">
		<h3>${requestScope["message"]}</h3>
		<br>
	</div>
	File name : ${name}
	<br> File size : ${requestScope["size"]}
	<br> File type : ${requestScope["type"]}
        <img src="${name}" style="height: 500px; width: 500px">
    </body>
</html>
