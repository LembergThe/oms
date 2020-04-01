<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="edit">
	<p style="color: red; font-size: xx-large; text-align: center;">Ooops
		something goes wrong !!!</p>
	<table  style="margin: 0;  position: center;">
		<tr>
			<td style="text-align:  center"><c:out value="${errorinfo.messageToUser }"></c:out>
			</td>

		</tr>
	</table>
	<table>
		<tr>
			<td>
				<FORM METHOD="get" ACTION="/edu/" style="margin: 0; text-align: center;">
					<input style="height: 35px; width: 150px; text-align : center;" type="submit"
						value="To Home Page">
				</FORM></td>

		</tr>
	</table>
	
	<div class="smallfont" style="margin-bottom: 2px">
		<b>Details</b> about <i>error</i>: <input type="button" value="Show"
			style="width: 45px; font-size: 10px; margin: 0px; padding: 0px;"
			onClick="if (this.parentNode.parentNode.getElementsByTagName('div')[1].getElementsByTagName('div')[0].style.display != '') { this.parentNode.parentNode.getElementsByTagName('div')[1].getElementsByTagName('div')[0].style.display = '';		this.innerText = ''; this.value = 'Hide'; } else { this.parentNode.parentNode.getElementsByTagName('div')[1].getElementsByTagName('div')[0].style.display = 'none'; this.innerText = ''; this.value = 'Show'; }">
	</div>
	<div class="alt2" style="margin: 0px; padding: 6px; border: 1px inset;">
		<div style="display: none;">
			<c:out value="${errorinfo.getException().getMessage() }"></c:out>
		</div>
	</div>
</div>