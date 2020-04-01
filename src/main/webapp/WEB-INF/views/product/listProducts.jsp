<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript" src="js/jquery-1.6.2.js"></script>
<fieldset >
		<legend>Item search</legend>
			<%@include file="searchProduct.jsp" %>
			
			<div id="list">
				<%@include file="list.jsp" %>
			</div>

			<br>

			<%@include file="selectedItem.jsp" %>




<!-- 		<br> -->
<!-- 		<fieldset style="width: 95%; padding: 10px;"> -->
<!-- 			<table style="clear: both;"> -->
<!-- 				<tr> -->
<!-- 					<td>Item:</td> -->
<%-- 					<td><c:if --%>
<%-- 							test="${(!empty productId) &&(productId!='')&&(productId!=null)}"> --%>
<%-- 							<c:out value="${findByIDProduct.productName}" /> --%>
<%-- 						</c:if> --%>
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td>Price</td> -->
<%-- 					<td><c:if --%>
<%-- 							test="${(!empty productId) &&(productId!='')&&(productId!=null)}"> --%>
<%-- 							<c:out value="${findByIDProduct.productPrice}" /> &nbsp$ --%>
<%-- 					</c:if> --%>
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 			</table> -->

<%-- 			<form:form method="POST" commandName="orderItemModel" --%>
<!-- 				action="addOrderItem.htm"> -->
<%-- 				<input type="hidden" name="productId" value=${productId } /> --%>
<%-- 				<input type="hidden" name="orderId" value=${orderId } /> --%>
<!-- 				<table style="border-collapse: collapse;"> -->
<!-- 					<tr> -->
<!-- 						<td>Quantity</td> -->
<%-- 						<td><form:input path="quantity" /> <form:errors --%>
<!-- 								path="quantity" /></td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td>Dimension</td> -->
<%-- 						<td><form:select path="dimensionRef" items="${dimensions}" --%>
<%-- 								itemValue="id" /> <form:errors path="dimensionRef" /></td> --%>
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td></td> -->
<%-- 						<td><c:if test="${empty productId}"> --%>
<!-- 								<script type="text/javascript"> -->
<!-- 									function message() { -->
<!-- 										alert("You must select a product."); -->
<!-- 									} -->
<!-- 								</script> -->
<%-- 							</c:if> <c:if test="${orderId=='/'|| empty orderId}"> --%>
<!-- 								<script type="text/javascript"> -->
<!-- 									function message() { -->
<!-- 										alert("You are illegal user."); -->
<!-- 									} -->
<!-- 								</script> -->
<%-- 							</c:if> <input type="reset" value="Remove" --%>
<%-- 							onclick="javascript:window.location = 'products.htm?orderId=${orderId}'" /> --%>
<!-- 							<input type="submit" onclick="javascript:message()" value="Done" /> -->
<!-- 							<input type="button" value="Cancel" -->
<!-- 							onclick="javascript:window.location = 'cancelAndRedirect.htm'" /> -->
<!-- 						<td> -->
<!-- 					</tr> -->
<!-- 				</table> -->
<%-- 			</form:form> --%>
<!-- 		</fieldset> -->
</fieldset>

