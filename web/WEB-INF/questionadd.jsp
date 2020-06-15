<%-- 
    Document   : questionadd
    Created on : 20.05.2020, 15:15:44
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
         <jsp:include page="/WEB-INF/auth/header.jsp"/>
        <h1>Добавить вопрос</h1>
         <form method="post"> 
           
                  
            <label for="title">Вопрос:</label><br/>
            <input type="text" name="title"><br/>
           
             <label for="opt1">Вариант 1:</label><br/>
            <input type="text" name="opt1"><br/>
              

                 <label for="opt2">Вариант 2:</label><br/>
            <input type="text" name="opt2"><br/>
            
               <label for="opt3">Вариант 3:</label><br/>
            <input type="text" name="opt3"><br/>
            
               <label for="opt4">Вариант 4:</label><br/>
            <input type="text" name="opt4"><br/>    
            
            <label for="correct">Правильный вариант:</label><br/>
            <input type="number" name="correct" min="1" max="4"><br/>
            
            
             <input type="submit" value="Добавить"><br/>
        </form>
    </body>
</html>
