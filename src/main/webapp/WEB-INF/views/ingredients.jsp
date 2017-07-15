<%--
  User: mz
  Date: 15/07/17
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--List of ingredients--%>
<section class="container">
    <ul class="list-group">
        <li class="list-group-item">ID - NAME</li>
        <c:forEach items="${ingredients}" var="ingredient">
            <li class="list-group-item">${ingredient.id} - ${ingredient.name}</li>
        </c:forEach>
    </ul>
</section>
