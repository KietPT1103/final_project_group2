<%-- 
    Document   : insertAdmin
    Created on : Oct 29, 2023, 9:27:54 AM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert admin</title>

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
                <div class="container_from-create">
                    <div class="form_input-create">
                        <h1>Register</h1>
                        <form id="RegForm" action="insertAdmin" method="post">
                            <h5>Full Name</h5> <input class="input-form" type="text" placeholder="Full name" name="fullName">
                            <h5>User Name</h5> <input class="input-form" type="email" placeholder="Email" name="userName">
                            <h5>Phone Number</h5> <input class="input-form" type="number" placeholder="Phone number" name="phone">
                            <h5>Address</h5> <input class="input-form" type="text" placeholder="Address" name="address">
                            <h5>Pass Word</h5> <input class="input-form" type="password" placeholder="Password" name="password">
                            <h5>Re-Pass Word</h5> <input class="input-form" type="password" placeholder="Re-enter your Password" name="rePassword">
                            <button type="submit" class="btn">Register</button>
                            <p style="color: red">${requestScope.error}</p>
                        </form>
                        <a class="back-admin" href="accountManagement" style="font-size: 20px ; border-bottom: 1px solid white ; color : white ; padding: 0 12px;">Back</a>
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
