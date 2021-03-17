
<%
    request.setAttribute("indexUrl", "/");
    request.setAttribute("ProductsUrl", "/products");
    request.setAttribute("UsersUrl", "/users");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <h3><%= "Hello"+"==>"+ session.getAttribute("login")+"<==" %></h3>
    <h1>Users</h1>
    <table class="table" align="center">
        <thead class="thead-dark" align="center">
        <tr>
            <th scope="col">id</th>
            <th scope="col">UserName</th>
            <th scope="col">Adress</th>
            <th scope="col">Login</th>
            <th scope="col">Bid List</th>
        </tr>
        </thead>
        <tbody align="center">
        <jsp:useBean id="allUserList" scope="request" type="java.util.List"/>
        <c:forEach items="${allUserList}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.userName}</td>
                <td>${user.adress}</td>
                <td>${user.login}</td>
                <td><form action="users" method="post">
                    <input type="hidden" name="id" value="${user.id}">
                    <input type="submit" value="Bid LIst">
                </form></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
