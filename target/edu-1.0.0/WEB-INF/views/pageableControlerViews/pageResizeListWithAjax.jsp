<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page
	import="com.softserveinc.edu.oms.web.util.pageable.PageShowElements"%>

<c:set var="PageShowElements"
	value="<%=PageShowElements.getAllPageShowElementsList()%>" />
<div>
	<select onChange="AjaxPageLoad(1,options[selectedIndex].value)">
		<c:forEach items="${PageShowElements}" var="pageShowElement">
			<option value="${pageShowElement.getIntegerValue()}"
				<c:if test="${pageModel.isCurrentPageShowElement(pageShowElement)}"> selected="selected" </c:if>>
				<spring:message code="pageable.show" />
				${pageShowElement.getIntegerValue()}
				<spring:message code="pageable.elements" />
			</option>
		</c:forEach>
	</select>
</div>