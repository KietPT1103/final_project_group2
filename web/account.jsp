<%-- 
    Document   : account
    Created on : Sep 30, 2023, 9:51:01 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Tech Solition</title>
        <link rel="stylesheet" href="./css/style.css">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
              rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

    </head>

    <body>
        <!--Header-->
        <%@ include file='./components/header.jsp' %>

        <!-- Account Page -->
        <div class="account-page">
            <div class="container">
                <div class="row">
                    <div class="col-2">
                        <div class="form-container">
                            <div class="form-btn">
                                <span onclick="login()">Login</span>
                                <span onclick="register()">Register</span>
                                <hr id="Indicator">
                            </div>
                            <form id="LoginForm" action="login" method="post">
                                <input class="input-form" type="email" placeholder="Email" name="username" value="${cookie.cuser.value}">
                                <input class="input-form" type="password" placeholder="Password" name="password" value="${cookie.cpass.value}">
                                <div class="rememberme" >
                                    <div class="remenberme-divcon"><input id="checkremenber" class="rememberme-input" type="checkbox" name="remember" value="ON" ${cookie.crem!=null?'checked':''} /></div>
                                    <label class="remember-label" style="margin-left: 15px; cursor: pointer" for="checkremenber">remember me</label>
                                </div>
                                <button type="submit" class="btn">Login</button>
                                <!--                        <a href="">Forget Password</a>-->
                            </form>

                            <form id="RegForm">
                                <input class="input-form" type="text" placeholder="Full name">
                                <input class="input-form" type="number" placeholder="Phone number">
                                <input class="input-form" type="email" placeholder="Email">
                                <input class="input-form" type="password" placeholder="Password">
                                <input class="input-form" type="password" placeholder="Re-enter your Password">
                                <button type="submit" class="btn">Register</button>
                                <p style="color: red">${requestScope.error}</p>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer -->
        <%@ include file='./components/footer.jsp' %>