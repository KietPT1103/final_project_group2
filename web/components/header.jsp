<%-- 
    Document   : header
    Created on : Sep 19, 2023, 9:54:20 PM
    Author     : ACER
--%>



        <div class="header">
            <div class="container">
                <div class="navbar">
                    <div class="logo">
                        <a href="index.jsp"><img src="./assets/images/logo.png" alt="logo" width="125px"></a>
                    </div>
                    <nav>
                        <ul  id="MenuItems">
                            <li><a style="text-decoration: none" href="home">Home</a></li>
                            <li><a style="text-decoration: none" href="products">Products</a></li>
                            <li><a  style="text-decoration: none" style="text-decoration: none" href="">About</a></li>
                            <li><a href="">Contact</a></li>
                                <c:if test="${sessionScope.account==null}">
                                <li><a style="text-decoration: none" href="account">log in</a></li>
                                </c:if>
                                <c:if test="${sessionScope.account!=null}">
                                    <li><a style="text-decoration: none" href="information"><i style="font-size: 18px" class="fa-regular fa-user"></i> ${sessionScope.account.fullName}</a></li>
                                </c:if>
                        </ul>
                    </nav>
                    <a href="cart.html"><img src="./assets/images/cart.png" width="30px" height="30px"></a>
                    <img src="./assets/images/menu.png" class="menu-icon" onclick="menutoggle()">
                </div>
            </div>
        </div>
