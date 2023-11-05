<%-- 
    Document   : updateProduct
    Created on : Oct 28, 2023, 7:20:22 PM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Product</title>
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
        <<link rel="stylesheet" href="./css/style.css"/>
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
                <div class="container_from-create update">
                    <div class="form_input-create">
                        <h1>Update Product</h1>
                        <h5 style="color: red">${requestScope.error}</h5>
                        <c:set var="pro" value="${requestScope.pro}"/>
                        <form action="updateProduct" method="post" enctype="multipart/form-data">
                            <h5>Enter ID </h5><input readonly value="${pro.id}" name="id" type="text" placeholder="your ID">
                            <h5>Enter name </h5><input value="${pro.name}" name="name" type="text" placeholder="full name">
                            <h5>Enter describe </h5><input value="${pro.description}" name="describe" type="text" placeholder="text">
                            <h5>Enter price </h5><input value="${pro.price}" name="price" type="number" placeholder="text">
                            <h5>Enter quantity </h5><input value="${pro.quantity}" name="quantity" type="number" placeholder="text">
                            <div class="row-create">
                                <h5>Enter picture </h5><input name="picture" type="file" placeholder="text">
                                <img src="assets/picture_pro/${pro.picture}" alt="alt"/>
                            </div>
                            <div class="row-create">
                                <h5>Enter Category</h5>
                                <select name="categoryItem" style="width: 200px;">
                                    <c:forEach items="${requestScope.data}" var="cates" >
                                        <option ${(pro.catergory.id eq cates.id)?"selected":""} value="${cates.id}">${cates.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <br/><!-- comment -->
                            <input class="submit-uP" type="submit" value="SAVE"/>
                        </form>
                    </div>
                    <div class="Back-button"> 
                        <a class="submit-create-back-u" href="productmangement?id=all">Back</a>
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
