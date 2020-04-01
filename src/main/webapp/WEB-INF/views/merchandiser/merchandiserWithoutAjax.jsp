<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="com.softserveinc.edu.oms.web.ParameterNames"%>

<div id="list">
	<div id="list">
		<spring:message code="merch.wosimple" />
		<form name="withAjax" method="post" action="merchandiser.htm"
			style="display: inline;">
			<input type="hidden" name="orderId" value="${selectedOrderID}">
			<a href="javascript:document.withAjax.submit()"><spring:message
					code="merch.toMainVersion" /> </a>
		</form>
		<c:choose>
			<c:when test="${pageModel.hasElementsToShow()}">
				<jsp:include
					page="/WEB-INF/views/pageableControlerViews/pageResizeListWithoutAjax.jsp"></jsp:include>
				<h3>
					<spring:message code="merch.h3" />
				</h3>
				<jsp:include page="components/merchandiserOrderItemsTable.jsp"></jsp:include>
				<h3>
					<spring:message code="merch.page" />
					${pageModel.getCurrentPage() } / ${pageModel.getNumberOfPages() }
				</h3>
				<jsp:include
					page="/WEB-INF/views/pageableControlerViews/pageActionButtonsWithoutAjax.jsp" />
			</c:when>
			<c:otherwise>
				<br><spring:message code="merch.noItemsInList" /></c:otherwise>
		</c:choose>
	</div>
	<div id="edit">
		<fieldset>
			<legend>
				<spring:message code="merch.totals" />
			</legend>
			<jsp:include page="components/generalOrderDataPart.jsp" />
			<c:choose>
				<c:when test="${orderStatus eq 'Delivered'}"><jsp:include
						page="components/merchandiserOrderDataNonEditable.jsp" /></c:when>
				<c:otherwise><jsp:include
						page="components/merchandiserOrderDataEditWithoutAjax.jsp" />
				</c:otherwise>
			</c:choose>
		</fieldset>
		<c:choose>
			<c:when test="${orderStatus eq 'Delivered'}"><jsp:include
					page="components/merchandiserOrderDataNonEditableButtons.jsp" /></c:when>
			<c:otherwise><jsp:include
					page="components/merchandiserOrderDataEditButtons.jsp" />
			</c:otherwise>
		</c:choose>
	</div>
</div>