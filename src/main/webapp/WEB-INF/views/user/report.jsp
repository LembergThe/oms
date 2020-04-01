<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<script type="text/javascript" src="js/jquery-1.6.2.js"></script>
<script type="text/javascript">
	function show(pdfURL) {
		$.ajax({
			url : pdfURL,
			cache : false,
			success : function() {
				window.location.assign(pdfURL);
			}
		});
	}

	$(document).ready(function() {
		show("${pdf}");
	});
</script>

<style>
</style>
</head>
<body>
	<div
		style="position: absolute; top: 50%; left: 50%; margin-top: -25px; margin-left: -25px;">
		<img alt="please wait:)" src="resources/ajax-loader.gif" />
	</div>
</body>
</html>