<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div>
	<c:choose>
		<c:when test="${pageModel.hasElementsToShow()}"><jsp:include
				page="/WEB-INF/views/pageableControlerViews/pageResizeListWithAjax.jsp"></jsp:include>
			<jsp:include page="merchandiserOrderItemsTable.jsp"></jsp:include>
			<h3>
				<spring:message code="merch.page" />
				${pageModel.currentPage} / ${pageModel.numberOfPages}
			</h3>
			<jsp:include
				page="/WEB-INF/views/pageableControlerViews/pageActionButtonsWithAjax.jsp"></jsp:include></c:when>
		<c:otherwise><spring:message code="merch.noItemsInList" /></c:otherwise>
	</c:choose>
</div>