package com.softserveinc.edu.oms.web.user;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.softserveinc.edu.oms.web.user.model.UserModel;
import com.softserveinc.edu.oms.web.user.util.ValidatorRegex;

public class AddEditUserValidator implements Validator {

	private static final int MAX_NAME_LENGTH = 13;
	private static final int MAX_PASSWORD_LENGTH = 10;
	private static final int MIN_PASSWORD_LENGTH = 4;

	@Override
	public boolean supports(final Class<?> clazz) {
		return UserModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(final Object target, final Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "login", null,
				"Login cannot be blank");
		ValidationUtils.rejectIfEmpty(errors, "firstName", null,
				"First name cannot be blank or contain spaces");
		ValidationUtils.rejectIfEmpty(errors, "lastName", null,
				"Last name cannot be blank or contain spaces");

		UserModel userModel = (UserModel) target;

		if (userModel.getLogin().matches(ValidatorRegex.NO_DIGITS_REGEX)) {
			errors.rejectValue("login", null, "Login cannot contain digits");
		} else if (userModel.getLogin().length() > MAX_NAME_LENGTH) {
			errors.rejectValue("login", null, "Login is too long");
		}

		if (userModel.getFirstName().matches(ValidatorRegex.NO_DIGITS_REGEX)) {
			errors.rejectValue("firstName", null,
					"First name cannot contain digits");
		} else if (userModel.getFirstName().length() > 13) {
			errors.rejectValue("firstName", null, "First name is too long");
		}

		if (userModel.getLastName().matches(ValidatorRegex.NO_DIGITS_REGEX)) {
			errors.rejectValue("lastName", null,
					"Last name cannot contain digits");
		} else if (userModel.getLogin().length() > 13) {
			errors.rejectValue("lastName", null, "Last name is too long");
		}

		if (userModel.getPassword().length() < MIN_PASSWORD_LENGTH
				|| userModel.getPassword().length() > MAX_PASSWORD_LENGTH) {
			errors.rejectValue("password", null,
					"Password cannot be shorter than 4 and longer than 10 characters");
		} else if (userModel.getPassword().contains(" ")) {
			errors.rejectValue("password", null, "Cannot contain spaces");
		}

		if (!userModel.getPassword().equals(userModel.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", null,
					"Confirm password is not equal to password");
		}

		if (!userModel.getEmail().matches(ValidatorRegex.EMAIL_REGEX)) {
			errors.rejectValue("email", null, "Invalid email address");
		}

		if (userModel.getRoleID() == null || userModel.getRoleID().equals("")) {
			errors.rejectValue("roleID", null, "Invalid role");
		}
	}
}
