<%-- 
    Document   : html
    Created on : Oct 2, 2023, 8:46:33 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--Thư viện dùng c-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Web Application Development</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/style.css"/>
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">PRJ301</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Products</a></li>
                </ul>
            </div>
        </nav>
        <div class="container body-content">
            <h2>List of Products</h2>
            <p>
            <form action="Search?index=1" method="POST">
                <input value="${save}" type="text" name="search" class="search-box" placeholder="Search"/>
                <input type="submit" class="search-btn" name="btnGo" value="Go"/>
            </form>
            <table class="table table-bordered">
                <tr>
                    <th>ID</th>
                    <th>Product Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Picture</th>
                    <th>Description</th>
                    <th>Category Name</th>
                    <th></th>
                </tr>
                <c:forEach items="${list}" var="p">    
                    <tr>
                        <td>${p.id}</td>
                        <td>${p.name}</td>
                        <td>${p.price}</td>
                        <td>${p.quantity}</td>
                        <td><img src="./assets/images/${p.picture}" alt="${p.picture}" width="200px" height="200px"/></td>
                        <td>${p.desciption}</td>
                        <td>${p.category.id}</td>
                        <td>
                            <a class="btn btn-success" href="Edit?sid=${p.id}">Edit</a>
                            <a class="btn btn-danger" href="DeleteNavigate?sid=${p.id}">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <div class="paging">
                <c:forEach begin="1" end="${end}" var="i">
                    <a id="${i}" href="Search?index=${i}&search=${save}">${i}</a>
                </c:forEach>
            </div>
        </div>
        <script>
            document.getElementById('${index}').style.backgroundColor = "red";
        </script>
    </body>
</html>
