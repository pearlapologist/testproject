<%-- 
    Document   : result
    Created on : 17.05.2020, 12:49:09
    Author     : bayan
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<% 
 ArrayList<String> names2 = new ArrayList();
 names2.add("name1");
  names2.add("name2");
   names2.add("name3");
 
   
    ArrayList <Integer> resls = new ArrayList<Integer>();
   
 resls.add(6);
  resls.add(6);
   resls.add(7);

 int total = 0;
 
 try{
    total = (Integer)request.getAttribute("result");
 //total = Integer.parseInt(request.getParameter("total"));
 }catch(Exception ex){
 }
 
 
 int id = (Integer)request.getAttribute("id");
//names2.add(Persons_list.getPersonById(id).getName());
resls.add(total);

%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Результаты</title>
    </head>
    <body>
                <jsp:include page="/WEB-INF/auth/header.jsp"/>
        <h1>Your result: <%=total%>/10</h1>
        
       Результаты других пользователей:
        <ul>
            <%
            for(int i =0; i< names2.size();i++){
            out.println("<li>" + names2.get(i) + " - " + resls.get(i)+ "</li>");
            }
           
            %>
        </ul>>
    </body>
</html>
