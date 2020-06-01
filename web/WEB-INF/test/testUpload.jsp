<%-- 
    Document   : testUpload
    Created on : 29.05.2020, 14:01:07
    Author     : bayan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form method="post" action="TestUpload" enctype="multipart/form-data">
            File:
            <input type="file" name="file" id="file"><br/>
            <input type="submit" name="upload" id="upload" value="upload"><br/>
        </form>
    </body>
</html>
