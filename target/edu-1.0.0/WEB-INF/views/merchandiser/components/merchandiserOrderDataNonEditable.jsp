<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div style="float: left; width: 50%;">
	<table>
		<tr>
			<td><spring:message code="merch.orStatus" />
			</td>
			<td><select name="orderStatus" disabled="disabled">
					<option value="Ordered"
						<c:if test="${orderStatus eq 'Ordered'}">selected="selected"</c:if>>
						<spring:message code="merch.ordered" />
					</option>
					<option value="Pending"
						<c:if test="${orderStatus eq 'Pending'}">selected="selected"</c:if>>
						<spring:message code="merch.pending" />
					</option>
					<option value="Delivered"
						<c:if test="${orderStatus eq 'Delivered'}">selected="selected"</c:if>>
						<spring:message code="merch.delivered" />
					</option>
			</select>
			</td>
		</tr>
		<tr>
			<td><spring:message code="merch.delDate" />
			</td>
			<td><input type="text" name="deliveryDate" disabled="disabled"
				value="${deliveryDate}"></td>
		</tr>
		<tr>
			<td><spring:message code="merch.gift" /></td>
			<td><input type="checkbox" name="isGift" disabled="disabled"
				<c:if test="${isGift}">checked="checked"</c:if>></td>
		</tr>
	</table>
</div>