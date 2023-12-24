<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <title>Client Management</title>
</head>
<body>
<%@include file="header.jsp"  %>
<div class="container mt-5">
    <div class="row mb-3">
        <div class="col-md-6">
            <form class="form-inline" action="search.client" method="get">
                <div class="form-group">
                    <input type="text" name="Keyword" class="form-control" value="${clientModel.keyword}" placeholder="Search...">
                </div>
                <button type="submit" class="btn btn-primary">Search</button>
            </form>
        </div>
    </div>

    <div class="row mb-3">
        <div class="col-md-12 text-right">
            <a href="add.client" class="btn btn-success">Add Client</a>
        </div>
    </div>

    <table class="table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${clientModel.clients}" var="c">
            <tr>
                <td>${c.idClient}</td>
                <td>${c.nameClient}</td>
                <td>${c.emailClient}</td>
                <td>
        			<a onclick="return confirm('Are you sure to delete this client ?')"  href="delete.client?id=${c.idClient}" class="btn btn-danger">Delete</a>
                </td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

</body>
</html>
