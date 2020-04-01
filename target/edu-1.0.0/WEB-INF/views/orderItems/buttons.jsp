<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">

	function askCancel(url, isSaved) {
		if(isSaved)
			window.location(url);
		var isOK = confirm("Are you sure you want to cancel? (All not saved changes will be lost)");
		if (isOK) {
			window.location = url;
		}
	}
	
	function order() {
		if(validateAll()) {
			document.getElementById("orderButton").setAttribute('action', 'orderItemsOrder.htm');
			document.getElementById("orderButton").setAttribute('method', 'post');
			
			document.getElementById("orderButton").submit();
		}
	}
	
</script>


<input type="submit" value="Save" id="save" name = "save"
	 onclick="javascript:$('#saveButton').submit()"/> 

<form:form  name="orderButton" id="orderButton" style="display: inline;" onclick="order();" >
	<input type="hidden" name="orderId" value="${orderId}" style = "display: inline;"/>
				
	<input type="button"  value="Order" <c:if test="${!orderData.isSaved }"> disabled="disabled" </c:if>  /> 
</form:form>

<form:form  name="cancelButton" id="cancelButton" action="orderItemsCancel.htm" method="post" style="display: inline;">
	<input type="hidden" name="orderId" value="${orderId}" style = "display: inline;"/>
				
	<input type="submit" value="Cancel"/>
<%-- 		 onclick="javascript:askCancel('orderItemsCancel.htm?',${orderData.isSaved})"/> --%>
</form:form>