<%-- 
    Document   : orderDetailManagement
    Created on : Oct 29, 2023, 8:49:12 PM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order detail management</title>

        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">
        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&display=swap" rel="stylesheet">

        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="css/styleadmin.css" rel="stylesheet">

        <!-- icon -->
        <script src="https://kit.fontawesome.com/8143c9cd7e.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container-xxl position-relative bg-white d-flex p-0">
            <!-- Sidebar Start 
            <%@ include file='admincomponents/adminheader.jsp' %>
            Sidebar End -->

            <!-- Content Start -->
            <div class="content">
                <!-- Navbar Start -->
                <%@ include file='admincomponents/adminuser.jsp' %>
                <!-- Navbar End -->

                <!-- Recent Sales Start -->
                <c:set var="order" value="${requestScope.order}"/>
                <div class="container-fluid pt-4 px-4">
                    <h1>Order content</h1>
                    <h3>Zip: ${order.id}</h3>
                    <h3>Username: ${order.userName}</h3>
                    <h3>Order date: ${order.date}</h3>
                    <div class="bg-light rounded h-100 p-4">
                        <h6 class="mb-4">Order detail</h6>
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Product id</th>
                                        <th scope="col">Quantity</th>
                                        <th scope="col">price</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:set var="i" value="0"/>
                                    <c:forEach items="${requestScope.listOfOrderDetail}" var="detail">
                                        <tr>
                                            <c:set var="i" value="${i+1}"/>
                                            <th scope="row">${i}</th>
                                            <td>${detail.product.name}</td>
                                            <td>${detail.quantity}</td>
                                            <td>${(detail.price*detail.quantity)}</td>
                                        </tr>
                                    </c:forEach>
                                    <tr>
                                        <th scope="row"></th>
                                        <td></td>
                                        <td></td>
                                        <td> <h5>Total: ${order.totalMoney}</h5></td>
                                    </tr>
                                </tbody>
                            </table>
                            <div class="d-flex align-items-center justify-content-between mb-4">
                                <h6 class="mb-0"><a href="deleteCart?orderId=${order.id}">Delete</a></h6>
                                <h6 class="mb-0"><a href="updateCart?orderId=${order.id}">Order processing</a></h6>
                            </div>
                        </div>
                        <a href="orderManagement">Back</a>
                    </div>

                </div>
                <!-- Recent Sales End -->
            </div>
            <!-- Content End -->


            <!-- Back to Top -->
            <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
        </div>

        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="lib/chart/chart.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/waypoints/waypoints.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>
        <script src="lib/tempusdominus/js/moment.min.js"></script>
        <script src="lib/tempusdominus/js/moment-timezone.min.js"></script>
        <script src="lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

        <!-- Template Javascript -->
        <script src="js/main.js"></script>
    </body>
</html>
