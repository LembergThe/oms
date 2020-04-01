<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="com.softserveinc.edu.oms.web.product.model.ListProductModel" %>

<table>
	<thead>
		<tr>
			<th>
				<form:form id="sortName" commandName="model" method="post" action="products.htm">
					<%@include file="hidden.jsp"%>
					<input type="hidden" value="<%=ListProductModel.ITEM_NAME%>" name="sort">
					<a href="javascript:$('#sortName').submit()">Item Name</a>
				</form:form>
			</th>
			<th>
				<form:form id="sortDescription" commandName="model" method="post" action="products.htm">
					<%@include file="hidden.jsp"%>
					<input type="hidden" value="<%=ListProductModel.ITEM_DESCRIPTION%>" name="sort">
					<a href="javascript:$('#sortDescription').submit()">Item Description</a>
				</form:form>
			</th>
			<th>Add</th>
		</tr>
	</thead>
	<c:forEach items="${products}"  var="product">
		<tr>
			<td>${product.productName}</td>
			<td>${product.productDescription}</td>
			<td>
				<form:form id="selectFrom${product.id }" commandName="model" method="post" action="products.htm">
					<%@include file="hidden.jsp"%>
					<input type="hidden" value="${product.id }" name="select">
					<a href="javascript:$('#selectFrom${product.id } ').submit()">Select</a>
				</form:form>
			</td>
		</tr>
	</c:forEach>
</table>