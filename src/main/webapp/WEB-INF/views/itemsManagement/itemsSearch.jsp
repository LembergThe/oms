<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h4>
	<spring:message code="is.pFound" /> <span id="recordsFound">${productSearchModel.recordsFound}</span>
</h4>

<fieldset>
	<legend><spring:message code="uSearch.searchBy" /></legend>
	<form:form id="searchForm" method="GET" action="${searchItems}"
		commandName="productSearchModel" style="width: 100%;">
		<form:label path=""><spring:message code="uSearch.fieldFilter" /></form:label>

		<form:select id="field" path="selectField" items="${selectFields}" />
		<form:input id="searchField" path="searchValue" style="width: 270px;" />


		<input type="submit" value="<spring:message code="liProd.search" />" name="search" />
	</form:form>

</fieldset>
<p style="margin: 0; text-align: right;">
	<a href="${resizeItemsList}"><spring:message code="uSearch.show" /> ${productSearchModel.pageSizeChange}
		<spring:message code="uSearch.items" /></a>
</p>