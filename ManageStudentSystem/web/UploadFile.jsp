
<%@page  import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload File</title>
        
        
    </head>
    <body>
        <form method="POST" action="UploadFile" enctype="multipart/form-data" >
            File:
            <input type="file" name="filename" id="file" /> <br/>
            </br>
            <input type="submit" value="Upload" name="upload" id="upload" />
        </form>
    </body>
</html>
