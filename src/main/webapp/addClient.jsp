<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>Add Client</title>
</head>
<body>
<%@include file="header.jsp"  %>
<div class="container mt-5">
    <h2 class="text-center mb-4">Add Client</h2>
    <form action="save.client" method="POST">
    
        <div class="form-group">
            <label for="nameProduct">Name:</label>
            <input type="text" class="form-control"  name="nameClient" id="nameClient" placeholder="Enter client name" required>
        </div>
        <div class="form-group">
            <label for="stock">Email:</label>
            <input type="text" class="form-control"  name="emailClient" id="emailClient" placeholder="Enter email" required>
        </div>
        
        <button type="submit" class="btn btn-primary">Add Client</button>
    </form>
</div>
</body>
</html>