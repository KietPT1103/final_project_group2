<%-- 
    Document   : adminuser
    Created on : Oct 20, 2023, 3:54:53 PM
    Author     : LENOVO
--%>


<nav class="navbar navbar-expand bg-light navbar-light sticky-top px-4 py-0">
    <a href="index.html" class="navbar-brand d-flex d-lg-none me-4">
        <h2 class="text-primary mb-0"><i class="fa-solid fa-house"></i></h2>
    </a>
    <!--<a href="#" class="sidebar-toggler flex-shrink-0">
        <i class="fa fa-bars"></i>
    </a>
    <form class="d-none d-md-flex ms-4">
        <input class="form-control border-0" type="search" placeholder="Search">
    </form>-->
    <div class="navbar-nav align-items-center ms-auto">
        <div class="nav-item dropdown">
            <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
                <i class="fa fa-bell me-lg-2"></i>
                <span class="d-none d-lg-inline-flex">Order notificatin</span>
            </a>
            <div class="dropdown-menu dropdown-menu-end bg-light border-0 rounded-0 rounded-bottom m-0">
                <c:forEach items="${sessionScope.listOrder}" var="order">
                    <c:if test="${!order.nodication}">
                        <a href="orderDetailManagement?orderId=${order.id}" class="dropdown-item">
                            <h6 class="fw-normal mb-0">Order : ${order.userName}</h6>
                            <small>Total: ${order.totalMoney}</small>
                        </a>
                        <hr class="dropdown-divider">
                    </c:if>
                </c:forEach>
                <a href="orderManagement" class="dropdown-item text-center">See all order</a>
            </div>
        </div>
        <div class="nav-item dropdown">
            <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
                <img class="rounded-circle me-lg-2" src="assets/picture/${sessionScope.account.picture}" alt="" style="width: 40px; height: 40px;">
                <span class="d-none d-lg-inline-flex">${sessionScope.account.fullName}</span>
            </a>
            <div class="dropdown-menu dropdown-menu-end bg-light border-0 rounded-0 rounded-bottom m-0">
                <a href="information" class="dropdown-item">My Profile</a>
                <a href="#" class="dropdown-item">Settings</a>
                <a href="logout" class="dropdown-item">Log Out</a>
            </div>
        </div>
    </div>
</nav>