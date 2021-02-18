
<%@ page session="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%
request.setAttribute("IndextUrl", "/");
request.setAttribute("ProductsUrl", "/products");
request.setAttribute("UsersUrl", "/users");
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
                <span id="nav-home"><a href="${tomcatUrl}">Home</a></span>
                <span id="nav-products"><a href="${ProductsUrl}">Products</a></span>
                <span id="nav-users"><a href="${UsersUrl}">Users</a></span>
            </div>
            <div id="asf-box">
                <h1>${pageContext.servletContext.serverInfo}</h1>
            </div>
        </div>
    </body>

</html>
