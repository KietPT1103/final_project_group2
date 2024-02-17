<%-- 
Document   : products
Created on : Sep 30, 2023, 9:42:31 AM
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
        <title>Shopping</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
              rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://kit.fontawesome.com/8143c9cd7e.js" crossorigin="anonymous"></script>
        <script src="https://kit.fontawesome.com/26eb80e241.js" crossorigin="anonymous"></script>
        <style>
            #myList {
                display: none;
            }
        </style>
    </head>

    <body>
        <!--Header-->
        <%@ include file='./components/header.jsp' %>

        <div class="small-container">
            <form action="Search?index=1&sort=asc" method="POST">
                <input value="${save}" type="text" name="search" placeholder="Searching..." id="searchBox" class="searchBox"/>
                <button type="submit" class="searchBtn">
                    <i class="fa-solid fa-magnifying-glass"></i>
                </button>
            </form>

            <div class="row row-2">
                <h1>All</h1>               
                <div class="select-input">
                    <span class="select-input__label">Giá</span>
                    <i class="header__select-select-icon fa-solid fa-chevron-down"></i>
                    <!-- List option -->
                    <ul class="select-input__list">
                        <li class="selec-input__item">
                            <a href="Search?index=1&sort=asc&search=${save}" class="select-input__link">Giá: Thấp đến cao</a>
                        </li>
                        <li class="selec-input__item">
                            <a href="Search?index=1&sort=desc&search=${save}" class="select-input__link">Giá: Cao đến Thấp</a>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="row">
                <c:forEach items="${requestScope.data}" var="a">
                    <c:set value="${a.id}" var="id"/>
                    <div class="col-4">
                        <a href="productsdetail?id=${id}">
                            <img src="./assets/picture_pro/${a.picture}">
                            <h4>${a.name}</h4>
                            <div class="rating">
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star-o"></i>
                            </div>
                            <p>Price: $<fmt:formatNumber pattern="##.#" value="${a.price}"/></p>
                        </a>
                    </div> 
                </c:forEach>   
            </div>
            <c:set var="so" value="${requestScope.sort}"/>

            <div class="page-btn">
                <a href="#"><i class="page-btn__chervo fa-solid fa-chevron-left"></i></a>
                    <c:forEach  begin="1" end="${end}" var="i">
                    <a class="page-btn__a" href="Search?index=${i}&sort=${so}&search=${save}">${i}</a>
                </c:forEach>
                <!--                <span>&#8594;</span>-->
                <a href="#"><i class="page-btn__chervo fa-solid fa-chevron-right"></i></a>
            </div>


        </div>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const pageNumbers = document.querySelectorAll('.page-btn__a');
                pageNumbers.forEach(function (pageNumber) {
                    pageNumber.addEventListener('click', function () {
                        pageNumbers.forEach(function (el) {
                            el.classList.remove('active');
                        });
                        this.classList.add('active');
                    });
                });
            });
        </script>
        <script>
            function show() {
                var sort = document.getElementById("Sort");
                var listItems = this.getElementsById("myList");
                listItems.style.display = "block";
            }
        </script>
        <!-- Footer -->
        <%@ include file='./components/footer.jsp' %>