<%-- 
    Document   : signup
    Created on : 17.05.2020, 16:39:00
    Author     : bayan
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    
  String errorStr = (String) request.getAttribute("pswdNotEq");
        
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <jsp:include page="header.jsp"/>
        <h1>Введите свои данные</h1>
         
        <%
        if(errorStr != null){
        out.print("<span style='color:red;'>" + errorStr+"</span>");
        }
        %>
        <form method="post"> 
              Имя:
            <input type="text" name="personName"><br/>
           
            Фамилия:
              <input type="text" name="personLast"><br/>
              
              
             <label for="number">Номер:</label><br/>
                <input type="text" name="number"><br/>
             
              Пароль:
                <input type="password" name="passwd"><br/>
                Подтвердите пароль:
                <input type="password" name="confirm"><br/>
            
                  <input type="submit" value="Sign up" ><br/>
                  
        </form>
    </body>
</html>
