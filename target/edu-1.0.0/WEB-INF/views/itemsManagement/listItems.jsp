<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="js/jquery-1.6.2.js"></script>

<script type="text/javascript">
	function deleteItem(id) {
		var url = "${deleteItem}?productID=" + id;
		var OK = confirm('<spring:message code="im.deleteMsg" />');
		if (OK) {
			window.location = url;
		}
	}

	$(document).ready(function() {
		$('#field').change(function() {
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
		$.getJSON('productsAJAX.htm', data, function(response) {
			$("#table").find("tr:gt(0)").remove();
			var records = response.records;

			$.each(records, function() {
				var newRow = "<tr>";

				newRow += "<td>" + this.productName + "</td>";
				newRow += "<td>" + this.productDescription + "</td>";
				newRow += "<td>" + this.productPrice + "</td>";
				newRow += "<td><a href='editItem.htm?productID=" + this.id
						+ "'>Edit</a></td>";
				newRow += "<td><a href='javascript:deleteItem(" + this.id
						+ ");'>Delete</a></td>";
				newRow += "</tr>";

				$("#table").append(newRow);
			});

			$('#recordsFound').html(response.recordsFound);
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
		<spring:message code="im.h2" />
	</h2>

	<a href="addItem.htm"><spring:message code="im.AddProduct" /> </a>

	<%@include file="itemsSearch.jsp"%>

	<table id="table">
		<thead>
			<tr>
				<th><a href="${itemSort}?propertyName=productName"><spring:message
							code="im.Name" /> </a></th>
				<th><a href="${itemSort}?propertyName=productDescription"><spring:message
							code="im.Description" /> </a></th>
				<th><a href="${itemSort}?propertyName=productPrice"><spring:message
							code="im.Price" /> </a></th>
				<th><spring:message code="edit" /></th>
				<th><spring:message code="delete" /></th>
			</tr>
		</thead>

		<c:forEach items="${products}" var="product">
			<tr>
				<td>${product.productName}</td>
				<td>${product.productDescription}</td>
				<td>${product.productPrice}</td>
				<td><a href="editItem.htm?productID=${product.id}"><spring:message
							code="edit" /> </a>
				<td><a href='javascript:deleteItem("${product.id}");'><spring:message
							code="delete" /> </a></td>
			</tr>
		</c:forEach>
	</table>

	<%@include file="itemsNavigation.jsp"%>

	<h5>
		<a href="reportItems.htm"><spring:message code="users.crReport" />
		</a>
	</h5>
</div>