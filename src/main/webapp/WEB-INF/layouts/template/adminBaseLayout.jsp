<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <%--Add a list of menu options from navigation file--%>
        <ul class="nav navbar-nav">
            <tiles:insertAttribute name="adminNavigation"/>
        </ul>
        <%--Logout button--%>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="<c:url value="/logout"/>">
                <span class="glyphicon glyphicon-log-out"></span>Logout</a>
            </li>
        </ul>
    </div>
</nav>
<section>
    <div class="pull-right" style="padding-right:50px">
        <a href="?language=en">English</a>|<a href="?language=pt_br">Portuguese</a>
    </div>
</section>
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
