<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>Confirmation</title>
</head>
<body>
<%@include file="header.jsp"  %>
<div class="container mt-5">
    <h2 class="text-center mb-4">Confirmation</h2>
    <div class="card text-center">
    ${message}   
    </div>
</div>
</body>
</html>