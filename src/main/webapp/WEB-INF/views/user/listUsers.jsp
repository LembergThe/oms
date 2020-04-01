<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="js/jquery-1.6.2.js"></script>

<script type="text/javascript">
	function deleteItem(id) {
		var url = "${deleteUser}?userID=" + id;
		var OK = confirm('proceed?');
		if (OK) {
			window.location = url;
		}
	}

	$(document).ready(function() {

		$('#field').change(function() {
			processSearch();
		});

		$('#condition').change(function() {
			processSearch();
		});

		var timer = null;

		$('#searchField').keyup(function() {
			clearTimeout(timer);
			timer = setTimeout(function() {
				processSearch();
			}, 500);
		});
	});

	function processSearch() {
		var data = $('#searchForm').serialize();
		$.getJSON('usersAJAX.htm', data, function(response) {
			$("#table").find("tr:gt(0)").remove();
			var records = response.records;

			$.each(records, function() {
				var newRow = "<tr>";

				newRow += "<td>" + this.firstName + "</td>";
				newRow += "<td>" + this.lastName + "</td>";
				newRow += "<td>" + this.login + "</td>";
				newRow += "<td>" + this.role.roleName + "</td>";
				newRow += "<td>" + this.region.regionName + "</td>";
				newRow += "<td><a href='editUser.htm?userID=" + this.id
						+ "'>Edit</a></td>";
				newRow += "<td><a href='javascript:deleteItem(" + this.id
						+ ");'>Delete</a></td>";
				newRow += "</tr>";

				$("#table").append(newRow);
			});

			$('#usersFound').html(response.recordsFound);
			$('#pageNumber').html(response.page);
			$('#pageCount').html(response.pageCount);

			var pageNumber = response.page * 1;
			var pagesCount = response.pageCount * 1;
			if (pageNumber == 1) {
				$('#first').attr('disabled', 'disabled');
				$('#previous').attr('disabled', 'disabled');
			} else {
				$('#first').removeAttr('disabled');
				$('#previous').removeAttr('disabled');
			}

			if (pageNumber == pagesCount) {
				$('#last').attr('disabled', 'disabled');
				$('#next').attr('disabled', 'disabled');
			} else {
				$('#last').removeAttr('disabled');
				$('#next').removeAttr('disabled');
			}
		});
	}
</script>

<div id="list">
	<h2>
		<spring:message code="users.h2" />
	</h2>

	<a href="addUser.htm"><spring:message code="users.cr" /> </a>

	<%@include file="userSearch.jsp"%>

	<table id="table">
		<thead>
			<tr>
				<th><a href="${usersSort}?propertyName=firstName"><spring:message
							code="users.fName" /> </a></th>
				<th><a href="${usersSort}?propertyName=lastName"><spring:message
							code="users.lName" /> </a></th>
				<th><a href="${usersSort}?propertyName=login"><spring:message
							code="users.login" /> </a></th>
				<th><a href="${usersSort}?propertyName=role"><spring:message
							code="users.role" /> </a></th>
				<th><a href="${usersSort}?propertyName=region"><spring:message
							code="users.region" /> </a></th>
				<th><spring:message code="users.edit" /></th>
				<th><spring:message code="users.del" /></th>
			</tr>
		</thead>

		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.firstName}</td>
				<td>${user.lastName}</td>
				<td>${user.login}</td>
				<td>${user.role}</td>
				<td>${user.region}</td>
				<td><a href="editUser.htm?userID=${user.id}"><spring:message
							code="users.edit" /> </a>
				<td><a href='javascript:deleteItem("${user.id}");'><spring:message
							code="users.del" /> </a>
				</td>
			</tr>
		</c:forEach>
	</table>

	<%@include file="userNavigation.jsp"%>

	<h5>
		<a href="reportUsers.htm"><spring:message code="users.crReport" />
		</a>
	</h5>
</div>