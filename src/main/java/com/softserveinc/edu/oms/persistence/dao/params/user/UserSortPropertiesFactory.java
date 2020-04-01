package com.softserveinc.edu.oms.persistence.dao.params.user;

import com.softserveinc.edu.oms.persistence.dao.params.SortProperties;

public class UserSortPropertiesFactory {
	private UserSortPropertiesFactory() {
	}

	private static final String REGION_PARAM_NAME = "region";
	private static final String EMAIL_PARAM_NAME = "email";
	private static final String ROLE_PARAM_NAME = "role";
	private static final String LAST_NAME_PARAM_NAME = "lastName";
	private static final String FIRST_NAME_PARAM_NAME = "firstName";
	private static final String LOGIN_PARAM_NAME = "login";

	public static SortProperties createSortPropertiesForUser(
			final String propertyName) {
		if (propertyName.equalsIgnoreCase(LOGIN_PARAM_NAME)) {
			return createSortPropertiesForLogin();

		} else if (propertyName.equalsIgnoreCase(FIRST_NAME_PARAM_NAME)) {
			return createSortPropertiesForFirstName();

		} else if (propertyName.equalsIgnoreCase(LAST_NAME_PARAM_NAME)) {
			return createSortPropertiesForLastName();

		} else if (propertyName.equalsIgnoreCase(ROLE_PARAM_NAME)) {
			return createSortPropertiesForRole();

		} else if (propertyName.equalsIgnoreCase(EMAIL_PARAM_NAME)) {
			return createSortPropertiesForEmail();

		} else if (propertyName.equalsIgnoreCase(REGION_PARAM_NAME)) {
			return createSortPropertiesForRegion();

		} else {
			return new SortProperties();
		}
	}

	public static SortProperties createSortPropertiesForLogin() {
		return new SortProperties(LOGIN_PARAM_NAME);
	}

	public static SortProperties createSortPropertiesForFirstName() {
		return new SortProperties(FIRST_NAME_PARAM_NAME);
	}

	public static SortProperties createSortPropertiesForLastName() {
		return new SortProperties(LAST_NAME_PARAM_NAME);
	}

	public static SortProperties createSortPropertiesForRole() {
		return new SortProperties(ROLE_PARAM_NAME);
	}

	public static SortProperties createSortPropertiesForRegion() {
		return new SortProperties(REGION_PARAM_NAME);
	}

	public static SortProperties createSortPropertiesForEmail() {
		return new SortProperties(EMAIL_PARAM_NAME);
	}
}
