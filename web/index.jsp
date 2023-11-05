<%-- 
    Document   : index
    Created on : Sep 19, 2023, 9:42:27 PM
    Author     : ACER
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home</title>
        <link rel="stylesheet" href="./css/style.css">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
              rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://kit.fontawesome.com/8143c9cd7e.js" crossorigin="anonymous"></script>
    </head>

    <body>
        <!--Header-->
        <%@ include file='./components/header.jsp' %>
        <div class="banner">
            <img src="./assets/banner/Galaxy-S23-Series_KV_Product_2p_HI.jpg"/>
        </div>

        <!-- Feadtued Categories -->
        <div class="categories">
            <div class="small-container">
                <div class="row">
                    <div class="col-3">
                        <img src="./assets/picture_pro/img1.jpg">
                    </div>
                    <div class="col-3">
                        <img src="./assets/picture_pro/img2.jpg">
                    </div>
                    <div class="col-3">
                        <img src="./assets/picture_pro/71dHUyDrENL.SS800.jpg">
                    </div>
                </div>
            </div>
        </div>
        <!-- Featured Products -->
        <div class="small-container">
            <h2 class="title">
                MOST OUTSTANDING PHONES</h2>
            <div class="row">
                <c:forEach items="${requestScope.listPro}" var="p">
                    <div class="col-4">                   
                        <a href="product_details.html"><img src="./assets/picture_pro/${p.picture}"></a>
                        <h4 style="font-weight: 800" >${p.name}</h4>
                        <div class="rating">
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star-o"></i>
                        </div>
                        <p>Price: ${p.price}$</p>
                    </div>              
                </c:forEach>
            </div>

            <h2 class="title"><a href="products">MORE</a></h2>
            <div class="row">

            </div>
        </div>

        <!-- Offer -->
        <div class="offer">
            <div class="small-container">
                <div class="row">
                    <div class="col-2">
                        <img src="./assets/images/exclusive.png" class="offer-img">
                    </div>
                    <div class="col-2">
                        <p>Exclusively Available on RedStore</p>
                        <h1>Smart Band 4</h1>
                        <small>The Mi Smart Band 4 fearures a 39.9%larger (than Mi Band 3) AMOLED color full-touch display
                            with adjustable brightness, so everything is clear as can be.<br></small>
                        <a href="products.html" class="btn">Buy Now &#8594;</a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Testimonial -->
        <div class="testimonial">
            <div class="small-container">
                <div class="row">
                    <div class="col-3">
                        <i class="fa-solid fa-check" style="color: #000000;font-size: 24px"></i>
                        <div class="rating">
                        </div>
                        <img src="./assets/banner/5087579.png">
                        <h3>Phạm Gia Tiến</h3>
                    </div>
                    <div class="col-3">
                        <i class="fa-solid fa-check" style="color: #000000;font-size: 24px"></i>
                        <div class="rating">
                        </div>
                        <img src="./assets/banner/5087579.png">
                        <h3>Nguyễn Lưu Minh Đức</h3>
                    </div>
                    <div class="col-3">
                        <i class="fa-solid fa-check" style="color: #000000;font-size: 24px"></i>
                        <div class="rating">
                        </div>
                        <img src="./assets/banner/5087579.png">
                        <h3>Phạm Tuấn Thanh</h3>
                    </div>
                    <div class="col-3">
                        <i class="fa-solid fa-check" style="color: #000000;font-size: 24px"></i>
                        <div class="rating">                       
                        </div>
                        <img src="./assets/banner/5087579.png">
                        <h3>Phạm Tuấn Kiệt</h3>
                    </div>
                    <div class="col-3">
                        <i class="fa-solid fa-check" style="color: #000000;font-size: 24px"></i>
                        <div class="rating">
                        </div>
                        <img src="./assets/banner/5087579.png">
                        <h3>Vương Việt Văn</h3>
                    </div>
                    <div class="col-3">
                        <i class="fa-solid fa-check" style="color: #000000;font-size: 24px"></i>
                        <div class="rating">          
                        </div>
                        <img src="./assets/banner/5087579.png">
                        <h3>Tô Lý Mỹ</h3>
                    </div>
                </div>
            </div>
        </div>
        <!-- Brands -->
        <div class="brands">
            <div class="small-container">
                <div class="row">
                    <div class="col-5">
                        <img src="./assets/images/logo-godrej.png">
                    </div>
                    <div class="col-5">
                        <img src="./assets/images/logo-oppo.png">
                    </div>
                    <div class="col-5">
                        <img src="./assets/images/logo-coca-cola.png">
                    </div>
                    <div class="col-5">
                        <img src="./assets/images/logo-paypal.png">
                    </div>
                    <div class="col-5">
                        <img src="./assets/images/logo-philips.png">
                    </div>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <%@ include file='./components/footer.jsp' %>