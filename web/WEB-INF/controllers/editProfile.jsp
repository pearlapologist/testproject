<%-- 
    Document   : editProfile
    Created on : 29.05.2020, 15:36:42
    Author     : bayan
--%>

<%@page import="models.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
    Person p = models.Account.getCurrentPerson(request);
    DbHelper db = new DbHelper();
    int personId = p.getId();
    Executor executor = null;

    int executorId = db.getExecutorIdByPersonId(personId);

    if (executorId != -1) {
        executor = db.getExecutor(executorId);
        db.loadExecutorServices(executor);
    }

    request.setAttribute("navCurr", "cabinet");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Настройки кабинета</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/auth/header.jsp"/>
        <div class="container">
            
            <form method="post" action="EditProfile" enctype="multipart/form-data">
            Имя :<%=p.getName()%><br/>
            Фамилия :<%=p.getLastname()%><br/>
            Мои услуги: 
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
            
            Изменить фотографию:
            <input type="file" name="file" id="file"><br/>
            <input  class="btn btn-secondary" type="submit" name="upload" id="upload" value="upload"><br/>

            <a class="btn btn-secondary" href="#" role="button">Редактировать</a>

            </form>
        </div>
    </body>
</html>
