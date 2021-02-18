<%--
  Created by IntelliJ IDEA.
  User: juar
  Date: 16.02.2021
  Time: 9:36
  To change this template use File | Settings | File Templates.
--%>
<%
    request.setAttribute("indexUrl", "/");
    request.setAttribute("ProductsUrl", "/products");
    request.setAttribute("UsersUrl", "/users");
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>auction-online</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
</head>
<body>
<div id="wrapper">
    <div id="navigation" class="curved container">
        <span id="nav-home"><a href="${indexUrl}">Home</a></span>
        <span id="nav-products"><a href="${ProductsUrl}">Products</a></span>
        <span id="nav-users"><a href="${UsersUrl}">Users</a></span>
    </div>
    <h1>Products</h1>
    <table class="table" align="center">
        <thead class="thead-dark" align="center">
        <tr>
            <th scope="col">Uid</th>
            <th scope="col">ProductName</th>
            <th scope="col">Description</th>
            <th scope="col">StartPrice</th>
            <th scope="col">RateStep</th>
            <th scope="col">TimeLot</th>
            <th scope="col">BuyStatus</th>
        </tr>
        </thead>
        <tbody align="center">
        <jsp:useBean id="productList" scope="request" type="java.util.List"/>
        <c:forEach items="${productList}" var="product">
            <tr>
                <td>${product.uid}</td>
                <td>${product.productName}</td>
                <td>${product.description}</td>
                <td>${product.startPrice}</td>
                <td>${product.rateStep}</td>
                <td>${product.timeLot}</td>
                <td>${product.buy_flag}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
