<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<%--
  User: mz
  Date: 15/07/17
--%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><tiles:insertAttribute name="adminTitle"/></title>
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
</head>

<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">FoodDelivery</a>
        </div>
        <ul class="nav navbar-nav">
            <tiles:insertAttribute name="adminNavigation"/>
        </ul>
    </div>
</nav>

<div class="container">
    <div class="jumbotron">
        <h2>
            <tiles:insertAttribute name="adminHeading"/>
        </h2>
        <p>
            <tiles:insertAttribute name="adminTag"/>
        </p>
    </div>

    <div class="row">
        <tiles:insertAttribute name="adminContent"/>
    </div>

    <div class="footer">
        <tiles:insertAttribute name="adminFooter"/>
    </div>

</div>
</body>
</html>