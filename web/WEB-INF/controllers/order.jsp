<%-- 
    Document   : order
    Created on : 29.05.2020, 10:16:09
    Author     : bayan
--%>

<%@page import="models.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%

    Order r = (Order) request.getAttribute("separateOrder");

    request.setAttribute("navCurr", "separateOrder");

    String errorStr = (String) request.getAttribute("newResponseError");

%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Заказ</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/auth/header.jsp"/>
        <div class="container">
            <div class="card" >
                <div class="row no-gutters">
                    <!-- <div class="col-sm-2" style="background: #868e96;">
                         <img src="Content/order2_default.jpg" class="card-img-top h-100" alt="...">
                     </div>-->
                    <div class="col-sm-7">
                        <div class="card-body">
                            <h5 class="card-title"><%=r.getTitle()%></h5>
                            <p class="card-text"><%=r.getDescription()%></p>

                            </br>

                            <button type="button" class="btn btn-dark" data-toggle="modal" data-target="#responseModal">
                                Launch demo modal
                            </button>

                        </div>
                    </div>
                </div>
            </div>

        </div>



        <!-- Modal -->
        <div class="modal fade" id="responseModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form method="post" action="NewResponse">
                        <div class="modal-header">
                            <h5 class="modal-title" id="responseModalLabel">Опишите предложение</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <!--<input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Цена"> 
                                <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Предложение">-->
                            <% if (errorStr != null) {
                            %>
                            <div class="alert alert-info" role="alert">
                                <%=errorStr%>
                            </div>
                            <%
                                }
                            %>

                            <label for="newResponsePrice">Цена</label>
                            <input type="number" class="form-control is-valid" id="newResponsePrice" name="newResponsePrice" value="" required>
                            <div class="invalid-feedback">
                                Пожалуйста, введите валидные данные.
                            </div>

                            <label for="newResponseText">Предложение</label>
                            <input type="text" class="form-control is-valid" id="newResponseText" name="newResponseText" value="" required>
                            <div class="invalid-feedback">
                                Пожалуйста, введите валидные данные.
                            </div>
                            <div class="form-group">
                                <div class="input-group" id="responseDatetimepicker">
                                    <input type="text" class="form-control" />
                                    <span class="input-group-addon">
                                        <span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                </div>
                            </div>



                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>
                            <button type="submit" class="btn btn-secondary" data-dismiss="modal">Отправить</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
                            
                            
    </body>
</html>
