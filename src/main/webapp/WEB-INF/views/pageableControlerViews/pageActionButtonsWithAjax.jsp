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
<div>
	<form style="display: inline;">
		<input type="button"
			value="<spring:message code="pageable.firstPage" />"
			<c:if test="${pageModel.isPageActionDisabled(firstPageAction)}"> disabled="disabled" </c:if>
			onclick="AjaxPageLoad(${firstPageAction.getActionResultPage(pageModel)},${pageModel.getShowElements().getIntegerValue()})" />
	</form>
	<form style="display: inline;">
		<input type="button"
			value="<spring:message code="pageable.previousPage" />"
			<c:if test="${pageModel.isPageActionDisabled(previousPageAction)}"> disabled="disabled" </c:if>
			onclick="AjaxPageLoad(${previousPageAction.getActionResultPage(pageModel)},${pageModel.getShowElements().getIntegerValue()})" />
	</form>
	<form style="display: inline;">
		<input type="button"
			value="<spring:message code="pageable.nextPage" />"
			<c:if test="${pageModel.isPageActionDisabled(nextPageAction)}"> disabled="disabled" </c:if>
			onclick="AjaxPageLoad(${nextPageAction.getActionResultPage(pageModel)},${pageModel.getShowElements().getIntegerValue()})" />
	</form>
	<form style="display: inline;">
		<input type="button"
			value="<spring:message code="pageable.lastPage" />"
			<c:if test="${pageModel.isPageActionDisabled(lastPageAction)}"> disabled="disabled" </c:if>
			onclick="AjaxPageLoad(${lastPageAction.getActionResultPage(pageModel)},${pageModel.getShowElements().getIntegerValue()})" />
	</form>
</div>