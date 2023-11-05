<%-- 
    Document   : productmanagement
    Created on : Oct 28, 2023, 12:39:09 AM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product management</title>

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

        <!-- xử lý thông báo của delete -->
        <script>
            function doDelete(id) {
                if (confirm('Do you want to delete this id: ' + id)) {
                    window.location = "deleteProduct?id=" + id;
                }
            }
        </script>
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

                <!-- Sale & Revenue Start -->
                <div class="container-fluid pt-4 px-4">
                    <div class="d-flex align-items-center justify-content-around ">
                        <a href="productmangement?id=""">All</a>
                        <c:forEach items="${requestScope.listOfCate}" var="cates">
                            <a href="productmangement?id=${cates.id}">${cates.name}</a>
                        </c:forEach>
                    </div>
                </div>
                <!-- Sale & Revenue End -->

                <!-- Recent Sales Start -->
                <div class="container-fluid pt-4 px-4">
                    <div class="bg-light text-center rounded p-4">
                        <div class="d-flex align-items-center justify-content-between mb-4">
                            <h6 class="mb-0">List of Product</h6>
                            <a href="createProduct">Add new</a>
                        </div>
                        <div class="table-responsive">
                            <table class="table text-start align-middle table-bordered table-hover mb-0">
                                <thead>
                                    <tr class="text-dark">
                                        <th style="font-size: 20px" scope="col">No.</th>
                                        <th style="font-size: 20px" scope="col">Id</th>
                                        <th style="font-size: 20px" scope="col">Name</th>
                                        <th style="font-size: 20px" scope="col">Description</th>
                                        <th style="font-size: 20px" scope="col">Price</th>
                                        <th style="font-size: 20px" scope="col">Quantity</th>
                                        <th style="font-size: 20px" scope="col">Picture</th>
                                        <th style="font-size: 20px" scope="col">Category</th>
                                        <th style="font-size: 20px" scope="col">Insert</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:set var="count" value="0"/>
                                    <c:forEach items="${requestScope.data}" var="pros">
                                        <c:set var="count" value="${count+1}"/>
                                        <tr>
                                            <td>${count}</td>
                                            <td>${pros.id}</td>
                                            <td>${pros.name}</td>
                                            <td>${pros.description}</td>
                                            <td>${pros.price}</td>
                                            <td>${pros.quantity}</td>
                                            <td><img src="assets/picture_pro/${pros.picture}" alt="alt" width="100px" height="100px"/></td>
                                            <td>${pros.catergory.name}</td>
                                            <td>
                                                <a class="btn btn-sm btn-primary" href="updateProduct?id=${pros.id}">update</a>
                                                <a class="btn btn-sm btn-primary" href="#" onclick="doDelete('${pros.id}')">Delete</a>
                                            </td>
                                        </tr> 
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
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
