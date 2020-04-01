<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<div id="edit">
	<fieldset>
		<legend><spring:message code="lo.login" /></legend>
		<c:if test="${not empty param.login_error}">
			<font color="red"> <spring:message code="lo.tryAgain" /><br /> <br /> <spring:message code="lo.reason" /> <c:out
					value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />. </font>
		</c:if>

		<form name="f" action="<c:url value='j_spring_security_check'/>"
			method="POST">
			<table>
				<tr>
					<td><spring:message code="lo.user" /></td>
					<td><input type='text' name='j_username' />
					</td>
				</tr>
				<tr>
					<td><spring:message code="lo.pass" /></td>
					<td><input type='password' name='j_password'></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="_spring_security_remember_me">
					</td>
					<td><spring:message code="lo.remember" /></td>
				</tr>

				<tr>
					<td colspan='2'><input name="submit" type="submit"><input
						name="reset" type="reset"></td>
				</tr>
			</table>
		</form>
	</fieldset>
</div>

