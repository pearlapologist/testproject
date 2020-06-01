<%-- 
    Document   : mavens
    Created on : 27.05.2020, 21:02:57
    Author     : bayan
--%>

<%@page import="models.Person"%>
<%@page import="models.DbHelper"%>
<%@page import="models.Executor"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    ArrayList<Executor> executors = (ArrayList<Executor>) request.getAttribute("executorsAll");
  
        int curPage = Integer.parseInt(request.getAttribute("executorsCurPage").toString());
    int lastPage = Integer.parseInt(request.getAttribute("executorsLastPage").toString());

    request.setAttribute("navCurr", "mavens");
  
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Спеециалисты</title>
    </head>
    <body>
       <jsp:include page="/WEB-INF/auth/header.jsp"/>
        <div class="container-fluid">

         <div class="card" ">
                <%for (Executor r : executors) {%>
                <%
               int id = r.getPersonId();
               DbHelper db = new DbHelper();
               Person p = db.getPerson(id);
                %>
                <div class="row no-gutters">
                    <div class="col-sm-2" style="background: #868e96;">
                        <img src="<%=models.DataUtils.getPersonPhotoPath(p)%>" class="card-img-top h-100" alt="...">
                    </div>
                    <div class="col-sm-7">
                        <div class="card-body">
                         <h4 class="card-title"><%=p.getName()%> <%=p.getLastname()%></h4>
                            <p class="card-text"><%=r.getSpecialztn()%></p>
                            <a href="Profile?id=<%=r.getId()%>" class="btn btn-outline-secondary btn-xs">View Profile</a>
                        </div>
                    </div>
                </div>
                <%}%>
            </div>
                
                
        </div>
    </body>
</html>
