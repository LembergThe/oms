function processSubmit() {
	var submit = true;

	if ($("#nameError").html() != "" || $("#firstNameError").html() != ""
			|| $("#lastNameError").html() != ""
			|| $("#passwordError").html() != ""
			|| $("#emailError").html() != "" || $("#confirmError").html() != "") {

		submit = false;
	}

	if ($("#login").val() == "") {
		$("#nameError").html("Login name cannot be blank");
		submit = false;
	}

	if ($("#firstName").val() == "") {
		$("#firstNameError").html("First name cannot be blank");
		submit = false;
	}

	if ($("#lastName").val() == "") {
		$("#lastNameError").html("Last name cannot be blank");
		submit = false;
	}

	if ($("#email").val() == "") {
		$("#emailError").html("Email cannot be blank");
		submit = false;
	}

	if ($("#password").val() == "") {
		$("#passwordError").html("Password cannot be blank");
		submit = false;
	} else if ($("#confirmPassword").val() == "") {
		$("#confirmError").html("Confirm password cannot be blank");
		submit = false;
	}

	if (!submit) {
		alert('check all fields for valid data');
	}
	return submit;
}

$(document).ready(function() {
	$("#nameLoader").hide();
	checkLogin();

	$('#login').keyup(function() {
		checkLogin();
	});

	$('#firstName').keyup(function() {
		checkFirstName();
	});

	$('#lastName').keyup(function() {
		checkLastName();
	});

	$('#password').keyup(function() {
		checkPassword();
	});

	$('#confirmPassword').keyup(function() {
		checkConfirmPassword();
	});

	$('#email').keyup(function() {
		checkEmail();
	});
});

function checkLogin() {
	var login = $("#login").val();

	 if (login == editedName) {
		$("#nameError").html("");
		return;
	}

	$("#nameLoader").show();

	$.getJSON('availability.htm', {
		name : login
	}, function(availability) {
		if (!availability.available) {
			$("#nameError").html(login + " already in use");
		} else {
			$("#nameError").html("");

			validateName(login, "#nameError",
					"Login name cannot contain digits");

			if (login.length > 13) {
				$("#nameError").html("Login name is too long");
			}
		}
		$("#nameLoader").hide();
	});
}

function checkFirstName() {
	var l = $("#firstName").val();

	if (l.length > 13) {
		$("#firstNameError").html("First name is too long");
	} else {
		$("#firstNameError").html("");
	}
	
	validateName(l, "#firstNameError", "First name cannot contain digits");
}

function checkLastName() {
	var l = $("#lastName").val();

	if (l.length > 13) {
		$("#lastNameError").html("Last name is too long");
	} else {
		$("#lastNameError").html("");
	}
	
	validateName(l, "#lastNameError", "Last name cannot contain digits");
}

function checkPassword() {
	var p = $("#password").val();

	if (p == 0) {
		$("#passwordError").html("");
		return;
	}

	if (p.length < 4 || p.length > 13) {
		$("#passwordError").html(
				"Password cannot be shorter than "
						+ "4 and longer than 10 characters");
	} else {
		$("#passwordError").html("");
	}
}

function checkEmail() {
	var e = $("#email").val();

	if (e == 0) {
		$("#emailError").html("");
		return;
	}

	validateEmail(e, "#emailError", "You should use valid email address");
}

function checkConfirmPassword() {
	var pass = $("#password").val();
	var confirmPass = $("#confirmPassword").val();

	if (confirmPass == 0) {
		$("#confirmError").html("");
		return;
	}

	if (pass != confirmPass) {
		$("#confirmError").html("Confirm password has to be equal to password");
	} else {
		$("#confirmError").html("");
	}
}

function validateName(text, errorHolder, errorMessage) {
	$.getJSON('noDigitsRegex.htm', function(wrapper) {
		var pattern = new RegExp(wrapper.string);

		if (pattern.test(text)) {
			$(errorHolder).html(errorMessage);
		}
	});
}

function validateEmail(text, errorHolder, errorMessage) {
	$.getJSON('emailRegex.htm', function(wrapper) {
		var pattern = new RegExp(wrapper.string);

		if (pattern.test(text)) {
			$(errorHolder).html("");
		} else {
			$(errorHolder).html(errorMessage);
		}
	});
}