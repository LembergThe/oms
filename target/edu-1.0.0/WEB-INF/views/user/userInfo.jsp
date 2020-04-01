<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	
	
<div >
	<a href="?lang=en_US"><spring:message code="tabs.en" /></a>
	<a href="?lang=uk_UA"><spring:message code="tabs.ua" /></a>
	
	<fieldset>
		<legend><spring:message code="tabs.userinfo" /></legend>
		
		<table>
			<tr>
				<td><spring:message code="users.fName" /></td><td>${user.firstName }</td>
			</tr>
			
			<tr>
				<td><spring:message code="users.lName" /></td><td>${user.lastName }</td>
			</tr>
			
			<tr>
				<td><spring:message code="users.customerType" /></td><td>${user.customerType.typeName }</td>
			</tr>
			
			<tr>
				<td><spring:message code="users.role" /></td><td>${user.role.roleName }</td>
			</tr>
		</table>
	</fieldset>
</div>