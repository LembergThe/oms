<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page
	import="com.softserveinc.edu.oms.web.util.pageable.ParameterPair"%>
<%@ page import="com.softserveinc.edu.oms.web.util.pageable.PageModel"%>
<%@ page
	import="com.softserveinc.edu.oms.web.util.pageable.PageableParameters"%>
<%@ page import="com.softserveinc.edu.oms.web.ParameterNames"%>

<script type="text/javascript" src="js/jquery-1.6.2.js"></script>

<div id="list">
	<script type="text/javascript">
		$(document).ready(function() {
			AjaxPageLoad('', '');
		});

		function AjaxPageLoad(pageParam, showElementsParam) {
			jQuery.get('merchandiserPageable.htm', {
				page : pageParam,
				showElements : showElementsParam,
				orderId :
	<%=request.getParameter(ParameterNames.ORDER_ID)%>
		}, function(data) {
				$("#pageable").html(data);
			});
		}
	</script>
	<div id="list">
		<h3>
			<spring:message code="merch.merchandiser" />
		</h3>
		<div id="pageable">
			<spring:message code="merch.toSimpleHtml" />
			<form name="withoutAjax" method="post"
				action="merchandiserWithoutAjax.htm" style="display: inline;">
				<input type="hidden" name="orderId" value="${selectedOrderID}">
				<a href="javascript:document.withoutAjax.submit()"><spring:message
						code="merch.woajax" /> </a>
			</form>
		</div>
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
						page="components/merchandiserOrderDataEditWithAjax.jsp" />
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