<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<script type="text/javascript" src="js/jquery-1.6.2.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$(document).ready(function() {
			$(".all").fadeIn(1500);
		});
		

		$("#table tr:nth-child(2n)").addClass("odd");
	});
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:insertAttribute name="title"/>
</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/style.css"/>" />
</head>
<body>

	<div class="all" style="display: none;">
		<div id="main">

			<tiles:insertAttribute name="header" />
			
			<%@include file="tabs.jsp"%>

			<div id="site_content">
				<div id="content">
					<tiles:insertAttribute name="body" />
				</div>
			</div>

			<tiles:insertAttribute name="footer" />

		</div>
	</div>
</body>
</html>
