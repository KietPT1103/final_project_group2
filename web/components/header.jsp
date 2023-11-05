<%-- 
    Document   : header
    Created on : Sep 19, 2023, 9:54:20 PM
    Author     : ACER
--%>



<div class="header">
    <div class="container">
        <div class="navbar">
            <div class="logo">
                <a href="home"><img src="./assets/images/logo.png" alt="logo" width="125px"></a>
            </div>
            <nav>
                <ul  id="MenuItems">
                    <li><a class="header__link style="text-decoration: none" href="home">Home</a></li>
                    <c:if test="${sessionScope.account!=null && sessionScope.account.role==1}">
                        <li><a class="header__link" style="text-decoration: none" href="admin"><i class="fa-solid fa-shop"></i> Admin</a></li>
                    </c:if>
                    <li><a class="header__link" style="text-decoration: none" href="products">Products</a></li>
                    <li><a  class="header__link" style="text-decoration: none" style="text-decoration: none" href="">About</a></li>
                    <li><a class="header__link"href="">Contact</a></li>
                    <c:if test="${sessionScope.account==null}">
                        <li><a class="header__link" style="text-decoration: none" href="account">Log in</a></li>
                    </c:if>
                    <c:if test="${sessionScope.account!=null}">
                        <li><a class="header__link" style="text-decoration: none" href="information"><i style="font-size: 18px" class="fa-regular fa-user"></i> ${sessionScope.account.fullName}</a></li>
                    </c:if>
                </ul>
            </nav>
            <a href="Cart"><i class="header__link-cart fa-brands fa-opencart"></i></a>
            <a class="menu-icon" href="#"><i class="fa-solid fa-bars" style="color: #ffffff;" onclick="menutoggle()"></i></a>           
        </div>
    </div>
</div>
