<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
request.setAttribute("IndextUrl", "/");
request.setAttribute("ProductsUrl", "/products");
request.setAttribute("UsersUrl", "/users");
request.setAttribute("LoginUrl", "/login");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>auction-online</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
</head>

    <body>
        <div id="wrapper">
            <div id="navigation" class="curved container">
                <span id="nav-home"><a href="${IndextUrl}">Home</a></span>
                <span id="nav-products"><a href="${ProductsUrl}">Products</a></span>
                <span id="nav-users"><a href="${UsersUrl}">Users</a></span>
                <span id="nav-login"><a href="${LoginUrl}">Login</a></span>
            </div>
            <div id="asf-box">
                <h3><%= "Hello"+"==>"+ session.getAttribute("login")+"<==" %></h3>
                <h1>Server:</h1>
                <h3>${pageContext.servletContext.serverInfo}</h3>
                <h2>DataBase:</h2>
                <h3><c:out value="${initParam.DriverNameDB}" /></h3>
            </div>
        </div>
    </body>

</html>
