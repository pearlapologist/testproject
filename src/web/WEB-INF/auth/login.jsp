<%-- 
    Document   : login
    Created on : 17.05.2020, 15:04:59
    Author     : bayan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String errorStr = (String) request.getAttribute("error");

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="container">
            <form action="Login" method="post">
                <h3>Введите свои данные</h3>
                <%            if (errorStr != null) {
                %>
                <div class="alert alert-info" role="alert">
                    <%=errorStr%>
                </div>
                <%
                    }
                %>

                <div class="form">
                    <div class="col-md-4 mb-3">
                        <label for="loginNumber">Номер телефона</label>
                        <input type="text" class="form-control is-valid" id="loginNumber" name="loginNumber" value="" required>
                        <div class="invalid-feedback">
                            Пожалуйста, введите валидные данные.
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="loginPasswd">Пароль</label>
                        <input type="text" class="form-control is-valid" id="loginPasswd" name="loginPasswd" value="" required>
                        <div class="invalid-feedback">
                            Пожалуйста, введите валидные данные.
                        </div>
                    </div>
                  
                </div>
                <button class="btn btn-primary" type="submit">Авторизоваться</button>
            </form>
        </div>
    </body>
</html>
  <!--  </div>
      
      
                      <div class="form-row">-->
                    <!--<div class="col-md-3 mb-3">
                        <label for="loginAge">Возраст</label>
                        <select class="custom-select is-valid" id="loginAge" name="loginAge" required>
                            <option selected disabled value="">Choose...</option>
                            <option>14</option>
                            <option>15</option>
                            <option>16</option>
                            <option>17</option>

                        </select>
                        <div class="invalid-feedback">
                            Пожалуйста, введите валидные данные.
                        </div>
                    </div>-->