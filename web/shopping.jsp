<%-- 
    Document   : shopping
    Created on : Oct 16, 2023, 3:59:14 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--Thêm định dạng cho số thực của giá sản phẩm-->
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shop page</title>
        <link rel="stylesheet" href="css/style.css"/>
        <!--css-->
        <style>
            /* Reset some default styles */
            body, h1, h2, h3, p, table {
                margin: 0;
                padding: 0;
            }

            /* Set a background color for the body */
            body {
                background-color: #f4f4f4;
                font-family: Arial, sans-serif;
            }

            /* Style the container */
            .container {
                width: 80%;
                margin: 0 auto;
                padding: 20px;
                background-color: #fff;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                border-radius: 5px;
            }

            /* Style the header */
            h2 {
                text-align: center;
                margin-bottom: 20px;
                color: #333;
            }

            /* Style the table */
            table {
                width: 100%;
                border-collapse: collapse;
            }

            table, th, td {
                border: 1px solid #ddd;
            }

            th, td {
                padding: 10px;
                text-align: left;
            }

            /* Style table header row */
            th {
                background-color: #333;
                color: #fff;
            }

            /* Style alternating rows */
            tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            /* Style the "Buy" button */
            input[type="button"] {
                background-color: #007bff;
                color: #fff;
                border: none;
                padding: 5px 10px;
                cursor: pointer;
            }

            input[type="button"]:hover {
                background-color: #0056b3;
            }

            /* Style the number input */
            input[type="number"] {
                width: 50px;
                text-align: center;
            }

            /* Style the product image */
            img {
                max-width: 200px;
                max-height: 200px;
            }

            /* Add some spacing to the page */
            body-content {
                margin-top: 20px;
            }

            /* Style the product description */
            td:nth-child(6) {
                max-width: 300px;
                white-space: nowrap;
                overflow: hidden;
                text-overflow: ellipsis;
            }

            /* Add some spacing between table rows */
            tr {
                margin-bottom: 10px;
            }

            /* Style links */
            a {
                color: #007bff;
                text-decoration: none;
            }

            a:hover {
                text-decoration: underline;
            }

        </style>
    </head>
    <body>
        <div class="container body-content">
            <h2>List of Products</h2>
            <p id="bag">
                <a href="show">My Cart (${requestScope.size})</a>
            </p>
            <form name="f" action="" method="post">
                <input style="text-align: center" type="number" name="num" value="1">
                <table class="table table-bordered" border="1px">
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
                    <c:forEach items="${requestScope.data}" var="p">  
                        <c:set var="id" value="${p.id}"/>
                        <tr>
                            <td>${id}</td>
                            <td>${p.name}</td>
                            <td><fmt:formatNumber pattern="##.#" value="${p.price}"/></td>
                            <td>${p.quantity}</td>
                            <td><img src="./assets/images/${p.picture}" alt="${p.picture}" width="200px" height="200px"/></td>
                            <td>${p.description}</td>
                            <td>${p.catergory.id}</td>
                            <td>
                                <input type="button" value="Buy" onclick="buy('${id}')"/>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </form>
        </div>

        <script>
            function buy(id) {
                var m = document.f.num.value;
                document.f.action = "buy?id=" + id + "&num=" + m;
                document.f.submit();
            }
        </script>

    </body>
</html>
