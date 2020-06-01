<%-- 
    Document   : me
    Created on : 17.05.2020, 9:33:04
    Author     : bayan
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="models.DbHelper"%>
<%@page import="models.Question"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    DbHelper db = new models.DbHelper();
    
 ArrayList<Question> list = db.getAllQuestions();
 int qNum = 0;
 try{
 qNum = Integer.parseInt(request.getParameter("qNum"));
 }catch(Exception ex){
 }
 
 int total = 0;
 
 try{
 total = Integer.parseInt(request.getParameter("total"));
 }catch(Exception ex){
 }
 

if(qNum >0){
int selected = Integer.parseInt(request.getParameter("opt"));
Question old = list.get(qNum-1);
if(selected == old.getCorrect()){
  total++;
}
}


 if(qNum == list.size()-1){
     request.setAttribute("result", total);
      getServletContext().getRequestDispatcher("/Result")
                    .forward(request, response);
return;
}


Question q = list.get(qNum);

%>

<%
String name = request.getParameter("name");

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Викторина</title>
    </head>
    <body>
                <jsp:include page="/WEB-INF/auth/header.jsp"/>
       Cuurent result: <%=total%>
        
         <form action="quiziz.jsp" method="get">
             
             <h2><%=q.getTitle()%></h2>
             
             <label for="opt1"><%=q.getOpt1()%></label><br/>
             <input type="radio" name="opt" value="1"> <br/>
            
              <label for="opt2"><%=q.getOpt2()%></label><br/>
            <input type="radio" name="opt" value="2"> <br/>
            
              <label for="opt3"><%=q.getOpt3()%></label><br/>
            <input type="radio" name="opt" value="3"> <br/>
            
              <label for="opt4"><%=q.getOpt4()%></label><br/>
            <input type="radio" name="opt" value="4"> <br/>
            
                <input type="hidden" name="qNum" value="<%=(qNum+1)%>"> <br/>
                   <input type="hidden" name="total" value="<=total%>"> <br/>
            
            <input type="submit" value="Ok">
       </form>
       
    </body>
</html>
