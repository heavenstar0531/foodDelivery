<%--
  User: mz
  Date: 17/07/17
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<section class="container" <%--ng-app="cartApp"--%>>
    <div class="row">
        <div class="col-md-5">
            <img src="<c:url value="/img/${product.id}.jpg"></c:url>" alt="image" style="width: 100%" />
        </div>

        <div class="col-md-5">
            <h3>${product.name}</h3>
            <p>${product.description}</p>
            <p>
                <strong>Item Code</strong>: <span class="label label-default">${product.id}</span>
            </p>
            <p>
                <strong>Category</strong> : ${product.category}
            </p>
            <p>
                <strong>Description</strong> : ${product.description}
            </p>
            <p> <strong>Ingredients</strong>:
                <c:forEach items="${product.ingredients}" var="ingredient">
                    ${ingredient.name},
                </c:forEach>
            </p>
            <h4>${product.price} $</h4>
            <a href="<spring:url value="/admin/products" />"
               class="btn btn-default"> <span
                    class="glyphicon-hand-left glyphicon"></span> back
            </a>
            <a href="<spring:url value="/admin/product/update?id=${product.id}" />"
               class="btn btn-warning"> Edit
            </a>
            <a href="<spring:url value="/admin/product/delete?id=${product.id}" />"
               class="btn btn-danger"> Delete
            </a>

        </div>
    </div>
</section>
