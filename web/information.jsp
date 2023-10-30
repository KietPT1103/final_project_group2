<%-- 
    Document   : information
    Created on : Oct 18, 2023, 12:50:00 PM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Information</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="css/styleinformation.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="./css/style.css">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
              rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://kit.fontawesome.com/8143c9cd7e.js" crossorigin="anonymous"></script>
    </head>

    <body>
        <%@ include file='./components/header.jsp' %>       

        <div class="container light-style flex-grow-1 container-p-y">
            <h4 class="font-weight-bold py-3 mb-4">
                Account settings
            </h4>
            <div class="card overflow-hidden">
                <div class="row no-gutters row-bordered row-border-light">
                    <div class="col-md-3 pt-0">
                        <div class="list-group list-group-flush account-settings-links">
                            <a class="list-group-item list-group-item-action active" data-toggle="list"
                               href="#account-general">General</a>
                            <a class="list-group-item list-group-item-action" data-toggle="list"
                               href="#account-change-password">Change password</a>
                            <a class="list-group-item list-group-item-action" data-toggle="list"
                               href="#history-order">History</a>
                        </div>
                    </div>
                    <div class="col-md-9">
                        <div class="tab-content">
                            <form class="tab-pane fade active show" id="account-general">
                                <div class="card-body media align-items-center">
                                    <img src="assets/picture/${sessionScope.account.picture}" alt
                                         class="d-block ui-w-80">
                                    <div class="media-body ml-4">
                                        <h6>${sessionScope.account.fullName}</h6>
                                        <p>${sessionScope.account.userName}</p>
                                        <label style="width: 9em" class="btn btn-outline-primary">
                                            Upload 
                                            <input  type="file" class="account-settings-fileinput">
                                        </label> &nbsp;
                                    </div>
                                </div>
                                <hr class="border-light m-0">
                                <div class="card-body">
                                    <div class="form-group">
                                        <label class="form-label">Full name:</label>
                                        <input type="text" class="form-control mb-1" value="${sessionScope.account.fullName}">
                                    </div>
                                    <div class="form-group">
                                        <label class="form-label">Email</label>
                                        <input type="text" class="form-control mb-1" value="${sessionScope.account.userName}">
                                        <div class="alert alert-warning mt-3">
                                            Your email is not confirmed. Please check your inbox.<br>
                                            <a href="javascript:void(0)">Resend confirmation</a>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="form-label">Phone number:</label>
                                        <input type="text" class="form-control" value="${sessionScope.account.phone}">
                                    </div>
                                    <div class="form-group">
                                        <label class="form-label">Address: </label>
                                        <input type="text" class="form-control" value="${sessionScope.account.address}">
                                    </div>
                                    <div class="text-right mt-3">
                                        <button style="width: 8em" type="submit" class="btn btn-primary">Save</button>&nbsp;
                                    </div>
                                </div>
                            </form>
                            <form class="tab-pane fade" id="account-change-password">
                                <div class="card-body pb-2">
                                    <div class="form-group">
                                        <label class="form-label">Current password</label>
                                        <input type="password" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label class="form-label">New password</label>
                                        <input type="password" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label class="form-label">Repeat new password</label>
                                        <input type="password" class="form-control">
                                    </div>
                                    <div class="text-right mt-3">
                                        <button style="width: 8em" type="submit" class="btn btn-primary">Save</button>&nbsp;
                                        <button style="width: 9em" type="reset" class="btn btn-default">Cancel</button>
                                    </div>
                                </div>
                            </form>
                            <form class="tab-pane fade" id="history-order">
                                <div class="card-body pb-2">
                                    <div class="table-container">
                                        <table class="custom-table">
                                            <thead class="table-header">
                                                <tr>
                                                    <th>ID Order</th>
                                                    <th>Product</th>
                                                    <th>Product Picture</th>
                                                    <th>Quantity</th>
                                                    <th>Price</th>
                                                    <th>Date</th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:set var="tt" value="0"/>
                                                <c:forEach items="${requestScope.history}" var="i">
                                                    <c:set var="tt" value="${tt+1}"/>
                                                    <tr>
                                                        <td>${tt}</td>
                                                        <td>${i.product.name}</td>
                                                        <td><img src="assets/picture_pro/${i.product.picture}" alt="Order ${tt}" width="150px" height="150px"></td>
                                                        <td>${i.quantity}</td>
                                                        <td>$<fmt:formatNumber pattern="##.##" value="${(i.price * i.quantity)}"/></td>
                                                        <td>${i.date}</td>
                                                        <td><a href="productsdetail?id=${i.product.id}">See product</a></td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div  class="text-right mt-3">
        <a style="margin-right: 100px" class="btn btn-primary" href="logout">Sign out</a>
    </div>

    <script data-cfasync="false" src="/cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript">
    </script>
    <%@ include file='./components/footer.jsp' %>

