package com.softserveinc.edu.oms.web.user.model;

public class UserModel {
	private String id;
	private String login;
	private String firstName;
	private String lastName;
	private String password;
	private String confirmPassword;
	private String email;
	private String regionID;
	private String roleID;
	private String customerTypeID;
	private String balance;

	public UserModel() {
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(final String login) {
		this.login = login;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(final String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getRegionID() {
		return regionID;
	}

	public void setRegionID(final String regionID) {
		this.regionID = regionID;
	}

	public String getRoleID() {
		return roleID;
	}

	public void setRoleID(final String roleID) {
		this.roleID = roleID;
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getCustomerTypeID() {
		return customerTypeID;
	}

	public void setCustomerTypeID(final String customerTypeID) {
		this.customerTypeID = customerTypeID;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(final String balance) {
		this.balance = balance;
	}
}
