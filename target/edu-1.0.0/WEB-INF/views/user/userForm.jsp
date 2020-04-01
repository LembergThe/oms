<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="js/json.min.js"></script>
<script type="text/javascript" src="js/jquery-1.6.2.js"></script>
<script type="text/javascript" src="js/user/addEditUser.js"></script>

<script type="text/javascript">
	var editedName = "${userModel.login}";
</script>

${userModel.login}

<div id="edit">
	<h3><spring:message code="uform.h3" /></h3>

	<br />
	<form:form method="POST" commandName="userModel"
		style="margin: 0; text-align: left;">
		<form:hidden path="id" />
		<form:hidden path="customerTypeID" />
		<form:hidden path="balance" />

		<table>
			<tr>
				<td><spring:message code="uform.login" /></td>
				<td><form:input path="login" id="login" /><img id="nameLoader"
					alt="please wait:)" src="resources/ajax-loader-small.gif" /><span
					id="nameError" class="error"></span> <form:errors path="login"
						class="error" /></td>
			</tr>
			<tr>
				<td><spring:message code="uform.fName" /></td>
				<td><form:input id="firstName" path="firstName" /><span
					id="firstNameError" class="error"></span> <form:errors
						path="firstName" class="error" />
				</td>
			</tr>
			<tr>
				<td><spring:message code="uform.lName" /></td>
				<td><form:input id="lastName" path="lastName" /><span
					id="lastNameError" class="error"></span> <form:errors
						path="lastName" class="error" />
				</td>
			</tr>
			<tr>
				<td><spring:message code="uform.pass" /></td>
				<td><form:password id="password" path="password" /><span
					id="passwordError" class="error"></span> <form:errors
						path="password" class="error" />
				</td>
			</tr>
			<tr>
				<td><spring:message code="uform.confirm" /></td>
				<td><form:password id="confirmPassword" path="confirmPassword" /><span
					id="confirmError" class="error"></span> <form:errors
						path="confirmPassword" class="error" />
				</td>
			</tr>
			<tr>
				<td><spring:message code="uform.email" /></td>
				<td><form:input id="email" path="email" /> <span
					id="emailError" class="error"></span> <form:errors id="emailError"
						path="email" class="error" /></td>
			</tr>
			<tr>
				<td><spring:message code="uform.region" /></td>
				<td><form:select path="regionID" items="${regions}"
						itemValue="id" />
			</tr>
		</table>


		<fieldset style="width: 300px; margin: 0; text-align: left;">
			<legend><spring:message code="uform.role" /></legend>

			<table>
				<c:forEach items="${roles}" var="role">
					<tr>
						<td><form:radiobutton path="roleID" value="${role.id}"
								label="${role}" />
						</td>
					</tr>
				</c:forEach>
			</table>
		</fieldset>

		<br />

		<input type="submit" value="<spring:message code="uform.bCr" />" onclick="return processSubmit()" />
		<input type="button" value="<spring:message code="uform.bCancel" />" 
			onClick="location.href='users.htm'" />
	</form:form>
</div>