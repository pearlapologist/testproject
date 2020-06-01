<%-- 
    Document   : profilw
    Created on : 17.05.2020, 14:25:37
    Author     : bayan
--%>

<%@page import="models.Service"%>
<%@page import="models.Executor"%>
<%@page import="models.DbHelper"%>
<%@page import="models.Person"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Person person = (Person) request.getAttribute("profilePerson");

    DbHelper db = new DbHelper();
    int personId = person.getId();
    Executor executor = null;

    int executorId = db.getExecutorIdByPersonId(personId);

    if (executorId != -1) {
        executor = db.getExecutor(executorId);
        db.loadExecutorServices(executor);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Профиль</title>
    </head>
    <body>

        <jsp:include page="/WEB-INF/auth/header.jsp"/>
        <div class="container">
            Имя :<%=person.getName()%><br/>
            Фамилия :<%=person.getLastname()%><br/>
            Контакты: <%=person.getNumber()%><br/>
            Услуги: 
            <%
                if (executor == null || executor.getServices().isEmpty()) {
            %>
            <span style="color: gray">(Пусто)</span>
            <%
            }
            else {
                for (Service s : executor.getServices()) {
            %>
            <span class="badge badge-dark"><%=s.getTitle()%></span>
            <%
                    }
                }
            %> <br/>

        </div>
            
         
    </body>
</html>
