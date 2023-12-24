<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1" />
    <link
      rel="stylesheet"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
    />
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <title>Add Order</title>
  </head>
  <body>
    <%@include file="header.jsp" %>

      <div class="container mt-5">
      <div class="row">
        <div class="col-md-6">
      <form id="productForm">
        <div class="form-group">
          <label for="productName">Product Name:</label>
          <select class="form-control" id="nameProduct" required>
            <c:forEach var="product" items="${addOrderModel.products}">
              <option
                data-max-quantity="${product.stock}"
                value="${product.nameProduct}"
              >
                ${product.nameProduct} - quantity : ${product.stock}
              </option>
            </c:forEach>
          </select>
        </div>

        <div class="form-group">
          <label for="quantity">Quantity: </label>
          <input type="number" class="form-control" id="quantity" required />
        </div>

        <button type="button" class="btn btn-primary" onclick="addProduct()">
          Add Product
        </button>
      </form>

     </div>
        <div class="col-md-6">
        <div class="form-group">
          <label for="nameClient">Client:</label>
          <select class="form-control" id="nameClient" required>
            <c:forEach var="client" items="${addOrderModel.clients}">
              <option
                data-id-client="${client.idClient}"
                value="${client.nameClient}"
              >
                ${client.nameClient}
              </option>
            </c:forEach>
          </select>
        </div>
          <h2 class="mt-4">Order Summary :</h2>
          <ul id="orderSummary" class="list-group"></ul>

          <button
            type="button"
            class="btn btn-success mt-3"
            onclick="submitOrder()"
          >
            Submit Order
          </button>
        </div>
      </div>
    </div>

    <script>
      const selectedProducts = new Set();
      function addProduct() {
        const select = document.getElementById("nameProduct");
        const productName = select.value;
        const selectedOption = select.options[select.selectedIndex];
        const quantity = document.getElementById("quantity").value;

        const maxQuantity = selectedOption.getAttribute("data-max-quantity");
        if (productName && quantity) {
          if (selectedProducts.has(productName)) {
            alert(
              "This product is already selected. Please choose a different one."
            );
            return;
          }
          if (quantity > parseInt(maxQuantity, 10)) {
            alert("Quantity exceeds the limit");
            return;
          }
          const orderSummary = document.getElementById("orderSummary");
          const listItem = document.createElement("li");
          listItem.className =
            "list-group-item d-flex justify-content-between align-items-center";
          const productInfoSpan = document.createElement("span");
          productInfoSpan.innerHTML = productName + " - Quantity: " + quantity;

          const deleteButton = document.createElement("button");
          deleteButton.className = "btn btn-danger btn-sm";
          deleteButton.textContent = "X";
          deleteButton.onclick = function () {
            orderSummary.removeChild(listItem);
            selectedProducts.delete(productName);
          };

          listItem.appendChild(productInfoSpan);
          listItem.appendChild(deleteButton);

          orderSummary.appendChild(listItem);
          selectedProducts.add(productName);
          document.getElementById("nameProduct").value = "";
          document.getElementById("quantity").value = "";
        } else {
          alert("Please enter both product name and quantity.");
        }
      }

      function submitOrder() {
        const select = document.getElementById("nameClient");
        const nameClient = select.value;
        const selectedOption = select.options[select.selectedIndex];
        const idClient = selectedOption.getAttribute("data-id-client");
        console.log(idClient);
        const orderItems = document.getElementById("orderSummary").children;
        const orderData = [];

        for (let i = 0; i < orderItems.length; i++) {
          const listItem = orderItems[i];
          const productInfoSpan = listItem.querySelector("span");
          const [productName, quantity] =
            productInfoSpan.textContent.split(" - Quantity: ");
          orderData.push({ productName, quantity });
        }
        for (var i = 0; i < orderData.length; i++) {
          console.log(orderData[i]);
        }

        const dataToSend = {
          idClient: idClient,
          data: orderData,
        };
        console.log(dataToSend);
        fetch("/craftstore/save.order", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(dataToSend),
        })
          .then((response) => {
            if (!response.ok) {
              throw new Error("Network response was not ok");
            }
            window.location.href = "/craftstore/index.order";
          })
          .catch((error) => {
            console.error("Error:", error);
          });
        document.getElementById("orderSummary").innerHTML = "";
      }
    </script>
  </body>
</html>
