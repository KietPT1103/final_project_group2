<%-- 
    Document   : cart
    Created on : Oct 27, 2023, 3:00:37 PM
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
        <title>Cart</title>
        <link rel="stylesheet" href="./css/style.css">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
              rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://kit.fontawesome.com/8143c9cd7e.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <!--Header-->
        <%@ include file='./components/header.jsp' %>

        <!-- Cart items details -->                       
        <div class="small-container cart-page">
            <table class="cart-page__item">
                <tr style="border-radius: 3px;">
                    <th>Product</th>
                    <th>Quantity</th>
                    <th>Subtotal</th>
                </tr>
                <c:choose>
                    <c:when test="${(not empty requestScope.cart)}">
                        <c:set var="o" value="${requestScope.cart}"/>
                        <c:set var="tt" value="0"/>
                        <c:forEach items="${o.items}" var="i">
                            <!--san pham-->              
                            <tr style="border-bottom: 1px solid #999999;">
                                <td style="padding: 22px 0px;">
                                    <div class="cart-info">
                                        <img src="./assets/picture_pro/${i.product.picture}" alt="${p.picture}">
                                        <div>
                                            <p>${i.product.name}</p>
                                            <small>Price: $<fmt:formatNumber pattern="##.#" value="${i.price}"/></small><br>
                                            <form action="process" method="post">
                                                <input type="hidden" name="id" value="${i.product.id}">
                                                <input class="input__cart" type="submit" value="Return"/>
                                            </form>  
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <button class="cart-button">
                                        <a href="process?num=-${(i.product.quantity) > 1?1:0}&id=${i.product.id}"
                                           ${(i.quantity <= 1) ? 'style="display:none;"':''}> - </a>
                                        ${i.quantity}
                                        
                                        <a href="process?num=1&id=${i.product.id}">+</a>
                                    </button>
                                </td>
                                <td><fmt:formatNumber pattern="##.#" value="${(i.price*i.quantity)}"/></td>
                            </tr>
                        </c:forEach> 
                    </table>
                    <div class="total-price">
                        <c:set var="s" value="30"/>
                        <table>
                            <tr>
                                <td>Total</td>
                                <td>$<fmt:formatNumber pattern="##.#" value="${o.totalMoney}"/></td>
                            </tr>
                        </table>
                    </div>
                    <div class="buy-confirm">
                        <form class="checkOut" action="checkout" method="post">
                            <input type="submit" value="Buy"/>
                        </form>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="small-container cart-page">
                    <table class="no-cart cart-page__item">
                        <tr>
                            <td>
                                <img src="./assets/banner/empty-cart_pro.png" alt="Mô tả ảnh">
                            </td>
                        </tr>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>
        <!--Footer-->
        <%@ include file='./components/footer.jsp' %>
    </body>
</html>
