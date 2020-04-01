<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="com.softserveinc.edu.oms.web.util.pageable.PageActions"%>

<c:set var="firstPageAction" value="<%=PageActions.FirstPageAction%>" />
<c:set var="previousPageAction"
	value="<%=PageActions.PreviousPageAction%>" />
<c:set var="nextPageAction" value="<%=PageActions.NextPageAction%>" />
<c:set var="lastPageAction" value="<%=PageActions.LastPageAction%>" />

<form method="post" action="${controlerWithoutAjaxUrl}"
	style="display: inline;">
	<input type="hidden" name="page"
		value="${firstPageAction.getActionResultPage(pageModel)}" /> <input
		type="hidden" name="showElements"
		value="${pageModel.getShowElements().getIntegerValue()}" />
	<c:forEach items="${pageModel.getListOfRequiredParameters()}"
		var="requiredParameter">
		<input type="hidden" name="${requiredParameter.getParameterName()}"
			value="${requiredParameter.getParameterValue()}" />
	</c:forEach>
	<input type="submit"
		value="<spring:message code="pageable.firstPage" />"
		style="display: inline;"
		<c:if test="${pageModel.isPageActionDisabled(firstPageAction)}"> disabled="disabled" </c:if> />
</form>
<form method="post" action="${controlerWithoutAjaxUrl}"
	style="display: inline;">
	<input type="hidden" name="page"
		value="${previousPageAction.getActionResultPage(pageModel)}" /> <input
		type="hidden" name="showElements"
		value="${pageModel.getShowElements().getIntegerValue()}" />
	<c:forEach items="${pageModel.getListOfRequiredParameters()}"
		var="requiredParameter">
		<input type="hidden" name="${requiredParameter.getParameterName()}"
			value="${requiredParameter.getParameterValue()}" />
	</c:forEach>
	<input type="submit"
		value="<spring:message code="pageable.previousPage" />"
		style="display: inline;"
		<c:if test="${pageModel.isPageActionDisabled(previousPageAction)}"> disabled="disabled" </c:if> />
</form>
<form method="post" action="${controlerWithoutAjaxUrl}"
	style="display: inline;">
	<input type="hidden" name="page"
		value="${nextPageAction.getActionResultPage(pageModel)}" /> <input
		type="hidden" name="showElements"
		value="${pageModel.getShowElements().getIntegerValue()}" />
	<c:forEach items="${pageModel.getListOfRequiredParameters()}"
		var="requiredParameter">
		<input type="hidden" name="${requiredParameter.getParameterName()}"
			value="${requiredParameter.getParameterValue()}" />
	</c:forEach>
	<input type="submit"
		value="<spring:message code="pageable.nextPage" />"
		style="display: inline;"
		<c:if test="${pageModel.isPageActionDisabled(nextPageAction)}"> disabled="disabled" </c:if> />
</form>
<form method="post" action="${controlerWithoutAjaxUrl}"
	style="display: inline;">
	<input type="hidden" name="page"
		value="${lastPageAction.getActionResultPage(pageModel)}" /> <input
		type="hidden" name="showElements"
		value="${pageModel.getShowElements().getIntegerValue()}" />
	<c:forEach items="${pageModel.getListOfRequiredParameters()}"
		var="requiredParameter">
		<input type="hidden" name="${requiredParameter.getParameterName()}"
			value="${requiredParameter.getParameterValue()}" />
	</c:forEach>
	<input type="submit"
		value="<spring:message code="pageable.lastPage" />"
		style="display: inline;"
		<c:if test="${pageModel.isPageActionDisabled(lastPageAction)}"> disabled="disabled" </c:if> />
</form>
