// Credit Card Validation Javascript
function validateAll() {
	if (validateCardNum()) {
		if (checkCvv2()) {
			if (checkExpDate()) {
				var cardType = document.getElementById("cardTypes").value;
				if (cardType == "maestro") {
					if (checkIssueNum()) {
						if (checkStartDateMaestro()) {
							return true;
						}
					}
				return false;
				} 
				
			}
		}
	
	return true;
	} else
		return false;
}

function validateCardNum() {
	var numbers = document.getElementById("cardNum").value;
	if (!checkCardNum(numbers)) {
		alert("Credit Card Number is incorrect. Please, re-type it again.");
		return false;
	} else
		return true;
}

function checkCardNum(s) {
	// remove non-numerics
	var v = "0123456789";
	var w = "";
	for ( var i = 0; i < s.length; i++) {
		x = s.charAt(i);
		if (v.indexOf(x, 0) != -1)
			w += x;
	}
	// validate number
	j = w.length / 2;
	if (j < 6.5 || j > 8 || j == 7)
		return false;
	k = Math.floor(j);
	m = Math.ceil(j) - k;
	c = 0;
	for (i = 0; i < k; i++) {
		a = w.charAt(i * 2 + m) * 2;
		c += a > 9 ? Math.floor(a / 10 + a % 10) : a;
	}
	for (i = 0; i < k + m; i++)
		c += w.charAt(i * 2 + 1 - m) * 1;
	return (c % 10 == 0);
}

function checkIssueNum() {
	var input = document.getElementById("issueNumber2").value;
	var pattern = /^\d{1}$/;

	if (!input.match(pattern)) {
		alert("Issue Number for Maestro card is incorrect. Please, re-type it again.");
		return false;
	} else
		return true;
}

function checkCvv2() {
	var input = document.getElementById("cvv2").value;
	var pattern1 = /^\d{3}$/;
	var pattern2 = /^\d{4}$/;

	if (!(input.match(pattern1) || input.match(pattern2))) {
		alert("CVV2 Code is incorrect. Please, re-type it again.");
		return false;
	} else
		return true;
}

function checkExpDate() {
	// get the month and year from the input and compare with the current date
	var inputMonth = document.getElementById("newCreditCardMonth").value;

	var inputYear = document.getElementById("newCreditCardYear").value;
	var currentDate = new Date();
	var currentMonth = currentDate.getMonth() + 1;
	var currentYear = currentDate.getFullYear();
	var currentDay = currentDate.getDate();

	// get the number of days in a given month
	var daysInInputMonth = 32 - new Date(inputYear, (inputMonth - 1), 32)
			.getDate();
	if ((inputYear < currentYear)) {
		alert("Expire Date is incorrect. Please, pick another one.");
		return false;
	} else if ((inputYear == currentYear) && (inputMonth < currentMonth)) {
		alert("Expire Date is incorrect. Please, pick another one.");
		return false;
	} else if ((inputYear == currentYear) && (inputMonth == currentMonth)) {
		if ((daysInInputMonth - currentDay) < 3) {
			alert("Unfortunately you are unable to pay by this Credit Card, since Expire Date is too close.");
			return false;
		}
	} 
		
	return true;
}

function checkStartDateMaestro() {

	var inputDate = document.getElementById("startDate2").value;
	var inputArray = inputDate.split("/");
	var startMonth = inputArray[1];
	var startYear = inputArray[2];
	startYear = parseInt(startYear);

	if (startMonth.match(/^0\d{1}$/)) {
		startMonth = startMonth.charAt(1);
		startMonth = parseInt(startMonth);
	}

	var expireMonth = document.getElementById("newCreditCardMonth").value;
	var expireYear = document.getElementById("newCreditCardYear").value;

	if ((startYear > expireYear)) {
		alert("Start Date for Maestro card is incorrent. Please, pick another one.");
		return false;
	} else if ((startYear == expireYear) && (startMonth >= expireMonth)) {
		alert("Start Date for Maestro card is incorrent. Please, pick another one.");
		return false;
	} else
		return true;
}
