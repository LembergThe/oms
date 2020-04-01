<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet" type="text/css" media="screen"
	href="css/cardStyles.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="css/datePicker.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="css/cssAlertMsg.css">
<script type="text/javascript" src="js/disableMaestro.js"></script>
<script type="text/javascript" src="js/jquery-1.6.2.js"></script>
<script type="text/javascript" src="js/customAlertBox.js"></script>
<!-- required plugins -->
<script type="text/javascript" src="js/date.js"></script>
<!--[if IE]><script type="text/javascript" src="js/jquery.bgiframe.min.js"></script><![endif]-->
<script type="text/javascript" src="js/jquery.datePicker.js"></script>
<script type="text/javascript" src="js/cardvalidate.js"></script>



<script type="text/javascript" charset="utf-8">
	var startD;

	$(function() {

		startD = $('#startDate2').datePicker({
			startDate : '01/01/1996'
		}).val(new Date().asString()).trigger('change');
	});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#hiddenDate').hide();
		disableInputStartup();
	});
</script>

<div id="cardfield">
	<form id="form2" name="form2" method="post" action="">
		<fieldset style="height: 306px">
			<legend>
				<strong><spring:message code="card.legend" /> </strong>
			</legend>
			<p>
				<strong> </strong>
			</p>
			<table width="320" height="178" border="0">
				<tr>
					<td width="163"><p>
							<strong><spring:message code="card.type" /> <span
								class="redAsterisk" id="redAsterisk3">*</span> </strong><br> <strong><img
								src="resources/logo_ccVisa.gif" width="37" height="23"
								alt="Visa" /><img src="resources/logo_ccMC.gif" width="37"
								height="23" alt="MasterCard" /><img
								src="resources/logo_ccAmex.gif" width="37" height="23"
								alt="AmericanExpress" /><img src="resources/logo_ccMaestro.gif"
								width="37" height="23" alt="Maestro" /> </strong>
						</p></td>
					<td width="147"><strong> <select name="cardTypes"
							class="cardTypesCss" id="cardTypes" onchange="enableInput();">
								<option selected="selected" value="visa">Visa</option>
								<option value="mastercard">MasterCard</option>
								<option value="amex">American Express</option>
								<option value="maestro">Maestro</option>
						</select> </strong>
					</td>
				</tr>
				<tr>
					<td><strong><spring:message code="card.number" /> <span
							class="redAsterisk" id="redAsterisk4">*</span> </strong>
					</td>
					<td><strong> <input name="cardNumber2" type="text"
							id="cardNum" class="inputField" value="" maxlength="16" />
					</strong>
					</td>
				</tr>
				<tr>
					<td><strong><spring:message code="card.cvv" /> (<a
							href="#"
							onClick="MyWindow=window.open('resources/whatiscvv2.html','MyWindow','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,width=350,height=500'); return false;"><spring:message
									code="card.what" />
						</a>)<span class="redAsterisk"> *</span> </strong>
					</td>
					<td><strong> <input name="cvv" type="text"
							id="cvv2" class="inputField" maxlength="4" /> </strong>
					</td>
				</tr>
				<tr>
					<td><strong><spring:message code="card.exp" /> <span
							class="redAsterisk">*</span> </strong>
					</td>
					<td align="left"><select name="newCreditCardMonth"
						title="Month" id="newCreditCardMonth">
							<option value="1" selected="selected">01</option>
							<option value="2">02</option>
							<option value="3">03</option>
							<option value="4">04</option>
							<option value="5">05</option>
							<option value="6">06</option>
							<option value="7">07</option>
							<option value="8">08</option>
							<option value="9">09</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
					</select>&nbsp;<select name="newCreditCardYear" title="Year"
						id="newCreditCardYear">
							<option value="2011" selected="selected">2011</option>
							<option value="2012">2012</option>
							<option value="2013">2013</option>
							<option value="2014">2014</option>
							<option value="2015">2015</option>
							<option value="2016">2016</option>
							<option value="2017">2017</option>
							<option value="2018">2018</option>
							<option value="2019">2019</option>
							<option value="2020">2020</option>
							<option value="2021">2021</option>
							<option value="2022">2022</option>
							<option value="2023">2023</option>
							<option value="2024">2024</option>
							<option value="2025">2025</option>
							<option value="2026">2026</option>
							<option value="2027">2027</option>
							<option value="2028">2028</option>
							<option value="2029">2029</option>
							<option value="2030">2030</option>
							<option value="2031">2031</option>
							<option value="2032">2032</option>
							<option value="2033">2033</option>
							<option value="2034">2034</option>
							<option value="2035">2035</option>
							<option value="2036">2036</option>
							<option value="2037">2037</option>
					</select></td>
				</tr>
				<tr>
					<td><strong><spring:message code="card.start" /> <br>
							<spring:message code="card.maestroOnly" /><span
							class="redAsterisk"> *</span> </strong>
					</td>
					<td><strong> <input name="startDate2" type="text"
							class="date-pick" id="startDate2" /> </strong>
					</td>
				</tr>
				<tr>
					<td><strong><spring:message code="card.issue" /> <br>
							<spring:message code="card.maestroOnly" /> </strong>
					</td>
					<td><strong> <input name="issueNumber2" type="text"
							class="inputField" id="issueNumber2" /> </strong>
					</td>
				</tr>
				<tr>
					<td style="visibility: hidden;">kekek</td>
					<td></td>
				</tr>
				<tr>
					<td style="visibility: hidden;">kekek</td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td><span style="color: red;">* <spring:message
								code="card.mandatory" /> </span>
					</td>
				</tr>
			</table>
		</fieldset>
	</form>

	<p>&nbsp;</p>
</div>
