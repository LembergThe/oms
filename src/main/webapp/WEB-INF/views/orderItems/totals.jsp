<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<script type="text/javascript" charset="utf-8">
	
	
	$(function() {
		
		Date.format = 'dd/mm/yyyy';
		$('#dateDays').datePicker().val(new Date().asString())
				.trigger('change');
		
	});
	
</script>

<style type="text/css">
#orderNum {
	width: 135px;
}

#legendHeight {
	height: 18px;
	line-height: 17px;
}
</style>
<div id="edit">
	<fieldset style="height: 306px">
		<legend id="legendHeight">
			<strong> <spring:message code="totals.Totals" />
			</strong>
		</legend>

		<form name="saveButton" id="saveButton" action="orderItemsSave.htm"
			method="post" style="display: inline;">
			<input type="hidden" name="orderId" value="${orderId}"
				style="display: inline;" /> <input type="hidden" name="page"
				value="${page.getCurrentPage()}" style="display: inline;" />

			<table>
				<tr>
					<td width="163"><spring:message code="totals.ordNum" />
					</td>
					<td width="135"><input type="text" name="orderNumber"
						id="orderNum" value="${orderNumber }" /></td>
				</tr>
				<tr>
					<td><spring:message code="totals.status" />
					</td>
					<td>${orderData.tempOrder.order.orderStatus.orderStatusName}</td>
				</tr>
				<tr>
					<td><spring:message code="totals.totPrice" />
					</td>
					<td>${orderData.tempOrder.order.totalPrice }</td>
				</tr>
				<tr>
					<td><spring:message code="totals.totNumItems" />
					</td>
					<td>${orderData.numberOfItems}</td>
				</tr>
				<tr>
					<td><spring:message code="totals.dateOrdering" />
					</td>
					<td>${orderData.tempOrder.order.orderDate }</td>
				</tr>
				<tr>
					<td><spring:message code="totals.prefDeliveryDate" />
					</td>
					<td><input name="deliveryDate" type="text" class="date-pick"
						id="dateDays" style="width: 110px" onfocus="setFormat();" />
					</td>
				</tr>
				<tr>
					<td><spring:message code="totals.deliveryDate" />
					</td>
					<td>//</td>
				</tr>
				<tr>
					<td><spring:message code="totals.assignee" />
					</td>
					<td><select name="assignee" id="assignee">
							<option value="-me-">-me-</option>
							<c:forEach items="${users}" var="user">
								<option value="${user.login}"
									<c:if test="${!empty assignee &&assignee.equals(user.login) }"> selected="selected"</c:if>>
									${user.login}</option>
							</c:forEach>
					</select></td>
				</tr>
			</table>
		</form>
	</fieldset>
</div>