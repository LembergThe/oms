<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div style="float: left; width: 50%;">
	<table>
		<tr>
			<td><spring:message code="merch.cName" /></td>
			<td>${customerName}</td>
		</tr>
		<tr>
			<td><spring:message code="merch.cType" /></td>
			<td>${customerType}</td>
		</tr>
		<tr>
			<td><spring:message code="merch.orNum" /></td>
			<td>${orderNumber}</td>
		</tr>
		<tr>
			<td><spring:message code="merch.totPrice" /></td>
			<td>${totalPrice}</td>
		</tr>
		<tr>
			<td><spring:message code="merch.totNumItems" /></td>
			<td>${totalNumberOfItems}</td>
		</tr>
		<tr>
			<td><spring:message code="merch.assignee" /></td>
			<td>${assigneeName}</td>
		</tr>
		<tr>
			<td><spring:message code="merch.dateOrder" /></td>
			<td>${dateOfOrdering}</td>
		</tr>
		<tr>
			<td><spring:message code="merch.prefDelivDate" /></td>
			<td>${preferableDeliveryDate}</td>
		</tr>
	</table>
</div>