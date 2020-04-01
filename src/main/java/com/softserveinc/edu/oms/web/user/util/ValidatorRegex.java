package com.softserveinc.edu.oms.web.user.util;

public class ValidatorRegex {
	private ValidatorRegex() {
	}

	public static final String NO_DIGITS_REGEX = "^.*[0-9].*$";
	public static final String EMAIL_REGEX = "^[\\w\\-]([\\.\\w])+[\\w]"
			+ "+@([\\w\\-]+\\.)+[a-zA-Z]{3,4}$";
}
