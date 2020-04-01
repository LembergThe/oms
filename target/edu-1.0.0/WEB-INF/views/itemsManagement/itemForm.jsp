<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="js/json.min.js"></script>
<script type="text/javascript" src="js/jquery-1.6.2.js"></script>

<div id="edit">
	<h3><spring:message code="if.h3" /></h3>

	<br />
	<form:form method="POST" commandName="productModel"
		style="margin: 0; text-align: left;">
		<form:hidden path="id" />

		<table>
			<tr>
				<td><spring:message code="if.pName" /></td>
				<td><form:input path="productName" id="name" /></td>
			</tr>
			<tr>
				<td><spring:message code="if.pDesc" /></td>
				<td><form:textarea id="description" path="productDescription" cols="27" rows="4"/>
				</td>
			</tr>
			<tr>
				<td><spring:message code="if.pPrice" /></td>
				<td><form:input id="price" path="productPrice"/>
				</td>
			</tr>
		</table>

		<input type="submit" value="<spring:message code="if.bOk" />" />
		<input type="button" value="<spring:message code="liProd.bCancel" />" 
			onClick="location.href='itemManagement.htm'" />
	</form:form>
</div>