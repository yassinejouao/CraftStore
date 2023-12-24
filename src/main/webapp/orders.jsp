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
    <title>Orders</title>
    <style>.row-INPROGRESS {
    background-color: #eaf794;
}

.row-DELIVERED {
    background-color: #95ff8a;
}

.row-CANCELED {
    background-color: #f78d8f;
}
    </style>
</head>

<body>
<%@include file="header.jsp"  %>
<div class="container mt-5">
    <div class="row mb-3">
        <div class="col-md-12 text-right">
            <a href="add.order" class="btn btn-success">Add order</a>
        </div>
    </div>

    <table class="table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Client</th>
                <th>Date</th>
                <th>Total</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${ordersModel.orders}" var="o">
            <tr id="orderRow${o.idOrder}">
                <td>${o.idOrder}</td>
                <td>${o.idClient}</td>
                <td>${o.dateOrder}</td>
                <td>${o.totalOrder}</td>
                <td>${o.statusOrder}</td>
                <td>
                    <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#editStatusModal${o.idOrder}">
                        Edit Status
                    </button>
        			<a onclick="return confirm('Are you sure to delete this Order ?')"  href="delete.order?id=${o.idOrder}" class="btn btn-danger">Delete</a>
        			<a href="show.order?id=${o.idOrder}" class="btn btn-success">Order Details</a>
                </td>
            </tr>
            <div class="modal fade" id="editStatusModal${o.idOrder}" tabindex="-1" role="dialog" aria-labelledby="editStatusModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="editStatusModalLabel">Edit Status</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form action="updatestatus.order" method="POST">
                                <input type="hidden" name="orderId" value="${o.idOrder}">
                                <label for="newStatus">New Status:</label>
                                <select name="status" id="status">
                                <c:forEach var="status" items="${statusValues}">
                					<option value="${status.name()}">${status}</option>
            					</c:forEach>
                                </select>
                                
                                <button type="submit" class="btn btn-primary">Save Changes</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script>
    function setRowColor(status, orderId) {
        var row = document.getElementById("orderRow" + orderId);
        if (row) {
            switch (status) {
                case "INPROGRESS":
                    row.classList.add("row-INPROGRESS");
                    break;
                case "DELIVERED":
                    row.classList.add("row-DELIVERED");
                    break;
                case "CANCELED":
                    row.classList.add("row-CANCELED");
                    break;
            }
        }
    }
    <c:forEach items="${ordersModel.orders}" var="o">
        setRowColor("${o.statusOrder}", "${o.idOrder}");
    </c:forEach>
</script>

</body>
</html>
