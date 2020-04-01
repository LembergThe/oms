<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div align="Center" style="margin: 0; text-align: center;">
	<form action="${page}" style="margin: 0; text-align: center; display: inline;">
		<input type="hidden" name="pageCommand" value="first"
			style="display: inline;" /> <input id="first" type="submit"
			value="<spring:message code="uNavi.first" />" name="first"
			<c:if test="${!productSearchModel.isFirstPage()}"> disabled="disabled" </c:if> />
	</form>

	<form action="${page}" style="margin: 0; text-align: center; display: inline;">
		<input type="hidden" name="pageCommand" value="previous"
			style="display: inline;" /> <input id="previous" type="submit"
			value="<spring:message code="uNavi.back" />" name="previous"
			<c:if test="${!productSearchModel.isPreviousPage()}"> disabled="disabled" </c:if> />
	</form>

	<form action="${page}" style="margin: 0; text-align: center; display: inline;">
		<input type="hidden" name="pageCommand" value="next"
			style="display: inline;" /> <input id="next" type="submit"
			value="<spring:message code="uNavi.forw" />" name="next"
			<c:if test="${!productSearchModel.isNextPage()}"> disabled="disabled" </c:if> />
	</form>

	<form action="${page}" style="margin: 0; text-align: center; display: inline;">
		<input type="hidden" name="pageCommand" value="last"
			style="display: inline;" /> <input id="last" type="submit"
			value="<spring:message code="uNavi.last" />" name="last"
			<c:if test="${!productSearchModel.isLastPage()}"> disabled="disabled" </c:if> />
	</form>
</div>


<h4>
	<spring:message code="uNavi.page" /> #: <span id="pageNumber">${pageNumber}</span> <spring:message code="uNavi.of" /> <span
		id="pageCount">${pages}</span>
</h4>