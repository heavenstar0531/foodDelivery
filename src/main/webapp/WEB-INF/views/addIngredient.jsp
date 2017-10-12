<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  User: mz
  Date: 15/07/17
--%>

<section class="container">
    <form:form method="POST" modelAttribute="newIngredient" class="form-horizontal">
        <fieldset>
            <legend>Form</legend>
            <%--ID field--%>
            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2">
                    <spring:message code="addIngredient.form.ingredientId.label" />
                </label>
                <div class="col-lg-10">
                    <form:input id="id" path="id" type="text" class="form:input-large"/>
                </div>
            </div>
            <%--Name field--%>
            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2" for="name">
                    <spring:message code="addIngredient.form.ingredientName.label"/>
                </label>
                <div class="col-lg-10">
                    <form:input id="name" path="name" type="text" class="form:input-large"/>
                </div>
            </div>
            <%--Submit button--%>
            <div class="form-group">
                <div class="col-lg-offset-2 col-lg-10">
                    <input type="submit" id="btnAdd" class="btn btn-success"
                           value="Add" />
                    <a href="<spring:url value="/admin" />"
                       class="btn btn-default"> Cancel
                    </a>
                </div>
            </div>
        </fieldset>
    </form:form>
</section>
