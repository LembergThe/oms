// JavaScript Document
function disableInputStartup() {
	document.getElementById("startDate2").disabled = true;
	document.getElementById("issueNumber2").disabled = true;
	disableStartDate();

}

function enableInput() {
	var card = document.getElementById("cardTypes").value;
	if (card == "maestro") {
		enableStartDate();
		document.getElementById("startDate2").disabled = false;
		document.getElementById("issueNumber2").disabled = false;

	} else {
		disableInputStartup();
		disableStartDate();
	}
}

function disableStartDate() {

	startD.dpSetDisabled(true);

}
function enableStartDate() {

	$('#startDate2').dpSetDisabled(false);

}
function setFormat() {

	
	$(function() {
		$('#dateDays').datePicker();
	});
	Date.format = 'dd/mm/yyyy';
}
function setFormatCr() {

	
	$(function() {
		$('#expDate2, #startDate2').datePicker();
	});
	Date.format = 'mm/yyyy';
}
/*function setFormatCr() {
	$('#expDate2').datePicker().val(
			expDate.getMonth() + '/' + expDate.getFullYear()).trigger('change');
}*/