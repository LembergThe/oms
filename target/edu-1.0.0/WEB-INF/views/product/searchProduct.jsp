<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="com.softserveinc.edu.oms.web.product.model.ListProductModel" %>
Search for item by:
<form:form method="POST" action="products.htm"
	commandName="model">
	<form:hidden path="orderId"/>
	<form:hidden path="orderItemId"/>
	<form:hidden path="sortPropertyName"/>
	<form:hidden path="ascending"/>
	<form:hidden path="productId"/>
	<form:hidden path="quantity"/>
	<form:hidden path="dimension"/>
	
				
	<form:select path="searchProperty">
		<form:option value="<%=ListProductModel.ITEM_NAME%>" />
		<form:option value="<%=ListProductModel.ITEM_DESCRIPTION%>" />
	</form:select>

	
	<form:input path="searchValue" value = "${searchValue }"/>
				
	<input type="submit" value = "Search"/>				
</form:form>