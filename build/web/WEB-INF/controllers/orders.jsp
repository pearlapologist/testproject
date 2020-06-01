<%-- 
    Document   : orders
    Created on : 27.05.2020, 20:43:20
    Author     : bayan
--%>

<%@page import="models.DataUtils"%>
<%@page import="models.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    ArrayList<Order> orders = (ArrayList<Order>) request.getAttribute("ordersAll");
    
    int curPage = Integer.parseInt(request.getAttribute("ordersCurPage").toString());
    int lastPage = Integer.parseInt(request.getAttribute("ordersLastPage").toString());

    request.setAttribute("navCurr", "orders");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Поиск заказов</title>
        
        <script>
            $(document).ready(function () {
                var date_input = $('input[name="date"]');
                var container = $('.bootstrap-iso form').length > 0 ? $('.bootstrap-iso form').parent() : "body";
                date_input.datepicker({
                    format: 'mm/dd/yyyy',
                    container: container,
                    todayHighlight: true,
                    autoclose: true,
                })
            })
        </script>
        
    </head>
    <body>     
        <jsp:include page="/WEB-INF/auth/header.jsp"/>
        <div class="container-fluid">

            <!--<div style="margin-top: 20px" class="row row-cols-1 row-cols-sm-4">
                   %for(Order r: orders){ %>
                <div class="col mb-4">
                    <div class="card h-100">
                        <img src="Content/order2_default.jpg" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">%=r.getTitle()%></h5>
                            <p class="card-text">%=r.getDescription()%></p>
                        </div>
                    </div>
                </div>
            %}%>
        </div>-->

            <div class="col-sm-4">
                <button type="button" class="btn btn-dark" data-toggle="modal" data-target="#newOrderModal">
                    Создать заказ
                </button>
            </div>

            Список заказов
            <div class="card" >
                <%for (Order r : orders) {%>
                <div class="row no-gutters">
                    <!-- <div class="col-sm-2" style="background: #868e96;">
                         <img src="Content/order2_default.jpg" class="card-img-top h-100" alt="...">
                     </div>-->
                    <div class="col-sm-7">
                        <div class="card-body">
                            <h5 class="card-title"><%=r.getTitle()%></h5>
                            <p class="card-text"><%=r.getDescription()%></p>
                            <a href="Order?id=<%=r.getId()%>" class="btn btn-primary stretched-link">View order</a>
                        </div>
                    </div>
                </div>
                <%}%>
            </div>


            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">

                    <li class="page-item <%if (curPage == 1) {
                            out.print(" disabled");
                        }%>">
                        <a class="page-link" href="<%=DataUtils.PATH%>Orders?page=<%=(curPage - 1)%>" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <%for (int i = 1; i <= lastPage; i++) {
                            String sActive = "";
                            if (i == curPage) {
                                sActive = " active ";
                            }

                    %>
                    <li class="page-item <%=sActive%>"><a class="page-link" href="<%=DataUtils.PATH%>Orders?page=<%=i%>"><%=i%></a></li>


                    <%
                        }%>
                    <li class="page-item <%if (curPage == lastPage) {
                            out.print(" disabled");
                        }%>">
                        <a class="page-link" href="<%=DataUtils.PATH%>Orders?page=<%=(curPage + 1)%>" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>

                </ul>
            </nav>
        </div>


        <!-- Modal -->
        <div class="modal fade" id="newOrderModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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

                            <label for="newOrderTitle">Название</label>
                            <input type="text" class="form-control is-valid" id="newOrderTitle" name="newOrderTitle" value="" required>
                            <div class="invalid-feedback">
                                Пожалуйста, введите валидные данные.
                            </div>
                            
                            
                            <label for="date">Дата или дедлайн</label>
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar">
                                        </i>
                                    </div>
                                    <input class="form-control" id="date" name="date" placeholder="MM/DD/YYYY" type="text"/>


                            <label for="newOrderPrice">Цена</label>
                            <input type="text" class="form-control is-valid" id="newOrderPrice" name="newOrderPrice" value="" required>
                            <div class="invalid-feedback">
                                Пожалуйста, введите валидные данные.
                            </div>

                            <label for="newOrderDesc">Описание</label>
                            <input type="text" class="form-control is-valid" id="newOrderDesc" name="newOrderDesc" value="" required>
                            <div class="invalid-feedback">
                                Пожалуйста, введите валидные данные.
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
