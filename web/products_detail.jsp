<%-- 
    Document   : products_detail
    Created on : Sep 30, 2023, 9:46:32 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Order</title>
        <link rel="stylesheet" href="./css/style.css">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
              rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://kit.fontawesome.com/8143c9cd7e.js" crossorigin="anonymous"></script>
    </head>

    <body>
        <!--Header-->
        <%@ include file='./components/header.jsp' %>

        <!-- Single Products -->

        <div class="small-container single-product">
            <a href="home">< Back to home</a>
            <div class="row">
                <c:set var="b" value="${requestScope.p}"/>
                <div class="col-2">
                    <img src="./assets/images/${b.picture}" width="100%" id="ProductImg">
                </div>

                <div class="col-2">
                    <h1>${b.name}</h1>
                    <h4>$<fmt:formatNumber pattern="##.##" value="${b.price}"/></h4> 
                    <a href="buy?id=${b.id}" class="btn">Add To Cart</a>

                    <h3>Product Details <i class="fa fa-indent"></i></h3>
                    <br>
                    <p>${b.description}</p>
                </div>              

            </div>
        </div>

        <!-- title -->
        <div class="small-container">
            <div class="row row-2">
                <h2>Order Products</h2>
                <a href="products">View More</a>
            </div>
        </div>
        <!-- Products -->
        <div class="small-container">
            <div class="row">
                <c:forEach items="${requestScope.random}" var="a">
                    <c:set value="${a.id}" var="id"/>
                    <div class="col-4">
                        <a href="productsdetail?id=${id}">
                            <img src="./assets/images/${a.picture}">
                            <h4>${a.name}</h4>
                            <div class="rating">
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star-o"></i>
                            </div>
                            <p>$<fmt:formatNumber pattern="##.#" value="${a.price}"/></p>
                        </a>
                    </div> 
                </c:forEach> 
            </div>
        </div>

        <!-- Footer -->
        <%@ include file='./components/footer.jsp' %>