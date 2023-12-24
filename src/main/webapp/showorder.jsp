<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <title>Order Details</title>
</head>
<body>
<%@include file="header.jsp"  %>
    <div class="container mt-5">
        <div class="jumbotron">
            <h1 class="display-5">Order Details</h1>
        </div>

        <div class="row">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h2>Order Information</h2>
                    </div>
                    <div class="card-body">
                    	<c:set var="od" value="${orderDetails[0]}" />
                        <p><strong>Order ID:</strong> #${od.idOrder} </p>
                        <p><strong>Date:</strong> ${od.dateOrder}</p>
                        <p><strong>Client:</strong> ${od.nameClient}</p>
                        <p><strong>Total Amount:</strong> ${od.totalOrder} MAD</p>
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h2>Order Items</h2>
                    </div>
                    <ul class="list-group list-group-flush">
                    <c:forEach var="od" items="${orderDetails}">
                        <li class="list-group-item">
                            <span class="productName">Product : ${od.nameProduct}</span>
                            <span class="quantity float-right">Quantity: ${od.quantity}</span>
                            <span class="price float-right mr-2">${od.total} MAD | </span>
                        </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
