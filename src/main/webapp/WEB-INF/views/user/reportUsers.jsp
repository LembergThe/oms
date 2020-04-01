<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div id="list">
	<h2><spring:message code="report.h2" /></h2>

	<a href="${getPDF}" target="_blank"><spring:message code="report.save" /></a>

	<%@include file="userSearch.jsp"%>

	<table>
		<thead>
			<tr>
				<th><a href="${usersSort}?propertyName=firstName"><spring:message code="users.fName" /></a></th>
				<th><a href="${usersSort}?propertyName=lastName"><spring:message code="users.lName" /></a>
				</th>
				<th><a href="${usersSort}?propertyName=login"><spring:message code="users.login" /></a></th>
				<th><a href="${usersSort}?propertyName=role"><spring:message code="users.role" /></a></th>
				<th><a href="${usersSort}?propertyName=region"><spring:message code="users.region" /></a></th>
			</tr>
		</thead>

		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.firstName}</td>
				<td>${user.lastName}</td>
				<td>${user.login}</td>
				<td>${user.role}</td>
				<td>${user.region}</td>
			</tr>
		</c:forEach>
	</table>

	<%@include file="userNavigation.jsp"%>

</div>