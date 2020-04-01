<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page
	import="com.softserveinc.edu.oms.web.util.pageable.PageShowElements;"%>

<c:set var="PageShowElements"
	value="<%=PageShowElements.getAllPageShowElementsList()%>" />
<div>
	<form name="selectPageShowElement" action="${controlerWithoutAjaxUrl}"
		method="post" style="display: inline;">
		<c:forEach items="${pageModel.getListOfRequiredParameters()}"
			var="requiredParameter">
			<input type="hidden" name="${requiredParameter.getParameterName()}"
				value="${requiredParameter.getParameterValue()}" />
		</c:forEach>
		<input type="hidden" name="page" value="1" /> <select
			name="showElements"
			onChange="javascript:document.selectPageShowElement.submit()">
			<c:forEach items="${PageShowElements}" var="pageShowElement">
				<option value="${pageShowElement.getIntegerValue()}"
					<c:if test="${pageModel.isCurrentPageShowElement(pageShowElement)}"> selected="selected" </c:if>>
					<spring:message code="pageable.show" />
					${pageShowElement.getIntegerValue()}
					<spring:message code="pageable.elements" />
				</option>
			</c:forEach>
		</select>
	</form>
</div>