<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div id="menubar">
	<ul id="nav">
		<c:set var="adminLink">
			<spring:message code='tabs.adm' />
		</c:set>
		<c:set var="itemsLink">
			<spring:message code="tabs.iManage" />
		</c:set>
		<c:set var="ordersLink">
			<spring:message code="tabs.ordering" />
		</c:set>
		<c:set var="infoLink">
			<spring:message code="tabs.userinfo" />
		</c:set>

		<c:set var="current">
			<tiles:insertAttribute name="tabs" />
		</c:set>

		<sec:authorize access="hasRole('ROLE_Administrator')">
			<li <c:if test="${current == 'admin'}">class="cur"</c:if>><a
				href="/OMS/users.htm">${adminLink}</a>
			</li>
		</sec:authorize>

		<sec:authorize access="hasRole('ROLE_Supervisor')">
			<li <c:if test="${current == 'items'}">class="cur"</c:if>><a
				href="/OMS/itemManagement.htm">${itemsLink}</a>
			</li>
		</sec:authorize>

		<sec:authorize
			access="hasAnyRole('ROLE_Administrator','ROLE_Customer','ROLE_Merchandiser')">
		<li <c:if test="${current == 'orders'}">class="cur"</c:if>><a
			href="/OMS/order.htm">${ordersLink}</a>
		</li>
		</sec:authorize>

		<li <c:if test="${current=='info'}">class="cur"</c:if>><a
			href="/OMS/userInfo.htm">${infoLink}</a></li>

		<li class="spec"><a href="/OMS/logout.htm" class="spec"><img
				alt="logout" src="resources/logout.png"> </a></li>
	</ul>
</div>
