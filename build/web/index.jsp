<%-- 
    Document   : index.jsp
    Created on : 17.05.2020, 10:53:10
    Author     : bayan
--%>
<%@page import="models.DataUtils"%>
<%@page import="models.Question"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //QuestionList list = new QuestionList();
    //list.getList();

    //String name = request.getParameter("name");
    //if(name==null || name == "" || name == "null"){
    ///name = "Guest";
// }
    //int age =0;
// try{
    //age = Integer.parseInt(request.getParameter("age"));
// }catch(Exception e){}


%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Главная</title>
       
    </head>
    <body> 
        <jsp:include page="/WEB-INF/auth/header.jsp"/>
            <div class="container">
      
        <h3>Здравствуйте</h3><br/>

            </div>
    </body>
</html>
