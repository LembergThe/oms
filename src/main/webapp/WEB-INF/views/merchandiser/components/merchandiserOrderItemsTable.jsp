<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div>
	<table style="position: center">
		<thead>
			<tr>
				<th><spring:message code="merch.iNum" /></th>
				<th><spring:message code="merch.iName" /></th>
				<th><spring:message code="merch.iDesc" /></th>
				<th><spring:message code="merch.Dimen" /></th>
				<th><spring:message code="merch.price" /></th>
				<th><spring:message code="merch.quantity" /></th>
				<th><spring:message code="merch.pricePerLine" /></th>
			</tr>
		</thead>

		<c:forEach items="${listOfOrderItems}" var="seletedOrderItem">
			<tr>
				<td>${seletedOrderItem.product.id}</td>
				<td>${seletedOrderItem.product.productName}</td>
				<td>${seletedOrderItem.product.productDescription}</td>
				<td>${seletedOrderItem.dimension.dimensionName}</td>
				<td>${seletedOrderItem.itemPrice}</td>
				<td>${seletedOrderItem.quantity}</td>
				<td>${seletedOrderItem.cost}</td>
			</tr>
		</c:forEach>
	</table>
</div>