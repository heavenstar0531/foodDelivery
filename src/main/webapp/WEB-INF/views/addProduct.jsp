<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  User: mz
  Date: 17/07/17
--%>
<section class="container">
    <form:form method="POST" modelAttribute="newProduct" class="form-horizontal">
        <fieldset>
            <legend>Form</legend>
                <%--ID field--%>
            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2">
                    <spring:message code="addProduct.form.productId.label"/>
                </label>
                <div class="col-lg-10">
                    <form:input id="id" path="id" type="text" class="form:input-large"/>
                </div>
            </div>
                <%--Name field--%>
            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2" for="name">
                    <spring:message code="addProduct.form.productName.label"/>
                </label>
                <div class="col-lg-10">
                    <form:input id="name" path="name" type="text" class="form:input-large"/>
                </div>
            </div>
                <%--Category--%>
            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2" for="name">
                    <spring:message code="addProduct.form.productCategory.label"/>
                </label>
                <div class="col-lg-10">
                    <form:input id="category" path="category" type="text" class="form:input-large"/>
                </div>
            </div>
                <%--Ingredients--%>
            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2" for="name">
                    <spring:message code="addProduct.form.productIngredients.label"/>
                </label>
                    <%--TODO: provide all available ingredients to create a product with multiple selection--%>
                <%--<form:select path="ingredients" multiple="true">
                    <form:options items="${availableIngredients}" itemValue="name" itemLabel="name"/>
                </form:select>--%>
            </div>
                <%--Description--%>
            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2" for="name">
                    <spring:message code="addProduct.form.productDescription.label"/>
                </label>
                <div class="col-lg-10">
                    <form:input id="description" path="description" type="text" class="form:input-large"/>
                </div>
            </div>
                <%--Price--%>
            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2" for="name">
                    <spring:message code="addProduct.form.productPrice.label"/>
                </label>
                <div class="col-lg-10">
                    <form:input id="price" path="price" type="text" class="form:input-large"/>
                </div>
            </div>
                <%--Active--%>
            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2" for="name">
                    <spring:message code="addProduct.form.productActive.label"/>
                </label>
                <div class="col-lg-10">
                    <form:radiobutton id="active" path="active" value="true"/> True
                    <form:radiobutton id="active" path="active" value="false"/> False
                </div>
            </div>
                <%--Submit button--%>
            <div class="form-group">
                <div class="col-lg-offset-2 col-lg-10">
                    <input type="submit" id="btnAdd" class="btn btn-primary" value="Add"/>
                </div>
            </div>
        </fieldset>
    </form:form>
</section>