<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

Your locale is ${locale.toString() }
	<fieldset>
		<legend><spring:message code="orderItems.itemSelection"/></legend>

		<table width="100%">
 		<tr>
 			<c:if test="${orderData.isEditable }">
	 			<td align="left">
	 				<form:form name="addItem" id="addItem" method="post" action="products.htm" style="display: inline;">
						<input type="hidden" name="orderId" value="${orderId}" style="display: inline;"/> 
						<input type="submit" value="<spring:message code="orderItems.addItem"/>" style="display: inline;"/> 
					</form:form>
	 			</td>
 			</c:if>
 			<td align="right">
 				<form:form  name="showLines" id="showLines" action="orderItemsResize.htm" method="post" style="display: inline;">
					<input type="hidden" name="orderId" id="orderId" value="${orderId}" style="display: inline;"/>
					<input type="hidden" name="page" id="page" value="${page.getCurrentPage()}" style="display: inline;"/> 
			
					<select name="rowsNumber" id="rowsNumber" onChange="javascript:document.showLines.submit()" style="display: inline;">
						<c:forEach items="${linesNumber.getRowsNumbers()}" var="rowsOption">
							<option value = "${rowsOption}"
								 <c:if test="${linesNumber.isSelected(rowsOption)}"> selected="selected" </c:if>>
								 <spring:message code="orderItems.show"/> ${rowsOption} <spring:message code="orderItems.lines"/>
							</option>
						</c:forEach>
					</select>
				</form:form></td>
 		</tr>
 		</table>

	<div id="list">
		<table border="1" width="100%" style="position: center">
			<thead>
				<tr>
					<th><spring:message code="orderItems.itemNumber"/></th>
					<th><spring:message code="orderItems.itemName"/></th>
					<th><spring:message code="orderItems.itemDescription"/></th>
					<th><spring:message code="orderItems.dimension"/></th>
					<th><spring:message code="orderItems.price"/></th>
					<th><spring:message code="orderItems.quantity"/></th>
					<th><spring:message code="orderItems.pricePerLine"/></th>
					<c:if test="${orderData.isEditable }">
						<th><spring:message code="edit"/></th>
						<th><spring:message code="delete"/></th>
					</c:if>
				</tr>
			</thead>

			<c:forEach items="${orderItems}" var="orderItem">
				<tr >
					<td>${orderItem.orderItem.product.id}</td>
					<td>${orderItem.orderItem.product.productName}</td>
					<td>${orderItem.orderItem.product.productDescription}</td>
					<td>${orderItem.orderItem.dimension.dimensionName}</td>
					<td>${orderItem.orderItem.itemPrice}</td>
					<td>${orderItem.orderItem.quantity}</td>
					<td>${orderItem.orderItem.cost}</td>
					<c:if test="${orderData.isEditable }">
						<td>
							<form:form id="edit${orderItem.temporaryId }" action="products.htm" method="post">
								<input type="hidden" name="orderId" value="${orderId}"/>
								<input type="hidden" name = "orderItemId" value="${orderItem.temporaryId }"/>
								<a href="javascript:$('#edit${orderItem.temporaryId }').submit()">
									<spring:message code="edit"/>
								</a>
							</form:form>
							
						</td>
						<td >
							<form name="deleteOrderItem" id="deleteOrderItem${orderItem.temporaryId }" method="post" action="orderItemsDelete.htm">
								<input type="hidden" name="orderId" value="${orderId }" />
								<input type="hidden" name="orderItemId" value="${orderItem.temporaryId }" /> 
								<input type="hidden" name="page" value="${page.getCurrentPage()}"/>
								<a href="javascript:$('#deleteOrderItem${orderItem.temporaryId }').submit()">
									<spring:message code="delete"/>
								</a>
							</form>
						</td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
	</div>
		<div align="Center">
		<c:forEach items="${page.getActionNames()}" var="actionName">
		
			<form method="post" action="orderItems${actionName}.htm" style="display: inline;">
				<input type="hidden" name="orderId" value="${orderId}" style = "display: inline;"/>
				<input type="hidden" name="page" value="${page.getCurrentPage()}" style = "display: inline;"/>
				
				<input type="submit" value="<spring:message code="${actionName }"/>" name = ${actionName }  style = "display: inline;"
					<c:if test="${page.isDisabled(actionName)}"> disabled="disabled" </c:if>/>
			</form>
			
		</c:forEach>
		</div>
	</fieldset>