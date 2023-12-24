<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>Add Product</title>
</head>
<body>
<%@include file="header.jsp"  %>
<div class="container mt-5">
    <h2 class="text-center mb-4">Add Product</h2>
    <form action="save.product" method="POST">
    
        <div class="form-group">
            <label for="nameProduct">Product Name:</label>
            <input type="text" class="form-control"  name="nameProduct" id="nameProduct" placeholder="Enter product name" required>
        </div>
        <div class="form-group">
            <label for="stock">Stock:</label>
            <input type="text" class="form-control"  name="stock" id="stock" placeholder="Enter stock" required>
        </div>
        <div class="form-group">
            <label for="priceProduct">Price:</label>
            <input type="text" class="form-control" value="${product.priceProduct}" name="priceProduct" id="priceProduct" placeholder="Enter price" required>
        </div>
        <button type="submit" class="btn btn-primary">Add Product</button>
    </form>
</div>
</body>
</html>